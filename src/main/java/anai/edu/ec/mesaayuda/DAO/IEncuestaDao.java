
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.Encuesta;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface IEncuestaDao {
    Boolean insertar(Encuesta o);
    Boolean actualizar(Encuesta o);
    Boolean eliminar(Encuesta o);
    List<Encuesta> obtenerElementos();
    Encuesta obtenerElemento(Encuesta id);
}
