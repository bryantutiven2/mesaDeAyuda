
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bryan
 */
public interface ISolicitudDao{
    Boolean insertar(SolicitudAyuda o);
    Boolean actualizar(SolicitudAyuda o);
    Boolean eliminar(SolicitudAyuda o);
    List<SolicitudAyuda> obtenerElementos(Map<String, String> mapaSolicitud);
    SolicitudAyuda obtenerElemento(SolicitudAyudaId id);
    Integer generarIdSolicitud();
}
