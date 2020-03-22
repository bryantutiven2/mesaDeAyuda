
package anai.edu.ec.mesaayuda.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {
    /***
     * 
     * @return una vista para ingresar usuario y contraseña 
     */
    @RequestMapping(value = "/")
    public ModelAndView mostrarLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
}
