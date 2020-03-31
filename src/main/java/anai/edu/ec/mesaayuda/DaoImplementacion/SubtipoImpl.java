
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.ISubtipoDao;
import anai.edu.ec.mesaayuda.Entity.Subtipo;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author bryan
 */
public class SubtipoImpl implements ISubtipoDao {
    private String select = "from Subtipo st join fetch st.tipoGrupo tp join fetch tp.grupo gru where gru.idGrupo= :grupo";
    private String selectId = "from Subtipo s where s.idSubtipo=:id";
    
    @Override
    public Boolean insertar(Subtipo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean actualizar(Subtipo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(Subtipo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Subtipo> obtenerElementos(String grupo) {
        List<Subtipo> listaSubtipos = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(select, Subtipo.class);
            query.setParameter("grupo", grupo);
            listaSubtipos = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaSubtipos;
    }

    @Override
    public Subtipo obtenerElemento(Integer id) {
        Subtipo subtipo = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectId, Subtipo.class);
            query.setParameter("id", id);
            subtipo = (Subtipo)query.uniqueResult();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return subtipo;
    }
    
}
