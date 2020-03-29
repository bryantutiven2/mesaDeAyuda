
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface ITipoGrupoDao{
    Boolean insertar(TipoGrupo o);
    Boolean actualizar(TipoGrupo o);
    Boolean eliminar(TipoGrupo o);
    List<TipoGrupo> obtenerElementos(String grupo);
    TipoGrupo obtenerElemento(Integer id);
}
