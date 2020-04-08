
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
import anai.edu.ec.mesaayuda.Entity.Grupo;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.Subtipo;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Enum.EstadoSolicitud;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import static anai.edu.ec.mesaayuda.Service.fechaSolicitud.convertirFecha;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bryan
 */
@Controller
@RequestMapping( "/admin" )
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
            String id_nvez = request.getParameter("idsnvez");
            String fechaInicio = request.getParameter("fechaInicio_cs");
            String fechafin = request.getParameter("fechaFin_cs");
            String estadoSolicitud = request.getParameter("estado_cs");
            
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
    
    
    @RequestMapping(value = { "/nuevasSolicitudes"}, method = RequestMethod.GET)
    public ModelAndView nuevasSolicitudes(HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        String listaIds = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        usuario = obtenerSessionUsuario(request, response);
        String grupo = usuario.getRol().split("_")[1];
        try{
            String estado = "pendiente";
            listaSolicitudAyuda = solicitudDao.nuevasSolicitudes(grupo, estado);
            if(listaSolicitudAyuda != null){
                for(SolicitudAyuda lista : listaSolicitudAyuda){
                    if(lista.getIdsSolicitudNVez().equals("null"))
                        listaIds= "";
                    listaTabla.add(
                            new SolicitudTabla(lista.getId().getIdSolicitud(), lista.getAyudaNVez(), listaIds,
                                    lista.getDescripcion(),
                                    lista.getUsuarioByIdUserSolicitaAyuda().getNombre()+" "+ lista.getUsuarioByIdUserSolicitaAyuda().getApellido(),
                                    String.valueOf(dateFormat.format(lista.getFechaInicio()))));
                }
                List<TipoGrupo> listaTipos = tipoDao.obtenerElementos(grupo);
                if(listaTipos != null)
                    model.addObject("listarTiposSN",listaTipos);
                List<Usuario> listaTecnicos = usuarioDao.obtenerElementos(grupo);
                if(listaTecnicos != null)
                    model.addObject("listarTecnicoSN", listaTecnicos);
                model.addObject("listaNuevasSolicitudes",listaTabla);
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        datosUsuario();
        model.addObject("viewMain","solicitudesNuevasAdmin");
        model.setViewName("menuUsuario");
        return model;
    }
    
    @RequestMapping(value = { "/actualizarSolicitud"}, method = RequestMethod.POST)
    public ModelAndView actualizarSolicitud(HttpServletRequest request, HttpServletResponse response){
        try{
            String cod = request.getParameter("codigo_sn");
            String tipo_grupo = request.getParameter("tipoGrupo_sn");
            String fechaFin = request.getParameter("fechaFin_sn");
            String tecnico = request.getParameter("tecnico_sn");
            
            Usuario userTecnico = usuarioDao.obtenerElemento(Integer.parseInt(tecnico));
            TipoGrupo tipo = tipoDao.obtenerElemento(Integer.parseInt(tipo_grupo));
            String grupoTipo = tipo.getGrupo().getIdGrupo();
            SolicitudAyudaId idSolicitud = new SolicitudAyudaId(Integer.parseInt(cod), grupoTipo);
            Date date = convertirFecha(fechaFin);
            
            if(userTecnico != null && tipo != null && date != null){
                System.out.println(date);
                SolicitudAyuda objetoSolicitud = solicitudDao.obtenerElemento(idSolicitud);
                objetoSolicitud.setId(idSolicitud);
                objetoSolicitud.setTipoGrupo(tipo);
                objetoSolicitud.setFechaFin(date);
                objetoSolicitud.setUsuarioByIdUserTecnico(userTecnico);
                objetoSolicitud.setUsuarioByIdUserAdmin(usuario);
                objetoSolicitud.setEstadoSolicitud("asignada");
                Boolean retorno = solicitudDao.actualizar(objetoSolicitud);
                if(retorno.equals(true))
                    System.out.println(retorno);
            }
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
//        nuevasSolicitudes(request, response);
        return nuevasSolicitudes(request, response);
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
    
    @RequestMapping(value = { "/consultarSolicitud" }, method = RequestMethod.GET)
    public ModelAndView consultarSolicitud(HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        HashSet<Integer> listaIds = new HashSet<Integer>();
        usuario = obtenerSessionUsuario(request, response);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaF, userTecnico, tipoG;
        try{
            listaSolicitudAyuda = solicitudDao.buscarSolicitudes(usuario.getIdUsuario());
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
                model.addObject("listaConsultaSolicitudes",listaTabla);
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        datosUsuario();
        model.addObject("viewMain","consultaSolicitudes");
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
