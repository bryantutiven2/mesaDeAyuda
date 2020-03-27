
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.IGrupoDao;
import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.GrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.Entity.Grupo;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import anai.edu.ec.mesaayuda.Service.fechaSolicitud;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class usuarioController {
    
    private ISolicitudDao solicitudDao;
    private IGrupoDao grupoDao;
    private String id_nvez = null;
    private Boolean retornoSolicitud = null;
    private Date fechaInicio;
    private String retornoVista = null;
    private ModelAndView model;
    private Usuario usuario;
    
    @RequestMapping(value = { "/crearSolicitud" }, method = RequestMethod.GET)
    public ModelAndView crearSolicitud(HttpServletRequest request, HttpServletResponse response){
        model = new ModelAndView();
        usuario = obtenerSessionUsuario(request, response);
        evaluarUsuario();
        retornoVista = "menuUsuario";
        model.addObject("viewMain","crearSolicitud");
        model.setViewName(retornoVista);
        return model;
    }
    
    @RequestMapping(value = { "/enviarSolicitud" }, method = RequestMethod.POST)
    public ModelAndView enviarSolicitud(HttpServletRequest request, HttpServletResponse response){
        model = new ModelAndView();
        solicitudDao = new SolicitudImpl();
        grupoDao = new GrupoImpl();
        
        try{
            String idgrupo = request.getParameter("grupo");
            System.out.println(idgrupo);
            String descripcion = request.getParameter("descripcion");
            System.out.println(descripcion);
            Integer nvez = Integer.parseInt(request.getParameter("nvez"));
            System.out.println(nvez);
            if(nvez>1)
                id_nvez = request.getParameter("idsnvez");
            else
                id_nvez = "null";
            Grupo grupo = grupoDao.obtenerElemento(idgrupo);
            Integer idSolicitud = solicitudDao.generarIdSolicitud();
            SolicitudAyudaId solicitudAyudaId = new SolicitudAyudaId(idSolicitud, idgrupo);
            usuario = obtenerSessionUsuario(request, response);
            evaluarUsuario();
            fechaInicio = fechaSolicitud.obtenerFechaInicio();
            
            SolicitudAyuda objetoSolicitud = new SolicitudAyuda();
            objetoSolicitud.setId(solicitudAyudaId);
            objetoSolicitud.setDescripcion(descripcion);
            objetoSolicitud.setAyudaNVez(nvez);
            objetoSolicitud.setIdsSolicitudNVez(id_nvez);
            objetoSolicitud.setEstadoBorrado(1);
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
        model.addObject("viewMain","crearSolicitud");
        model.setViewName(retornoVista);
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
