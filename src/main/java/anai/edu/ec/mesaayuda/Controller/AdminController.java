
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.IDepartamentoDao;
import anai.edu.ec.mesaayuda.DAO.IEncuestaDao;
import anai.edu.ec.mesaayuda.DAO.IGrupoDao;
import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DAO.ISubtipoDao;
import anai.edu.ec.mesaayuda.DAO.ITipoGrupoDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.DepartamentoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.EncuestaImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.GrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SubtipoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.TipoGrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.ConsultaObjeto;
import anai.edu.ec.mesaayuda.Entity.Departamento;
import anai.edu.ec.mesaayuda.Entity.Encuesta;
import anai.edu.ec.mesaayuda.Entity.Grupo;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.Subtipo;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.ServiceResponse;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import anai.edu.ec.mesaayuda.Service.fechaSolicitud;
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
    private IEncuestaDao encuestaDao = new EncuestaImpl();
    private IDepartamentoDao departamentoDao = new DepartamentoImpl();
    private IGrupoDao grupoDao = new GrupoImpl();
    private ISubtipoDao subtipoDao = new SubtipoImpl();
    private ISolicitudDao solicitudDao = new SolicitudImpl();
    private ITipoGrupoDao tipoDao = new TipoGrupoImpl();
    private IUsuarioDao usuarioDao = new UsuarioImpl();
    private List<SolicitudAyuda> listaSolicitudAyuda;
    private Boolean retornoSolicitud = null;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    /***
     * Este método permite guardar una solicitud que ha realizado el administrador
     * El método es un post ajax del lado del cliente
     * @param solicitudT es un objeto que se recibe del cliente con datos como del grupo, descripción
     * @param request
     * @param response
     * @return Un string de éxito en caso de que se haya guardado la solicitud, error si no se guardo la solicitud
     */
    @PostMapping( "/enviarSolicitud" )
    public ResponseEntity<Object> enviarSolicitud(@RequestBody SolicitudTabla solicitudT,
                                                HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<SolicitudTabla>> respo = null;
        try{
            usuario = obtenerSessionUsuario(request, response);
            String idgrupo = solicitudT.getGrupo();
            Integer nvez = solicitudT.getN_vez();
            String id_nvez = "";
            if(nvez>1)
                id_nvez = solicitudT.getIds_n_vez();
            else
                id_nvez = "null";
            String descripcion = solicitudT.getDescripcion();
            Grupo grupo = grupoDao.obtenerElemento(idgrupo);
            Integer idSolicitud = solicitudDao.generarIdSolicitud();
            SolicitudAyudaId solicitudAyudaId = new SolicitudAyudaId(idSolicitud, idgrupo);
            SolicitudAyuda objetoSolicitud = new SolicitudAyuda();
            
            if(idgrupo.equals("sist") || idgrupo.equals("mant")){
                objetoSolicitud.setId(solicitudAyudaId);
                objetoSolicitud.setGrupo(grupo);
                objetoSolicitud.setDescripcion(descripcion);
                objetoSolicitud.setAyudaNVez(nvez);
                objetoSolicitud.setIdsSolicitudNVez(id_nvez);
                objetoSolicitud.setEstadoBorrado(0);
            }
            if(idgrupo.equals("sist")){
                Integer idTipo = Integer.parseInt(solicitudT.getTipo());
                Integer idSubtipo = Integer.parseInt(solicitudT.getIdSubtipo().split("-")[1]);
                Integer idTecnico = Integer.parseInt(solicitudT.getUserTecnico());
                Integer idUserSolicitaAyuda = Integer.parseInt(solicitudT.getUserSolicitaAyuda());
                Integer idEncuesta = solicitudT.getIdEncuesta();
                String fechaInicio = solicitudT.getFechaInicio();
                String fechafin = solicitudT.getFechaFin();
                String estadoSolicitud = solicitudT.getEstadoSolicitud();
                String estadoSolicitudTecnico = "inactiva";
                TipoGrupo tipoGrupo = tipoDao.obtenerElemento(idTipo);
                Subtipo subtipo = subtipoDao.obtenerElemento(idSubtipo);
                Usuario tecnico = usuarioDao.obtenerElemento(idTecnico);
                Usuario userSolicitaAyuda = usuarioDao.obtenerElemento(idUserSolicitaAyuda);
                Encuesta encuesta = encuestaDao.obtenerElemento(idEncuesta);
                Date datefi = convertirFecha(fechaInicio);
                Date dateff = convertirFecha(fechafin);
                
                objetoSolicitud.setEstadoSolicitud(estadoSolicitud);
                objetoSolicitud.setEstadoSolicitudTecnico(estadoSolicitudTecnico);
                objetoSolicitud.setFechaInicio(datefi);
                objetoSolicitud.setFechaFin(dateff);
                objetoSolicitud.setUsuarioByIdUserSolicitaAyuda(userSolicitaAyuda);
                objetoSolicitud.setUsuarioByIdUserAdmin(usuario);
                objetoSolicitud.setUsuarioByIdUserTecnico(tecnico);
                objetoSolicitud.setTipoGrupo(tipoGrupo);
                objetoSolicitud.setSubtipo(subtipo);
                objetoSolicitud.setEncuesta(encuesta);
                objetoSolicitud.setEstadoEncuesta(0);
            }
            
            Boolean retorno = solicitudDao.insertar(objetoSolicitud);
            if(retorno)
                respo = new ServiceResponse<>("success");
            else
                respo = new ServiceResponse<>("error");
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);
    }
    
    /***
     * Permite obtener las solicitudes nuevas que el administrador debe gestionar como es el caso de asignar un
     * técnico a una solicitud
     * @param request
     * @param response
     * @return retorna una lista de solicitudes que tienen como estado pendiente o reevaluar
     */
    @GetMapping( "/cargarNuevasSolicitudes")
    public ResponseEntity<Object> cargarNuevasSolicitudes(HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<SolicitudTabla>> respo = null;
        HashSet<Integer> setIdUnico = new HashSet<Integer>();
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        usuario = obtenerSessionUsuario(request, response);
        String grupo = usuario.getRol().split("_")[1];
        String estado = "asignada-pendiente-reevaluar";
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
    
    /***
     * Permite gestionar una solicitud para asignarle el técnico, tipo, fecha posible a arreglar
     * @param request
     * @param response
     * @return un model, una lista de tipos, una lista de tecnicos, lista de encuestas a asignar
     */
    @RequestMapping(value = { "/nuevasSolicitudes"}, method = RequestMethod.GET)
    public ModelAndView nuevasSolicitudes(HttpServletRequest request, HttpServletResponse response){
        List<Encuesta> listaE = new ArrayList<>();
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
            
            List<Encuesta> listaEncuesta = encuestaDao.obtenerElementos();
            if(listaEncuesta.size()>0){
                for(Encuesta e: listaEncuesta){
                    if(e.getEstadoBorrado() == 0)
                        listaE.add(e);
                }
                model.addObject("listaEncuesta",listaE);
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
    
    /***
     * 
     * @param solicitudT es el objeto que contiene los datos a ser actualizado en la solicitud
     * @param request
     * @param response
     * @return un String con error, asignada, finalizada dependiendo de que tipo de actualización se realizo
     */
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
            Integer idEncuesta = solicitudT.getIdEncuesta();
            
            Usuario userTecnico = usuarioDao.obtenerElemento(Integer.parseInt(tecnico));
            TipoGrupo tipo = tipoDao.obtenerElemento(Integer.parseInt(tipo_grupo));
            Encuesta encuesta = encuestaDao.obtenerElemento(idEncuesta);
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
                    objetoSolicitud.setMensajeUserTecnico("");
                    objetoSolicitud.setEncuesta(encuesta);
                    objetoSolicitud.setEstadoEncuesta(0);
                    Boolean retorno = solicitudDao.actualizar(objetoSolicitud);
                    if(retorno == true)
                        mensaje = "asignado";
                }
            }
            respo = new ServiceResponse<>(mensaje);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);
    }
    
    /***
     * 
     * @param request
     * @param response
     * @return una lista de objetos con usuarios, tipo de grupos, subtipos, encuestas, de esta manera la vista tendrá
     * lo necesario para crearse una solicitud
     */
    @RequestMapping(value = { "/crearSolictud"}, method = RequestMethod.GET)
    public ModelAndView crearSolicitudAdmin(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        List<Encuesta> listaE = new ArrayList<>();
        List<Encuesta> listaEncuesta = encuestaDao.obtenerElementos();
        for(Encuesta e: listaEncuesta){
            if(e.getEstadoBorrado() == 0)
                listaE.add(e);
        }
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
        model.addObject("listaEncuesta",listaE);
        model.addObject("viewMain","crearSolicitudAdmin");
        model.setViewName("menuUsuario");
        datosUsuario();
        return model;
    }
    
    /***
     * 
     * @param consultaO es un objeto que contiene que tipo de datos requiere el cliente
     * @param request
     * @param response
     * @return una lista de solicitados en base a los parametros requeridos, por ejemplo solicitudes realizadas
     * al grupo sistemas, filtrar solicitudes ya sea pendiente, finalizada, asignada
     */
    @PostMapping( "/filtroConsultarSolicitud" )
    public ResponseEntity<Object> filtroConsultarSolicitud(@RequestBody ConsultaObjeto consultaO,
                                                HttpServletRequest request, HttpServletResponse response){
        consultaO.toString();
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        ServiceResponse<List<SolicitudTabla>> respo = null;
        HashSet<Integer> listaIds = new HashSet<Integer>();
        HashSet<String> grupos = new HashSet<>();
        grupos.add("sist");
        grupos.add("mant");
        HashSet<String> estados = new HashSet<>();
        estados.add("pendiente");
        estados.add("asignada");
        estados.add("reevaluar");
        estados.add("finalizada");
        usuario = obtenerSessionUsuario(request, response);
        String fechaIT, fechaFT, fechaF, userTecnico, tipoG, descripT;
        String tipoSolicitud = consultaO.getTipoSolicitud();
        Date fechaD = fechaSolicitud.convertirFechaSimple(consultaO.getFechaDesde());
        Date fechaH = fechaSolicitud.convertirFechaSimple(consultaO.getFechaHasta());
        try{
            if(tipoSolicitud.equals("mSolicitudes")){ //mis solicitudes
                consultaO.toString();
                String grupo = consultaO.getGrupo();
                String estado = consultaO.getEstado();
                if(grupos.contains(grupo) && !estados.contains(estado))
                    listaSolicitudAyuda = solicitudDao.buscarPorGrupo(grupo, usuario.getIdUsuario(), fechaD, fechaH);
                else if(!grupos.contains(grupo) && estados.contains(estado))
                    listaSolicitudAyuda = solicitudDao.buscarPorEstado(estado, usuario.getIdUsuario(), fechaD, fechaH);
                else if(grupos.contains(grupo) && estados.contains(estado)){
                    listaSolicitudAyuda = solicitudDao.obtenerElementos(usuario.getIdUsuario(), grupo, estado, fechaD, fechaH);
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
            else if(tipoSolicitud.equals("tSolicitudes")){ //solicitudes realizadas
                String estadoAyuda;
                Integer idTecnico = consultaO.getIdTecnico();
                Usuario usuarioTecnico = usuarioDao.obtenerElemento(idTecnico);
                if(estados.contains(consultaO.getEstado()))
                    estadoAyuda = consultaO.getEstado();
                else 
                    estadoAyuda = "asignada-reevaluar-finalizada";
                listaSolicitudAyuda = solicitudDao.cargarSolicitudesTecnico(usuarioTecnico.getIdUsuario(), estadoAyuda, fechaD, fechaH);
                if(listaSolicitudAyuda != null){
                    for(SolicitudAyuda lista : listaSolicitudAyuda){
                        if(!listaIds.contains(lista.getId().getIdSolicitud())){
                            listaIds.add(lista.getId().getIdSolicitud());
                            String userSolicitaAyuda = lista.getUsuarioByIdUserSolicitaAyuda().getNombre() +" "+ lista.getUsuarioByIdUserSolicitaAyuda().getApellido();
                            if(lista.getFechaInicioTecnico() != null)
                                    fechaIT = String.valueOf(dateFormat.format(lista.getFechaInicioTecnico()));
                            else
                                fechaIT = "";
                            if(lista.getFechaFin() != null)
                                    fechaF = String.valueOf(dateFormat.format(lista.getFechaFin()));
                            else
                                fechaF = "";
                            if(lista.getFechaFinTecnico() != null)
                                    fechaFT = String.valueOf(dateFormat.format(lista.getFechaFinTecnico()));
                            else
                                fechaFT = "";
                            if(lista.getMensajeUserTecnico() != null)
                                    descripT = lista.getMensajeUserTecnico();
                            else
                                descripT = "";
                            listaTabla.add(
                                new SolicitudTabla(
                                        lista.getId().getIdSolicitud(), lista.getDescripcion(), descripT,userSolicitaAyuda,
                                        String.valueOf(dateFormat.format(lista.getFechaInicio())),
                                        fechaF, fechaIT, fechaFT, lista.getEstadoSolicitudTecnico(), lista.getEstadoSolicitud()
                                )); 
                        }
                    }
                }
                respo = new ServiceResponse<>("success",listaTabla);
            }
            else if(tipoSolicitud.equals("uSolicitudes")){ //solicitudes realizadas
                String estadoAyuda;
                Integer idUsuario = consultaO.getIdUsuarioS();
                Usuario usuarioSolicitaA = usuarioDao.obtenerElemento(idUsuario);
                String grupoId = usuario.getRol().split("_")[1];
                if(estados.contains(consultaO.getEstado()))
                    estadoAyuda = consultaO.getEstado();
                else 
                    estadoAyuda = "pendiente-asignada-reevaluar-finalizada";
                listaSolicitudAyuda = solicitudDao.obtenerElementos(usuarioSolicitaA.getIdUsuario(), grupoId, estadoAyuda, fechaD, fechaH);
                if(listaSolicitudAyuda != null){
                    for(SolicitudAyuda lista : listaSolicitudAyuda){
                         if(!listaIds.contains(lista.getId().getIdSolicitud())){
                            listaIds.add(lista.getId().getIdSolicitud());
                            String userSolicitaAyuda = usuarioSolicitaA.getNombre() +" "+ usuarioSolicitaA.getApellido();
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
    
    /***
     * 
     * @param request
     * @param response
     * @return un modelo de la vista de consultar solicitudes escogida en el sidebar
     */
    @RequestMapping(value = { "/consultarSolicitud" }, method = RequestMethod.GET)
    public ModelAndView consultarSolicitud(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        String grupoId = usuario.getRol().split("_")[1];
        List<Usuario> listaTecnicos = usuarioDao.obtenerElementos(grupoId);
        List<Usuario> listaUsuarios = usuarioDao.obtenerUsuarios();
        model.addObject("listarTecnicoCS", listaTecnicos);
        model.addObject("listaUsuariosSA", listaUsuarios);
        datosUsuario();
        model.addObject("viewMain","consultaSolicitudesAdmin");
        model.setViewName("menuUsuario");
        return model;
    }
    
    /***
     * 
     * @param request
     * @param response
     * @return retorna la vista del dashboard técnico por el usuario
     */
    @RequestMapping(value = { "/consultarDashboardTecnico" }, method = RequestMethod.GET)
    public ModelAndView dashboardTecnico(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        model.addObject("viewMain","dashboardTecnico");
        model.setViewName("menuUsuario");
        return model;
    }
    
    @RequestMapping(value = { "/consultarDashboardEncuesta" }, method = RequestMethod.GET)
    public ModelAndView dashboardEncuesta(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        model.addObject("viewMain","dashboardEncuesta");
        model.setViewName("menuUsuario");
        return model;
    }
    
    @RequestMapping(value = { "/consultarDashboardUsuario" }, method = RequestMethod.GET)
    public ModelAndView dashboardUsuario(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        String grupoId = usuario.getRol().split("_")[1];
        List<Departamento> listaDepartamentos = departamentoDao.obtenerElementos();
        List<TipoGrupo> listaTipos = tipoDao.obtenerElementos(grupoId);
        model.addObject("listarDepartamento",listaDepartamentos);
        model.addObject("listarTiposCS",listaTipos);
        model.addObject("viewMain","dashboardUsuario");
        model.setViewName("menuUsuario");
        return model;
    }
    
    /***
     * asigna al modelo datos del usuario como nombre, correo, en el navbar
     */
    private void datosUsuario(){
        String rol = usuario.getRol().split("_")[0];
        String tipo = usuario.getRol().split("_")[1];
        model.addObject("rol",rol);
        model.addObject("tipoAdmin",tipo);
        model.addObject("tipoRolU",rol);
        model.addObject("username", usuario.getNombre() + " " + usuario.getApellido());
        model.addObject("usuario", usuario.getUsuario());
        model.addObject("correo", usuario.getCorreo());
    }
}
