
package anai.edu.ec.mesaayuda.DAO;

import java.util.List;

/**
 *
 * @author bryan
 */
public interface DAO<T, S> {
    Boolean insertar(T o);
    Boolean actualizar(T o);
    Boolean eliminar(T o);
    List<T> obtenerElementos();
    T obtenerElemento(S id);
}
