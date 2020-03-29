
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.Usuario;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface IUsuarioDao{
    
    Boolean insertar(Usuario o);
    Boolean actualizar(Usuario o);
    Boolean eliminar(Usuario o);
    List<Usuario> obtenerElementos(String rol);
    Usuario obtenerElemento(Integer id);
    List<Usuario> obtenerElementosUtp();
    Usuario obtenerElementoUtp(Integer id);
    Usuario verificarUsuario(String user, String password);
}
