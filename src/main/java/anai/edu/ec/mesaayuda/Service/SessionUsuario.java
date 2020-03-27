
package anai.edu.ec.mesaayuda.Service;

import anai.edu.ec.mesaayuda.Entity.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bryan
 */
public class SessionUsuario {

    public static final Boolean crearSessionUsuario(HttpServletRequest request, HttpServletResponse response, Usuario usuario) {
        Boolean retorno = null;
        HttpSession sessionUsuario = null;
        try{
            sessionUsuario = request.getSession();
            sessionUsuario.setAttribute("usuario", usuario);
            retorno = true;
        }
        catch(Exception e){
            retorno = false;
            e.printStackTrace();
        }
        return retorno;
    }

    public static final Usuario obtenerSessionUsuario(HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = null;
        HttpSession sessionUsuario = null;
        try{
            sessionUsuario = request.getSession();
            usuario = (Usuario) sessionUsuario.getAttribute("usuario");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    
    public static final void eliminarSessionUsuario(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessionUsuario = null;
        try{
            sessionUsuario = request.getSession();
            sessionUsuario.invalidate();
        }
        catch(Exception e){
           e.printStackTrace();
        }
    }
}
