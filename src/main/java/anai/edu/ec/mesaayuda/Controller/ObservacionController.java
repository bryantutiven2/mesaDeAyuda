
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.IObservacionDao;
import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.ObservacionImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.Observacion;
import anai.edu.ec.mesaayuda.Entity.ObservacionTabla;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.ServiceResponse;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import anai.edu.ec.mesaayuda.Service.fechaSolicitud;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bryan
 */
@Controller
@RequestMapping( "/observacion" )
@RestController
public class ObservacionController {
    private IUsuarioDao usuarioDao = new UsuarioImpl();
    private IObservacionDao observacionDao = new ObservacionImpl();
    private ISolicitudDao solicitudDao = new SolicitudImpl();
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    @PostMapping( "/cargarObservaciones")
    public ResponseEntity<Object> cargarEncuestas(@RequestBody ObservacionTabla observacionO,
                                                HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<ObservacionTabla>> respo = null;
        List<ObservacionTabla> listaTabla = new ArrayList<>();
        try{
            Integer idSolicitud = observacionO.getId();
            List<Observacion> listaObservaciones = observacionDao.obtenerElementos(idSolicitud);
            for(Observacion o : listaObservaciones){
                String usuarioNombre = o.getUsuario().getNombre() + " " + o.getUsuario().getApellido();
                ObservacionTabla mensajeO = new ObservacionTabla();
                mensajeO.setUsuario(usuarioNombre);
                mensajeO.setMensaje(o.getMensaje());
                mensajeO.setFecha(dateFormat.format(o.getFechaMensaje()));
                listaTabla.add(mensajeO);
            }
            if(listaObservaciones != null)
                respo = new ServiceResponse<>("success",listaTabla);
            else
                respo = new ServiceResponse<>("vacio",listaTabla);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);   
    }
    
    @PostMapping( "/crearObservacion" )
    public ResponseEntity<Object> crearEncuesta(@RequestBody ObservacionTabla observacionO,
                                    HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<ObservacionTabla>> respo = null;
        String mensaje = "error";
        try{
            String mObservacion = observacionO.getMensaje();
            Usuario usuario = obtenerSessionUsuario(request, response);
            Date fechaO = fechaSolicitud.obtenerFecha();
            SolicitudAyuda solicitudO = solicitudDao.obtenerSolicitudId(observacionO.getId());
            if(mObservacion != null && usuario != null){
                Observacion observacionObjeto = new Observacion();
                observacionObjeto.setMensaje(mObservacion);
                observacionObjeto.setUsuario(usuario);
                observacionObjeto.setFechaMensaje(fechaO);
                observacionObjeto.setSolicitudAyuda(solicitudO);
                Boolean retorno = observacionDao.insertar(observacionObjeto);
                if(retorno == true)
                    mensaje = "exito";
                else if(retorno == false) 
                    mensaje = "error";
            }
            respo = new ServiceResponse<>(mensaje);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);
    }
}
