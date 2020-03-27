
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;

/**
 *
 * @author bryan
 */
public interface ISolicitudDao extends DAOSolicitud <SolicitudAyuda, SolicitudAyudaId>{
    Integer generarIdSolicitud();
}
