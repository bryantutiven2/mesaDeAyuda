
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;

/**
 *
 * @author bryan
 */
public interface ISolicitudDao extends DAO <SolicitudAyuda, SolicitudAyudaId>{
    Integer generarIdSolicitud();
}
