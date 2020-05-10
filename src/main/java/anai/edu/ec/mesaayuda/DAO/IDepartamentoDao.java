
package anai.edu.ec.mesaayuda.DAO;

import anai.edu.ec.mesaayuda.Entity.Departamento;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface IDepartamentoDao{
    Boolean insertar(Departamento o);
    List<Departamento> obtenerElementos();
    Departamento obtenerElemento(Integer id);
}
