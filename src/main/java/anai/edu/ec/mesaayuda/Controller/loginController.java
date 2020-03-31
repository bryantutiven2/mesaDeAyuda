
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
public class loginController {
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
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        user = request.getParameter("usuario");
        pass = request.getParameter("contrasena");
        usuario = usuarioDao.verificarUsuario(user, pass);
        if(usuario != null){
            crearSessionUsuario(request, response, usuario);
            String rol = usuario.getRol();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if( rol.equals("admin_sist") || rol.equals("tecnico_sist") || rol.equals("general_acad") ){
                if(rol.equals("admin_sist")){
                    String grupo = usuario.getRol().split("_")[1];
                    List<Usuario> listaTecnicos = usuarioDao.obtenerElementos(grupo);
                    List<Usuario> listaUsuarios = usuarioDao.obtenerUsuarios();
                    List<TipoGrupo> listaTipos = tipoDao.obtenerElementos(grupo);
                    List<Subtipo> listaSubtipos = subtipoDao.obtenerElementos(grupo);
                    model.addObject("idAdmin", usuario.getIdUsuario());
                    model.addObject("listarTiposCS",listaTipos);
                    model.addObject("listarTecnicoCS", listaTecnicos);
                    model.addObject("listaUsuariosSA", listaUsuarios);
                    model.addObject("listarSubtipo_CS",listaSubtipos);
                    model.addObject("viewMain","crearSolicitudAdmin");
                }
                else if(rol.equals("general_acad")){
                    String tipoG;
                    try{
                        Map<String, String> mapaSolicitud = new HashMap<>();
                        mapaSolicitud.put("idUserSolicitaAyuda", String.valueOf(usuario.getIdUsuario()));
                        listaSolicitudAyuda = solicitudDao.obtenerElementos(mapaSolicitud);
                        if(listaSolicitudAyuda != null){
                            for(SolicitudAyuda lista : listaSolicitudAyuda){
                                if(lista.getTipoGrupo() != null){
                                    tipoG = lista.getTipoGrupo().getNombreTipo();
                                    listaTabla.add(
                                    new SolicitudTabla(lista.getId().getIdSolicitud(), lista.getGrupo().getNombreGrupo(),
                                            tipoG, lista.getDescripcion()));
                                }
                            }
                            model.addObject("listaSolicitudesModal",listaTabla);
                        }
                    }
                    catch(Exception exc){
                        exc.printStackTrace();
                    }
                    model.addObject("viewMain","crearSolicitud");
                }
                retornoVista = "menuUsuario";
                model.addObject("rol",rol);
                model.addObject("username", usuario.getNombre() + " " + usuario.getApellido());
                model.addObject("usuario", usuario.getUsuario());
                model.addObject("correo", usuario.getCorreo());
            }
        }
        else if(usuario == null){
            model.addObject("error", "Usuario o contrasena invalido!");
            retornoVista = "login";
        }
        model.setViewName(retornoVista);
        return model;
    }
    
}
