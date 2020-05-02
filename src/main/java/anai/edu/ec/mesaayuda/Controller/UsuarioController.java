
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.IGrupoDao;
import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.GrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.Entity.ConsultaObjeto;
import anai.edu.ec.mesaayuda.Entity.Grupo;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.ServiceResponse;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import anai.edu.ec.mesaayuda.Service.fechaSolicitud;
import static anai.edu.ec.mesaayuda.Service.fechaSolicitud.obtenerFecha;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping( "/usuario" )
@RestController
public class UsuarioController {
    
    private ISolicitudDao solicitudDao = new SolicitudImpl();
    private IGrupoDao grupoDao = new GrupoImpl();
    private String id_nvez = null;
    private Date fechaInicio;
    private String retornoVista = null;
    private ModelAndView model = new ModelAndView();
    private Usuario usuario;
    private List<SolicitudAyuda> listaSolicitudAyuda;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    /***
     * 
     * @param request
     * @param response
     * @return la vista de crear solicitud
     */
    @RequestMapping(value = { "/crearSolicitud" }, method = RequestMethod.GET)
    public ModelAndView crearSolicitud(HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        model.addObject("viewMain","crearSolicitud");
        model.setViewName("menuUsuario");
        return model;
    }
    
    /***
     * 
     * @param solicitudT objeto que contiene datos para crear la solciitud como descripción, grupo, número de vez
     * de solciitud, ids de anteriores ayudas
     * @param request
     * @param response
     * @return un string de éxito o error según la transation realizada
     */
    @PostMapping( "/enviarSolicitud" )
    public ResponseEntity<Object> enviarSolicitud(@RequestBody SolicitudTabla solicitudT,
                                                HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        ServiceResponse<List<SolicitudTabla>> respo = null;
        usuario = obtenerSessionUsuario(request, response);
        String idgrupo = solicitudT.getGrupo();
        String descripcion = solicitudT.getDescripcion();
        Integer nvez = solicitudT.getN_vez();
        if(nvez>1)
            id_nvez = solicitudT.getIds_n_vez();
        else
            id_nvez = "null";
        try{
            Grupo grupo = grupoDao.obtenerElemento(idgrupo);
            Integer idSolicitud = solicitudDao.generarIdSolicitud();
            SolicitudAyudaId solicitudAyudaId = new SolicitudAyudaId(idSolicitud, idgrupo);
            usuario = obtenerSessionUsuario(request, response);
            fechaInicio = obtenerFecha();
            SolicitudAyuda objetoSolicitud = new SolicitudAyuda();
            objetoSolicitud.setId(solicitudAyudaId);
            objetoSolicitud.setDescripcion(descripcion);
            objetoSolicitud.setAyudaNVez(nvez);
            objetoSolicitud.setIdsSolicitudNVez(id_nvez);
            objetoSolicitud.setEstadoBorrado(0);
            objetoSolicitud.setEstadoSolicitud("pendiente");
            objetoSolicitud.setFechaInicio(fechaInicio);
            objetoSolicitud.setUsuarioByIdUserSolicitaAyuda(usuario);
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
     * 
     * @param consultaO objeto que contiene si quiere consultar solicitudes finalizadas dependiendo de un grupo
     * @param request
     * @param response
     * @return una lista de solicitudes
     */
    @PostMapping( "/solicitudes" )
    public ResponseEntity<Object> solicitudes(@RequestBody ConsultaObjeto consultaO,
                                                HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        HashSet<Integer> listaIds = new HashSet<>();
        ServiceResponse<List<SolicitudTabla>> respo = null;
        usuario = obtenerSessionUsuario(request, response);
        String idGrupo = consultaO.getGrupo();
        String tipoG;
        String estado = "finalizada";
        try{
            listaSolicitudAyuda = solicitudDao.obtenerElementos(usuario.getIdUsuario(), idGrupo, estado);
            if(listaSolicitudAyuda != null){
                for(SolicitudAyuda lista : listaSolicitudAyuda){
                    if(!listaIds.contains(lista.getId().getIdSolicitud())){
                       if(lista.getTipoGrupo() != null){
                            tipoG = lista.getTipoGrupo().getNombreTipo();
                            listaTabla.add(
                            new SolicitudTabla(lista.getId().getIdSolicitud(), lista.getGrupo().getNombreGrupo(),
                                    tipoG, lista.getDescripcion()));
                        } 
                    }
                }
                respo = new ServiceResponse<>("success",listaTabla);
            }
            else
                respo = new ServiceResponse<>("success",null);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        
        return new ResponseEntity<Object>(respo, HttpStatus.OK);
    }
    
    /***
     * 
     * @param consultaO objeto de datos que se requieren obtener, hacer un filtro total de sistemas, consultar por estado,
     * grupo
     * @param request
     * @param response
     * @return lista de Solicitudes
     */
    @PostMapping( "/filtroConsultarSolicitud" )
    public ResponseEntity<Object> filtroConsultarSolicitud(@RequestBody ConsultaObjeto consultaO,
                                                HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        ServiceResponse<List<SolicitudTabla>> respo = null;
        HashSet<Integer> listaIds = new HashSet<>();
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
        String grupo = consultaO.getGrupo();
        String estado = consultaO.getEstado();
        try{
            if(grupos.contains(grupo) && !estados.contains(estado))
                listaSolicitudAyuda = solicitudDao.buscarPorGrupo(grupo, usuario.getIdUsuario());
            else if(!grupos.contains(grupo) && estados.contains(estado))
                listaSolicitudAyuda = solicitudDao.buscarPorEstado(estado, usuario.getIdUsuario());
            else if(grupos.contains(grupo) && estados.contains(estado)){
                listaSolicitudAyuda = solicitudDao.obtenerElementos(usuario.getIdUsuario(), grupo, estado);
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
     * @return view de consultarSolicitudes.jsp
     */
    @RequestMapping(value = { "/consultarSolicitud" }, method = RequestMethod.GET)
    public ModelAndView consultarSolicitud(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        model.addObject("listaConsultaSolicitudes",null);
        model.addObject("viewMain","consultaSolicitudes");
        model.setViewName("menuUsuario");
        return model;
    }
    
    /***
     * Envía junto al modelo datos del usuario que se usaran en el navbar
     */
    private void datosUsuario(){
        String rol = usuario.getRol();
        model.addObject("rol",rol);
        model.addObject("username", usuario.getNombre() + " " + usuario.getApellido());
        model.addObject("usuario", usuario.getUsuario());
        model.addObject("correo", usuario.getCorreo());
    }
}
