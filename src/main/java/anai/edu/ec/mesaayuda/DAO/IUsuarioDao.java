
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
    List<Usuario> obtenerElementos(String grupo);
    Usuario obtenerElemento(Integer id);
    List<Usuario> obtenerElementosUtp();
    List<Usuario> obtenerUsuarios();
    Usuario obtenerElementoUtp(Integer id);
    Usuario verificarUsuario(String user, String password);
}
