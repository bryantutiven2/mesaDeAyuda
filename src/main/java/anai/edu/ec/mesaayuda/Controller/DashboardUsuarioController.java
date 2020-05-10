
package anai.edu.ec.mesaayuda.Controller;


import anai.edu.ec.mesaayuda.DAO.IDepartamentoDao;
import anai.edu.ec.mesaayuda.DAO.ITipoGrupoDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.DepartamentoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.TipoGrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.Departamento;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Entity.UsuarioTabla;
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
@RequestMapping( "/dashboardUsuario" )
@RestController
public class DashboardUsuarioController {
    private Usuario usuario;
    private IUsuarioDao usuarioDao = new UsuarioImpl();
    private ITipoGrupoDao tipoDao = new TipoGrupoImpl();
    private IDepartamentoDao departamentoDao = new DepartamentoImpl();
    
    @PostMapping( "/crearUsuario" )
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioTabla usuarioO,
                                    HttpServletRequest request, HttpServletResponse response){
        ServiceResponse<List<Usuario>> respo = null;
        String mensaje = "error";
        try{
            String nombre = usuarioO.getNombre();
            String apellido = usuarioO.getApellido();
            String username = usuarioO.getUsername();
            String password = usuarioO.getPassword();
            String email = usuarioO.getCorreo();
            Integer idDepartamento = usuarioO.getIdDepartamento();
            Integer idTipo = usuarioO.getIdTipo();
            String rol = usuarioO.getRol();
            
            if(idDepartamento != null){
                Usuario usuarioNuevo = new Usuario();
                usuarioNuevo.setNombre(nombre);
                usuarioNuevo.setApellido(apellido);
                usuarioNuevo.setUsuario(username);
                usuarioNuevo.setContrasena(password);
                usuarioNuevo.setCorreo(email);
                usuarioNuevo.setRol(rol);
                Departamento departament = departamentoDao.obtenerElemento(idDepartamento);
                if(departament != null)
                    usuarioNuevo.setDepartamento(departament);
                
                if(idTipo != null){
                    TipoGrupo tipo = tipoDao.obtenerElemento(idTipo);
                    usuarioNuevo.setTipoGrupo(tipo);
                }
                
                Boolean retorno = usuarioDao.insertar(usuarioNuevo);
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
