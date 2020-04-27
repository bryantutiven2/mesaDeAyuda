
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.IEncuestaDao;
import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.EncuestaImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.Entity.Encuesta;
import anai.edu.ec.mesaayuda.Entity.EncuestaTabla;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
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
@RequestMapping( "/notificacionEncuesta" )
@RestController
public class EncuestaController {
    private Usuario usuario;
    private IEncuestaDao encuestaDao = new EncuestaImpl();
    private ISolicitudDao solicitudDao = new SolicitudImpl();
    
    @GetMapping( "/pendiente")
    public ResponseEntity<Object> cargarPEncuestas(HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<EncuestaTabla>> respo = null;
        List<EncuestaTabla> listaTabla = new ArrayList<>();
        usuario = obtenerSessionUsuario(request, response);
        Integer idUsuario = usuario.getIdUsuario();
        try{
            List<SolicitudAyuda> listaSolicitud = solicitudDao.obtenerSolicitudEncuesta(idUsuario);
            for(SolicitudAyuda sa: listaSolicitud){
                String idSolicitud = String.valueOf(sa.getId().getIdGrupo()+"-"+sa.getId().getIdSolicitud());
                Integer idEncuesta = sa.getEncuesta().getIdEncuesta();
                Encuesta survay = encuestaDao.obtenerElemento(idEncuesta);
                listaTabla.add(new EncuestaTabla(
                                survay.getIdEncuesta(), survay.getNombre(), survay.getDescripcion(),
                                survay.getCodigoEmbebido(), survay.getCodigoRegistro(),survay.getEstadoBorrado(),idSolicitud));
            }
            
            if(!listaTabla.isEmpty())
                respo = new ServiceResponse<>("success",listaTabla);
            else
                respo = new ServiceResponse<>("vacio",listaTabla);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return new ResponseEntity<Object>(respo, HttpStatus.OK);   
    }
    
    @PostMapping( "/actualizarNEncuesta" )
    public ResponseEntity<Object> actualizarNEncuesta(@RequestBody EncuestaTabla encuestaO,
                                    HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<Encuesta>> respo = null;
        String mensaje = "error";
        try{
            String idSolicitud = encuestaO.getIdSolicitud();
            Integer estadoS = encuestaO.getEstadoBorrado();
            
            if(idSolicitud != "" && estadoS != null){
                String grupo = idSolicitud.split("-")[0];
                Integer idS = Integer.parseInt(idSolicitud.split("-")[1]);
                SolicitudAyudaId sID = new SolicitudAyudaId();
                sID.setIdGrupo(grupo);
                sID.setIdSolicitud(idS);
                SolicitudAyuda solicitudObjeto = solicitudDao.obtenerElemento(sID);
                solicitudObjeto.setEstadoEncuesta(estadoS);
                Boolean retorno = solicitudDao.actualizar(solicitudObjeto);
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
