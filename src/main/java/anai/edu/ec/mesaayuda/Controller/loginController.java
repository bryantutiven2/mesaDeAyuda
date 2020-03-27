
package anai.edu.ec.mesaayuda.Controller;


import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.crearSessionUsuario;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {
    private String user = null;
    private String pass = null;
    private IUsuarioDao usuarioDao = new UsuarioImpl();
    private Boolean retornoLogin = null;
    private String retornoVista = null;
//    @RequestMapping(value = "/")
//    public ModelAndView mostrarLogin() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("login");
//        return mv;
//    }
    
    @RequestMapping(value = { "/", "login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                    @RequestParam(value = "logout", required = false) String logout) {
        HibernateUtil.getSessionFactory();
        ModelAndView model = new ModelAndView();
        if(error != null){
            model.addObject("error", "Invalid username and password!");
        }
        else if(logout != null){
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        return model;
    }
    
    @RequestMapping(value = { "/menuUsuario" }, method = RequestMethod.POST)
    public ModelAndView mostrarMenuUsuario(HttpServletRequest request, HttpServletResponse response){
        
        ModelAndView model = new ModelAndView();
        user = request.getParameter("usuario");
        pass = request.getParameter("contrasena");
        Usuario usuario = usuarioDao.verificarUsuario(user, pass);
        if(usuario != null){
            crearSessionUsuario(request, response, usuario);
            String rol = usuario.getRol();
            if( rol.equals("admin_sist") || rol.equals("tecnico_sist") || rol.equals("general_acad") )
                retornoVista = "menuUsuario";
            model.addObject("rol",rol);
            model.addObject("username", usuario.getNombre() + " " + usuario.getApellido());
            model.addObject("usuario", usuario.getUsuario());
            model.addObject("correo", usuario.getCorreo());
        }
        else if(usuario == null){
            model.addObject("error", "Usuario o contrasena invalido!");
            retornoVista = "login";
        }
        model.addObject("viewMain","crearSolicitud");
        model.setViewName(retornoVista);
        return model;
    }
    
}
