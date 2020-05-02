
package anai.edu.ec.mesaayuda.Controller;


import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DAO.ISubtipoDao;
import anai.edu.ec.mesaayuda.DAO.ITipoGrupoDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SubtipoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.TipoGrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.Subtipo;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.SessionUsuario;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.crearSessionUsuario;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private String user = null;
    private String pass = null;
    private Usuario usuario;
    private ISubtipoDao subtipoDao = new SubtipoImpl();
    private ITipoGrupoDao tipoDao = new TipoGrupoImpl();
    private ISolicitudDao solicitudDao = new SolicitudImpl();
    private List<SolicitudAyuda> listaSolicitudAyuda;
    private IUsuarioDao usuarioDao = new UsuarioImpl();
    private Boolean retornoLogin = null;
    private String retornoVista = null;
    private ModelAndView model = new ModelAndView();
    
    /***
     * 
     * @param request
     * @param response
     * @return el modelo con la vista de login.jsp
     */
    @RequestMapping(value = { "/", "login"}, method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        HibernateUtil.getSessionFactory();
        Usuario usuario = SessionUsuario.obtenerSessionUsuario(request, response);
        if(usuario != null){
            String rol = usuario.getRol();
            if(rol.equals("admin_sist")){
                AdminController adminModel = new AdminController();
                model = adminModel.dashboardTecnico(request, response);
            }
            else if(rol.equals("general_acad")){
                UsuarioController usuarioModel = new UsuarioController();
                model = usuarioModel.crearSolicitud(request, response);
            }
            else if(rol.equals("tecnico_sist")){
                UsuarioController usuarioModel = new UsuarioController();
                model = usuarioModel.crearSolicitud(request, response);
            }
        }
        else{
            model.setViewName("login");
        }
        return model;
    }
    
    /***
     * 
     * @param request
     * @param response
     * @return la vista login, cerrando la session del usuario en el servidor
     */
    @RequestMapping(value = { "logout" }, method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        SessionUsuario.eliminarSessionUsuario(request, response);
        model.setViewName("login");
        return model;
    }
    
    /***
     * 
     * @param request
     * @param response
     * @return el modelo de la vista dependiendo del rol del usuario, ya sea este general, técnico, admin
     */
    @RequestMapping(value = { "/menuUsuario" }, method = RequestMethod.POST)
    public ModelAndView mostrarMenuUsuario(HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        user = request.getParameter("usuario");
        pass = request.getParameter("contrasena");
        usuario = usuarioDao.verificarUsuario(user, pass);
        if(usuario != null){
            crearSessionUsuario(request, response, usuario);
            String rol = usuario.getRol();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            if(rol.equals("admin_sist")){
                AdminController adminModel = new AdminController();
                model = adminModel.dashboardTecnico(request, response);
            }
            else if(rol.equals("general_acad")){
                UsuarioController usuarioModel = new UsuarioController();
                model = usuarioModel.crearSolicitud(request, response);
            }
            else if(rol.equals("tecnico_sist")){
                UsuarioController usuarioModel = new UsuarioController();
                model = usuarioModel.crearSolicitud(request, response);
            }
        }
        else if(usuario == null){
            model.addObject("error", "Usuario o contrasena invalido!");
            model.setViewName("login");
        }
        return model;
    }
    
    
}
