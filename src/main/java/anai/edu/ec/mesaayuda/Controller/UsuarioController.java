
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
    private Boolean retornoSolicitud = null;
    private Date fechaInicio;
    private String retornoVista = null;
    private ModelAndView model = new ModelAndView();
    private Usuario usuario;
    private List<SolicitudAyuda> listaSolicitudAyuda;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    @RequestMapping(value = { "/crearSolicitud" }, method = RequestMethod.GET)
    public ModelAndView crearSolicitud(HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        model.addObject("viewMain","crearSolicitud");
        model.setViewName("menuUsuario");
        return model;
    }
    
    @RequestMapping(value = { "/enviarSolicitud" }, method = RequestMethod.POST)
    public ModelAndView enviarSolicitud(HttpServletRequest request, HttpServletResponse response){
        try{
            String idgrupo = request.getParameter("grupo");
            String descripcion = request.getParameter("descripcion");
            Integer nvez = Integer.parseInt(request.getParameter("nvez"));
            if(nvez>1)
                id_nvez = request.getParameter("idsnvez");
            else
                id_nvez = "null";
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
            retornoSolicitud = solicitudDao.insertar(objetoSolicitud);
            //if(retornoSolicitud.equals(true))
                retornoVista = "menuUsuario";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        datosUsuario();
        model.addObject("viewMain","crearSolicitud");
        model.setViewName(retornoVista);
        return model;
    }
    @PostMapping( "/solicitudes" )
    public ResponseEntity<Object> solicitudes(@RequestBody ConsultaObjeto consultaO,
                                                HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        ServiceResponse<List<SolicitudTabla>> respo = null;
        usuario = obtenerSessionUsuario(request, response);
        String idGrupo = consultaO.getGrupo();
        String tipoG;
        String estado = "finalizada";
        try{
            listaSolicitudAyuda = solicitudDao.obtenerElementos(usuario.getIdUsuario(), idGrupo, estado);
            if(listaSolicitudAyuda != null){
                for(SolicitudAyuda lista : listaSolicitudAyuda){
                    if(lista.getTipoGrupo() != null){
                        tipoG = lista.getTipoGrupo().getNombreTipo();
                        listaTabla.add(
                        new SolicitudTabla(lista.getId().getIdSolicitud(), lista.getGrupo().getNombreGrupo(),
                                tipoG, lista.getDescripcion()));
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
    
    @PostMapping( "/filtroConsultarSolicitud" )
    public ResponseEntity<Object> filtroConsultarSolicitud(@RequestBody ConsultaObjeto consultaO,
                                                HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        ServiceResponse<List<SolicitudTabla>> respo = null;
        HashSet<Integer> listaIds = new HashSet<Integer>();
        usuario = obtenerSessionUsuario(request, response);
        String fechaF, userTecnico, tipoG;
        String grupo = consultaO.getGrupo();
        String estado = consultaO.getEstado();
        try{
            
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
        model.addObject("listaConsultaSolicitudes",null);
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
