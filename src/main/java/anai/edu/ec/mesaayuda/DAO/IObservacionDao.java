
package anai.edu.ec.mesaayuda.DAO;


import anai.edu.ec.mesaayuda.Entity.Observacion;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface IObservacionDao {
    Boolean insertar(Observacion o);
    List<Observacion> obtenerElementos(Integer id);
}
