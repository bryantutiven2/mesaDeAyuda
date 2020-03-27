
package anai.edu.ec.mesaayuda.DAO;

import java.util.List;

/**
 *
 * @author bryan
 */
public interface DAOSolicitud<T, S> {
    Boolean insertar(T o);
    Boolean actualizar(T o);
    Boolean eliminar(T o);
    List<T> obtenerElementos(String g, String e);
    T obtenerElemento(S id);
}
