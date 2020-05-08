
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.IObservacionDao;
import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DAO.ISubtipoDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.ObservacionImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SubtipoImpl;
import anai.edu.ec.mesaayuda.Entity.ConsultaObjeto;
import anai.edu.ec.mesaayuda.Entity.Observacion;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.Subtipo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.ServiceResponse;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import anai.edu.ec.mesaayuda.Service.fechaSolicitud;
import static anai.edu.ec.mesaayuda.Service.fechaSolicitud.obtenerFecha;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bryan
 */
@Controller
@RequestMapping( "tecnico" )
@RestController
public class TecnicoController {
    private ModelAndView model = new ModelAndView();
    private Usuario usuario;
    private ISolicitudDao solicitudDao = new SolicitudImpl();
    private List<SolicitudAyuda> listaSolicitudAyuda;
    private ISubtipoDao subtipoDao = new SubtipoImpl();
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private IObservacionDao observacionDao = new ObservacionImpl();
    
    /***
     * 
     * @param request
     * @param response
     * @return lista de solicitudes que se les asigno a un técnico
     */
    @GetMapping( "/cargarSolicitudes")
    public ResponseEntity<Object> cargarSolicitudes(HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<SolicitudTabla>> respo = null;
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        usuario = obtenerSessionUsuario(request, response);
        String estado = "asignada";
        try{
            //listaSolicitudAyuda = solicitudDao.cargarSolicitudesTecnico(usuario.getIdUsuario(), estado);
            if(listaSolicitudAyuda != null){
                for(SolicitudAyuda lista : listaSolicitudAyuda){
                    String userSolicitaAyuda = lista.getUsuarioByIdUserSolicitaAyuda().getNombre() +" "+ lista.getUsuarioByIdUserSolicitaAyuda().getApellido();
                   listaTabla.add(
                        new SolicitudTabla(
                                lista.getId().getIdSolicitud(), lista.getDescripcion(), userSolicitaAyuda,
                                String.valueOf(dateFormat.format(lista.getFechaInicio())),
                                String.valueOf(dateFormat.format(lista.getFechaFin())),
                                lista.getEstadoSolicitudTecnico()
                        )); 
                }
                respo = new ServiceResponse<>("success",listaTabla);
            }
            else{
                respo = new ServiceResponse<>("vacio",listaTabla);
            }
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);   
    }
    
    /***
     * 
     * @param solicitudT objeto que contiene datos para gestionar una solicitud que se le fue asignada, entre esta la
     * de comenzar una solicitud, mandar a reevaluar o finalizar la solicitud
     * @param request
     * @param response
     * @return un string de procesada, finalizada o error en uan solciitud
     */
    @PostMapping( "/actualizarSolicitud" )
    public ResponseEntity<Object> actualizarSolicitudes(@RequestBody SolicitudTabla solicitudT,
                                                        HttpServletRequest request, HttpServletResponse response){
        String mensaje="";
        ServiceResponse<List<SolicitudTabla>> respo = null;
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        usuario = obtenerSessionUsuario(request, response);
        String grupoId = usuario.getRol().split("_")[1];
        try{
            SolicitudAyudaId solicitudId = new SolicitudAyudaId(solicitudT.getId(),grupoId);
            SolicitudAyuda objetoSolicitud = solicitudDao.obtenerElemento(solicitudId);
            String estadoSolicitudT = solicitudT.getEstadoSolicitud();
            if(estadoSolicitudT.equals("null")){
                objetoSolicitud.setFechaInicioTecnico(obtenerFecha());
                objetoSolicitud.setEstadoSolicitudTecnico(solicitudT.getEstadoSolicitudTecnico());
                mensaje = "proceso";
            }
            else if(estadoSolicitudT.equals("finalizada") || estadoSolicitudT.equals("reevaluar")){
                objetoSolicitud.setFechaFinTecnico(obtenerFecha());
                objetoSolicitud.setEstadoSolicitudTecnico(solicitudT.getEstadoSolicitudTecnico());
                objetoSolicitud.setMensajeUserTecnico(solicitudT.getDescripcionTecnico());
                objetoSolicitud.setEstadoSolicitud(solicitudT.getEstadoSolicitud());
                if(!solicitudT.getIdSubtipo().equals("none")){
                    Subtipo subtipo = subtipoDao.obtenerElemento(Integer.parseInt(solicitudT.getIdSubtipo()));
                    objetoSolicitud.setSubtipo(subtipo);
                }
                
                objetoSolicitud.setEstadoSolicitudTecnico(solicitudT.getEstadoSolicitudTecnico());
                mensaje = "finalizado";
                
                /*insertar mensaje del tecnico como observacion*/
                String mObservacion = solicitudT.getDescripcionTecnico();
                Date fechaO = fechaSolicitud.obtenerFecha();
                SolicitudAyuda solicitudO = solicitudDao.obtenerSolicitudId(solicitudT.getId());
                Observacion observacionObjeto = new Observacion();
                observacionObjeto.setMensaje(mObservacion);
                observacionObjeto.setUsuario(usuario);
                observacionObjeto.setFechaMensaje(fechaO);
                observacionObjeto.setSolicitudAyuda(solicitudO);
                observacionDao.insertar(observacionObjeto);
                
            }
            Boolean actualizacion = solicitudDao.actualizar(objetoSolicitud);
            if(actualizacion == true){
                respo = new ServiceResponse<>(mensaje);
            }
            else{
                respo = new ServiceResponse<>("error");
            }
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);   
    }
    
    /***
     * 
     * @param consultaO objeto con datos necesario para filtrar una solicitud dependiendo de lo que el usuario necesita
     * @param request
     * @param response
     * @return lista de solicitudes
     */
    @PostMapping( "/filtroConsultarSolicitud" )
    public ResponseEntity<Object> filtroConsultarSolicitud(@RequestBody ConsultaObjeto consultaO,
                                                HttpServletRequest request, HttpServletResponse response){
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
        String fechaF, userTecnico, tipoG;
        String tipoSolicitud = consultaO.getTipoSolicitud();
        String grupo = consultaO.getGrupo();
        String estado = consultaO.getEstado();
        try{
            if(tipoSolicitud.equals("mSolicitudes")){ //mis solicitudes
                /*if(grupos.contains(grupo) && !estados.contains(estado))
                    listaSolicitudAyuda = solicitudDao.buscarPorGrupo(grupo, usuario.getIdUsuario());
                else if(!grupos.contains(grupo) && estados.contains(estado))
                    listaSolicitudAyuda = solicitudDao.buscarPorEstado(estado, usuario.getIdUsuario());
                else if(grupos.contains(grupo) && estados.contains(estado)){
                    listaSolicitudAyuda = solicitudDao.obtenerElementos(usuario.getIdUsuario(), grupo, estado);
                }*/
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
                //listaSolicitudAyuda = solicitudDao.cargarSolicitudesTecnico(usuario.getIdUsuario(), estadoAyuda);
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
    
    /***
     * 
     * @param request
     * @param response
     * @return view of cargarSolcitudesTecnico.jsp
     */
    @RequestMapping(value = { "/gestionarSolicitudes"}, method = RequestMethod.GET)
    public ModelAndView gestionarSolicitudes(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        List<Subtipo> listaSubtipos = subtipoDao.obtenerElementosPorTipo(usuario.getTipoGrupo().getIdTipo());
        datosUsuario();
        model.addObject("listarSubtipo_CS",listaSubtipos);
        model.addObject("viewMain","cargarSolicitudesTecnico");
        model.setViewName("menuUsuario");
        return model;
    }
    
    /***
     * 
     * @param request
     * @param response
     * @return view view con consultaSolicitudesTecnico.jsp
     */
    @RequestMapping(value = { "/consultarSolicitud" }, method = RequestMethod.GET)
    public ModelAndView consultarSolicitud(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        model.addObject("viewMain","consultaSolicitudesTecnico");
        model.setViewName("menuUsuario");
        return model;
    }
    
    /***
     * Datos necesario para mandar a la vista del navbar
     */
    private void datosUsuario(){
        String rol = usuario.getRol();
        model.addObject("rol",rol);
        model.addObject("username", usuario.getNombre() + " " + usuario.getApellido());
        model.addObject("usuario", usuario.getUsuario());
        model.addObject("correo", usuario.getCorreo());
    }
}
