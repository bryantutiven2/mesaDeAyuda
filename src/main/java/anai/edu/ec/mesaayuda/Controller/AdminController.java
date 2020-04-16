
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.IGrupoDao;
import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DAO.ISubtipoDao;
import anai.edu.ec.mesaayuda.DAO.ITipoGrupoDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.GrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SubtipoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.TipoGrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.ConsultaObjeto;
import anai.edu.ec.mesaayuda.Entity.Grupo;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.Subtipo;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.ServiceResponse;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import static anai.edu.ec.mesaayuda.Service.fechaSolicitud.convertirFecha;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bryan
 */
@Controller
@RequestMapping( "/admin" )
@RestController
public class AdminController {
    private ModelAndView model = new ModelAndView();
    private Usuario usuario;
    private IGrupoDao grupoDao = new GrupoImpl();
    private ISubtipoDao subtipoDao = new SubtipoImpl();
    private ISolicitudDao solicitudDao = new SolicitudImpl();
    private ITipoGrupoDao tipoDao = new TipoGrupoImpl();
    private IUsuarioDao usuarioDao = new UsuarioImpl();
    private List<SolicitudAyuda> listaSolicitudAyuda;
    private Boolean retornoSolicitud = null;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    
    @RequestMapping(value = { "/enviarSolicitud"}, method = RequestMethod.POST)
    public ModelAndView enviarSolicitudAdmin(HttpServletRequest request, HttpServletResponse response){
        try{
            String idgrupo = request.getParameter("grupo");
            Integer idTipo = Integer.parseInt(request.getParameter("tipoGrupo_CS"));
            Integer idSubtipo = Integer.parseInt(request.getParameter("subtipo_CS").split("-")[1]);
            Integer idTecnico = Integer.parseInt(request.getParameter("tecnico_cs"));
            Integer idUserSolicitaAyuda = Integer.parseInt(request.getParameter("idUserSolicitaA"));
            String descripcion = request.getParameter("descripcion");
            Integer nvez = Integer.parseInt(request.getParameter("nvez"));
            String id_nvez = "";
            if(nvez>1)
                id_nvez = request.getParameter("idsnvez");
            else
                id_nvez = "null";
            String fechaInicio = request.getParameter("fechaInicio_cs");
            String fechafin = request.getParameter("fechaFin_cs");
            String estadoSolicitud = request.getParameter("estado_cs");
            String estadoSolicitudTecnico = "inactiva";
            
            Grupo grupo = grupoDao.obtenerElemento(idgrupo);
            TipoGrupo tipoGrupo = tipoDao.obtenerElemento(idTipo);
            Subtipo subtipo = subtipoDao.obtenerElemento(idSubtipo);
            Usuario tecnico = usuarioDao.obtenerElemento(idTecnico);
            Usuario userSolicitaAyuda = usuarioDao.obtenerElemento(idUserSolicitaAyuda);
            Date datefi = convertirFecha(fechaInicio);
            Date dateff = convertirFecha(fechafin);
            
            Integer idSolicitud = solicitudDao.generarIdSolicitud();
            SolicitudAyudaId solicitudAyudaId = new SolicitudAyudaId(idSolicitud, idgrupo);
            
            usuario = obtenerSessionUsuario(request, response);
            
            SolicitudAyuda objetoSolicitud = new SolicitudAyuda();
            objetoSolicitud.setId(solicitudAyudaId);
            objetoSolicitud.setDescripcion(descripcion);
            objetoSolicitud.setAyudaNVez(nvez);
            objetoSolicitud.setIdsSolicitudNVez(id_nvez);
            objetoSolicitud.setEstadoBorrado(0);
            objetoSolicitud.setEstadoSolicitud(estadoSolicitud);
            objetoSolicitud.setEstadoSolicitudTecnico(estadoSolicitudTecnico);
            objetoSolicitud.setFechaInicio(datefi);
            objetoSolicitud.setFechaFin(dateff);
            objetoSolicitud.setUsuarioByIdUserSolicitaAyuda(userSolicitaAyuda);
            objetoSolicitud.setUsuarioByIdUserAdmin(usuario);
            objetoSolicitud.setUsuarioByIdUserTecnico(tecnico);
            objetoSolicitud.setGrupo(grupo);
            objetoSolicitud.setTipoGrupo(tipoGrupo);
            objetoSolicitud.setSubtipo(subtipo);
            
            retornoSolicitud = solicitudDao.insertar(objetoSolicitud);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return crearSolicitudAdmin(request, response);
    }
    
    @GetMapping( "/cargarNuevasSolicitudes")
    public ResponseEntity<Object> cargarNuevasSolicitudes(HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<SolicitudTabla>> respo = null;
        HashSet<Integer> setIdUnico = new HashSet<Integer>();
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        usuario = obtenerSessionUsuario(request, response);
        String grupo = usuario.getRol().split("_")[1];
        String estado = "pendiente-reevaluar";
        String listaIds = null;
        String userTecnico = null;
        String descripcionTecnico = null;
        try{
            listaSolicitudAyuda = solicitudDao.nuevasSolicitudes(grupo,estado);
            if(listaSolicitudAyuda != null){
                for(SolicitudAyuda lista : listaSolicitudAyuda){
                    if(!setIdUnico.contains(lista.getId().getIdSolicitud())){
                        setIdUnico.add(lista.getId().getIdSolicitud());
                        String userSolicitaAyuda = lista.getUsuarioByIdUserSolicitaAyuda().getNombre() +" "+ lista.getUsuarioByIdUserSolicitaAyuda().getApellido();
                        if(lista.getUsuarioByIdUserTecnico()!= null)
                            userTecnico = lista.getUsuarioByIdUserTecnico().getNombre()+" "+lista.getUsuarioByIdUserTecnico().getApellido();
                        else
                            userTecnico = "";
                        if(lista.getMensajeUserTecnico()==null || lista.getMensajeUserTecnico().equals("null"))
                            descripcionTecnico = "";
                        else
                            descripcionTecnico = lista.getMensajeUserTecnico();

                        if(lista.getIdsSolicitudNVez().equals("null") || lista.getIdsSolicitudNVez() == null)
                            listaIds = " ";
                        else
                            listaIds = lista.getIdsSolicitudNVez();

                        SolicitudTabla objetoTabla = new SolicitudTabla();
                        objetoTabla.setId(lista.getId().getIdSolicitud());
                        objetoTabla.setDescripcion(lista.getDescripcion());
                        objetoTabla.setUserSolicitaAyuda(userSolicitaAyuda);
                        objetoTabla.setDescripcionTecnico(descripcionTecnico);
                        objetoTabla.setUserTecnico(userTecnico);
                        objetoTabla.setN_vez(lista.getAyudaNVez());
                        objetoTabla.setIds_n_vez(listaIds);
                        objetoTabla.setFechaInicio(dateFormat.format(lista.getFechaInicio()));
                        objetoTabla.setEstadoSolicitud(lista.getEstadoSolicitud());
                        listaTabla.add(objetoTabla); 
                    }
                }
            }
            respo = new ServiceResponse<>("success",listaTabla);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);   
    }
    
    @RequestMapping(value = { "/nuevasSolicitudes"}, method = RequestMethod.GET)
    public ModelAndView nuevasSolicitudes(HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        String listaIds = null;        
        usuario = obtenerSessionUsuario(request, response);
        String grupo = usuario.getRol().split("_")[1];
        try{
            
            List<TipoGrupo> listaTipos = tipoDao.obtenerElementos(grupo);
            if(listaTipos != null)
                model.addObject("listarTiposSN",listaTipos);
            List<Usuario> listaTecnicos = usuarioDao.obtenerElementos(grupo);
            if(listaTecnicos != null)
                model.addObject("listarTecnicoSN", listaTecnicos);
            model.addObject("listaNuevasSolicitudes",listaTabla);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        datosUsuario();
        model.addObject("viewMain","solicitudesNuevasAdmin");
        model.setViewName("menuUsuario");
        return model;
    }
    
    @PostMapping( "/actualizarSolicitud" )
    public ResponseEntity<Object> actualizarSolicitud(@RequestBody SolicitudTabla solicitudT,
                                    HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<SolicitudTabla>> respo = null;
        String estadoSolicitudTecnico = "inactiva";
        String mensaje = "error";
        try{
            String estadoSolicitudT = solicitudT.getEstadoSolicitud();
            
            String cod = String.valueOf(solicitudT.getId());
            String tipo_grupo = solicitudT.getTipo();
            String fechaFin = solicitudT.getFechaFin();
            String tecnico = String.valueOf(solicitudT.getUserTecnico());
            
            Usuario userTecnico = usuarioDao.obtenerElemento(Integer.parseInt(tecnico));
            TipoGrupo tipo = tipoDao.obtenerElemento(Integer.parseInt(tipo_grupo));
            String grupoTipo = tipo.getGrupo().getIdGrupo();
            SolicitudAyudaId idSolicitud = new SolicitudAyudaId(Integer.parseInt(cod), grupoTipo);
            Date date = convertirFecha(fechaFin);
            
            if(userTecnico != null && tipo != null && date != null){
                SolicitudAyuda objetoSolicitud = solicitudDao.obtenerElemento(idSolicitud);
                if(estadoSolicitudT.equals("asignada")){
                    objetoSolicitud.setId(idSolicitud);
                    objetoSolicitud.setTipoGrupo(tipo);
                    objetoSolicitud.setFechaFin(date);
                    objetoSolicitud.setUsuarioByIdUserTecnico(userTecnico);
                    objetoSolicitud.setUsuarioByIdUserAdmin(usuario);
                    objetoSolicitud.setEstadoSolicitud(estadoSolicitudT);
                    objetoSolicitud.setEstadoSolicitudTecnico(estadoSolicitudTecnico);
                    Boolean retorno = solicitudDao.actualizar(objetoSolicitud);
                    if(retorno == true)
                        mensaje = "asignado";
                }
                else if(estadoSolicitudT.equals("finalizada")){
                    objetoSolicitud.setEstadoSolicitud(estadoSolicitudT);
                    Boolean retorno = solicitudDao.actualizar(objetoSolicitud);
                    if(retorno == true)
                        mensaje = "finalizado";
                }
            }
            respo = new ServiceResponse<>(mensaje);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/crearSolictud"}, method = RequestMethod.GET)
    public ModelAndView crearSolicitudAdmin(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        String grupoId = usuario.getRol().split("_")[1];
        List<Usuario> listaTecnicos = usuarioDao.obtenerElementos(grupoId);
        List<Usuario> listaUsuarios = usuarioDao.obtenerUsuarios();
        List<TipoGrupo> listaTipos = tipoDao.obtenerElementos(grupoId);
        List<Subtipo> listaSubtipos = subtipoDao.obtenerElementos(grupoId);
        model.addObject("idAdmin", usuario.getIdUsuario());
        model.addObject("listarTiposCS",listaTipos);
        model.addObject("listarTecnicoCS", listaTecnicos);
        model.addObject("listaUsuariosSA", listaUsuarios);
        model.addObject("listarSubtipo_CS",listaSubtipos);
        model.addObject("viewMain","crearSolicitudAdmin");
        model.setViewName("menuUsuario");
        datosUsuario();
        return model;
    }
    
    @PostMapping( "/filtroConsultarSolicitud" )
    public ResponseEntity<Object> filtroConsultarSolicitud(@RequestBody ConsultaObjeto consultaO,
                                                HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        ServiceResponse<List<SolicitudTabla>> respo = null;
        HashSet<Integer> listaIds = new HashSet<Integer>();
        usuario = obtenerSessionUsuario(request, response);
        String fechaF, userTecnico, tipoG;
        String tipoSolicitud = consultaO.getTipoSolicitud();
        String grupo = consultaO.getGrupo();
        String estado = consultaO.getEstado();
        try{
            if(tipoSolicitud.equals("mSolicitudes")){ //mis solicitudes
                if(grupo != null && estado == null)
                    listaSolicitudAyuda = solicitudDao.buscarPorGrupo(grupo, usuario.getIdUsuario());
                else if(estado != null && grupo == null)
                    listaSolicitudAyuda = solicitudDao.buscarPorEstado(estado, usuario.getIdUsuario());
                else if(estado != null && grupo != null){
                    listaSolicitudAyuda = solicitudDao.buscarPorGrupo(grupo, usuario.getIdUsuario());
                    listaSolicitudAyuda.addAll(solicitudDao.buscarPorEstado(estado, usuario.getIdUsuario()));
                }
                if(listaSolicitudAyuda != null){
                    for(SolicitudAyuda lista : listaSolicitudAyuda){
                        if(!listaIds.contains(lista.getId().getIdSolicitud())){
                            listaIds.add(lista.getId().getIdSolicitud());
                            if(lista.getFechaFin() != null)
                                fechaF = String.valueOf(dateFormat.format(lista.getFechaFin()));
                            else
                                fechaF = "";
                            if(lista.getUsuarioByIdUserTecnico() != null)
                                userTecnico = lista.getUsuarioByIdUserTecnico().getNombre() + " " + lista.getUsuarioByIdUserTecnico().getApellido();
                            else
                                userTecnico = "";
                            if(lista.getTipoGrupo() != null)
                                tipoG = lista.getTipoGrupo().getNombreTipo();
                            else
                                tipoG = "";
                            listaTabla.add(
                                new SolicitudTabla(lista.getId().getIdSolicitud(), lista.getGrupo().getNombreGrupo(),
                                        tipoG, lista.getDescripcion(), userTecnico,
                                        String.valueOf(dateFormat.format(lista.getFechaInicio())),
                                        fechaF, lista.getEstadoSolicitud()));
                        }

                    }
                    respo = new ServiceResponse<>("success",listaTabla);
                }
            }
            else if(tipoSolicitud.equals("rSolicitudes")){ //solicitudes realizadas
                String estadoAyuda = "reevaluar-finalizada";
                listaSolicitudAyuda = solicitudDao.cargarSolicitudesTecnico(usuario.getIdUsuario(), estadoAyuda);
                if(listaSolicitudAyuda != null){
                    for(SolicitudAyuda lista : listaSolicitudAyuda){
                        String userSolicitaAyuda = lista.getUsuarioByIdUserSolicitaAyuda().getNombre() +" "+ lista.getUsuarioByIdUserSolicitaAyuda().getApellido();
                        listaTabla.add(
                            new SolicitudTabla(
                                    lista.getId().getIdSolicitud(), lista.getDescripcion(), lista.getMensajeUserTecnico(),userSolicitaAyuda,
                                    String.valueOf(dateFormat.format(lista.getFechaInicio())),
                                    String.valueOf(dateFormat.format(lista.getFechaFin())),
                                    String.valueOf(dateFormat.format(lista.getFechaInicioTecnico())),
                                    String.valueOf(dateFormat.format(lista.getFechaFinTecnico())),
                                    lista.getEstadoSolicitudTecnico(), lista.getEstadoSolicitud()
                            )); 
                    }
                }
                respo = new ServiceResponse<>("success",listaTabla);
            }
            else{
                respo = new ServiceResponse<>("success",null);
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/consultarSolicitud" }, method = RequestMethod.GET)
    public ModelAndView consultarSolicitud(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        model.addObject("viewMain","consultaSolicitudesAdmin");
        model.setViewName("menuUsuario");
        return model;
    }
    
    private void datosUsuario(){
        
        String rol = usuario.getRol();
        model.addObject("rol",rol);
        model.addObject("username", usuario.getNombre() + " " + usuario.getApellido());
        model.addObject("usuario", usuario.getUsuario());
        model.addObject("correo", usuario.getCorreo());
    }
}
