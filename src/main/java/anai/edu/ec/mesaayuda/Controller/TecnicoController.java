
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.ServiceResponse;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    
    @GetMapping( "/cargarSolicitudes")
    public ResponseEntity<Object> cargarSolicitudes(HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<SolicitudTabla>> respo = null;
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        usuario = obtenerSessionUsuario(request, response);
        String estado = "asignada";
        try{
            listaSolicitudAyuda = solicitudDao.cargarSolicitudesTecnico(usuario.getIdUsuario(), estado);
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
                System.out.println("cargarSolicitudes");
            }
            respo = new ServiceResponse<>("success",listaTabla);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);   
    }
    
    @PostMapping( "/actualizarSolicitud" )
    public ResponseEntity<Object> actualizarSolicitudes(@RequestBody SolicitudTabla solicitudT,
                                                        HttpServletRequest request, HttpServletResponse response){
        String mensaje="";
        ServiceResponse<List<SolicitudTabla>> respo = null;
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        usuario = obtenerSessionUsuario(request, response);
        String grupoId = usuario.getRol().split("_")[1];
        try{
            SolicitudAyudaId solicitudId = new SolicitudAyudaId(solicitudT.getId(),grupoId);
            SolicitudAyuda objetoSolicitud = solicitudDao.obtenerElemento(solicitudId);
            String estadoSolicitudT = solicitudT.getEstadoSolicitud();
            if(estadoSolicitudT.equals("null")){
                objetoSolicitud.setEstadoSolicitudTecnico(solicitudT.getEstadoSolicitudTecnico());
                mensaje = "proceso";
            }
            else if(estadoSolicitudT.equals("finalizada") || estadoSolicitudT.equals("reevaluar")){
                objetoSolicitud.setEstadoSolicitudTecnico(solicitudT.getEstadoSolicitudTecnico());
                objetoSolicitud.setMensajeUserTecnico(solicitudT.getDescripcionTecnico());
                objetoSolicitud.setEstadoSolicitud(solicitudT.getEstadoSolicitud());
                objetoSolicitud.setEstadoSolicitudTecnico(solicitudT.getEstadoSolicitudTecnico());
                mensaje = "finalizado";
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
    
    @RequestMapping(value = { "/gestionarSolicitudes"}, method = RequestMethod.GET)
    public ModelAndView gestionarSolicitudes(HttpServletRequest request, HttpServletResponse response){
        usuario = obtenerSessionUsuario(request, response);
        datosUsuario();
        model.addObject("viewMain","cargarSolicitudesTecnico");
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
