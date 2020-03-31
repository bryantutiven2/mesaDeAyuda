
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.Subtipo;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface ISubtipoDao{
    Boolean insertar(Subtipo o);
    Boolean actualizar(Subtipo o);
    Boolean eliminar(Subtipo o);
    List<Subtipo> obtenerElementos(String grupo);
    Subtipo obtenerElemento(Integer id);
}
