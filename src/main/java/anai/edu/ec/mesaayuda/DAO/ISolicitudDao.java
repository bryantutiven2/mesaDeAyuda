
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
    List<SolicitudAyuda> nuevasSolicitudes(String grupo, String estado);
    List<SolicitudAyuda> obtenerElementos(Integer idUser, String idGrupo, String estado);
    SolicitudAyuda obtenerElemento(SolicitudAyudaId id);
    Integer generarIdSolicitud();
    List<SolicitudAyuda> buscarPorGrupo(String grupo, Integer idUser);
    List<SolicitudAyuda> buscarPorEstado(String estado, Integer idUser);
    List<SolicitudAyuda> cargarSolicitudesTecnico(Integer idUser, String estado);
    List<SolicitudAyuda> solicitudesDashboardTecnico(String idGrupo);
    List<SolicitudAyuda> obtenerSolicitudEncuesta(Integer idUserSA);
    SolicitudAyuda obtenerSolicitudId(Integer id);
}
