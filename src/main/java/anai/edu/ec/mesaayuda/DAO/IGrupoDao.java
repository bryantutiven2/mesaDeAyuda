
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.Grupo;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface IGrupoDao{
    Boolean insertar(Grupo o);
    Boolean actualizar(Grupo o);
    Boolean eliminar(Grupo o);
    List<Grupo> obtenerElementos();
    Grupo obtenerElemento(String id);
}
