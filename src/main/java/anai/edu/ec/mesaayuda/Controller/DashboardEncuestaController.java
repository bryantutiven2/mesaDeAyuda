
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.IEncuestaDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.EncuestaImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.Encuesta;
import anai.edu.ec.mesaayuda.Entity.EncuestaTabla;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.ServiceResponse;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bryan
 */
@Controller
@RequestMapping( "/dashboardEncuesta" )
@RestController
public class DashboardEncuestaController {
    private Usuario usuario;
    private IEncuestaDao encuestaDao = new EncuestaImpl();
    private IUsuarioDao usuarioDao = new UsuarioImpl();
    
    @GetMapping( "/cargarEncuestas")
    public ResponseEntity<Object> cargarEncuestas(HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<EncuestaTabla>> respo = null;
        List<EncuestaTabla> listaTabla = new ArrayList<>();
        usuario = obtenerSessionUsuario(request, response);
        String grupo = usuario.getRol().split("_")[1];
        try{
            List<Encuesta> listaEncuestas = encuestaDao.obtenerElementos();
            for(Encuesta e : listaEncuestas){
                listaTabla.add(new EncuestaTabla(
                                e.getIdEncuesta(), e.getNombre(), e.getDescripcion(), e.getCodigoEmbebido(), 
                                e.getCodigoRegistro(), e.getEstadoBorrado()
                                ));
            }
            if(listaEncuestas != null)
                respo = new ServiceResponse<>("success",listaTabla);
            else
                respo = new ServiceResponse<>("vacio",listaTabla);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);   
    }
    
    @PostMapping( "/crearEncuesta" )
    public ResponseEntity<Object> crearEncuesta(@RequestBody EncuestaTabla encuestaO,
                                    HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<Encuesta>> respo = null;
        String mensaje = "error";
        try{
            String nombre = encuestaO.getNombre();
            String descripcion = encuestaO.getDescripcion();
            String codEmbebido = encuestaO.getCodigoEmbebido();
            String codRegistro = encuestaO.getCodigoRegistro();
            Integer estado = encuestaO.getEstadoBorrado();
            
            if(nombre != null && descripcion != null && codEmbebido != null && codRegistro != null){
                Encuesta encuestaObjeto = new Encuesta();
                encuestaObjeto.setNombre(nombre);
                encuestaObjeto.setDescripcion(descripcion);
                encuestaObjeto.setCodigoEmbebido(codEmbebido);
                encuestaObjeto.setCodigoRegistro(codRegistro);
                encuestaObjeto.setEstadoBorrado(estado);
                
                Boolean retorno = encuestaDao.insertar(encuestaObjeto);
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
    
    @PostMapping( "/actualizarEncuesta" )
    public ResponseEntity<Object> actualizarEncuesta(@RequestBody EncuestaTabla encuestaO,
                                    HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<Encuesta>> respo = null;
        String mensaje = "error";
        try{
            Integer idEncuesta = encuestaO.getIdEncuesta();
            String nombre = encuestaO.getNombre();
            String descripcion = encuestaO.getDescripcion();
            String codEmbebido = encuestaO.getCodigoEmbebido();
            String codRegistro = encuestaO.getCodigoRegistro();
            Integer estado = encuestaO.getEstadoBorrado();
            
            if(idEncuesta != null && nombre != null && descripcion != null && codEmbebido != null && codRegistro != null){
                Encuesta encuestaObjeto = new Encuesta();
                encuestaObjeto.setIdEncuesta(idEncuesta);
                encuestaObjeto.setNombre(nombre);
                encuestaObjeto.setDescripcion(descripcion);
                encuestaObjeto.setCodigoEmbebido(codEmbebido);
                encuestaObjeto.setCodigoRegistro(codRegistro);
                encuestaObjeto.setEstadoBorrado(estado);
                
                Boolean retorno = encuestaDao.actualizar(encuestaObjeto);
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
