
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
public class adminController {
    private ModelAndView model = new ModelAndView();
    private Usuario usuario;
    private ISolicitudDao solicitudDao;
    private List<SolicitudAyuda> listaSolicitudAyuda;
    
    @RequestMapping(value = { "gestionarNuevaSolicitud"}, method = RequestMethod.GET)
    public ModelAndView gestionarNuevaSolicitud(){
        
        return model;
    }
    
    
    @RequestMapping(value = { "nuevasSolicitudes"}, method = RequestMethod.GET)
    public ModelAndView nuevasSolicitudes(HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        solicitudDao = new SolicitudImpl();
        usuario = obtenerSessionUsuario(request, response);
        String grupo = usuario.getRol().split("_")[1];
        String estado = "pendiente";
        listaSolicitudAyuda = solicitudDao.obtenerElementos(grupo,estado);
        if(listaSolicitudAyuda != null){
            for(SolicitudAyuda lista : listaSolicitudAyuda){
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                listaTabla.add(
                        new SolicitudTabla(lista.getId().getIdSolicitud(), lista.getAyudaNVez(), lista.getIdsSolicitudNVez(),
                                lista.getDescripcion(),
                                lista.getUsuarioByIdUserSolicitaAyuda().getNombre()+" "+ lista.getUsuarioByIdUserSolicitaAyuda().getApellido(),
                                String.valueOf(dateFormat.format(lista.getFechaInicio()))));
            }
        }
        evaluarUsuario();
        model.addObject("listaNuevasSolicitudes",listaTabla);
        model.addObject("viewMain","solicitudesNuevasAdmin");
        model.setViewName("menuUsuario");
        return model;
    }
    
    private void evaluarUsuario(){
        
        String rol = usuario.getRol();
        model.addObject("rol",rol);
        model.addObject("username", usuario.getNombre() + " " + usuario.getApellido());
        model.addObject("usuario", usuario.getUsuario());
        model.addObject("correo", usuario.getCorreo());
    }
}
