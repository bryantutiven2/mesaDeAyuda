
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.Usuario;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface IUsuarioDao extends DAO<Usuario, Integer>{
    List<Usuario> obtenerElementosUtp();
    Usuario obtenerElementoUtp(Integer id);
    Usuario verificarUsuario(String user, String password);
}
