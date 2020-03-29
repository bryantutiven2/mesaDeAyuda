
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.ITipoGrupoDao;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author bryan
 */
public class TipoGrupoImpl implements ITipoGrupoDao{
    private String select = "from TipoGrupo tp join fetch tp.grupo gru where gru.idGrupo= :grupo";
    private String selectId = "from TipoGrupo tp join fetch tp.grupo where tp.idTipo= :id";
    
    @Override
    public Boolean insertar(TipoGrupo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean actualizar(TipoGrupo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(TipoGrupo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoGrupo> obtenerElementos(String grupo) {
        List<TipoGrupo> listaTipos = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(select, TipoGrupo.class);
            query.setParameter("grupo", grupo);
            listaTipos = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaTipos;
    }

    @Override
    public TipoGrupo obtenerElemento(Integer id) {
        TipoGrupo tipo = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectId, TipoGrupo.class);
            query.setParameter("id", id);
            tipo = (TipoGrupo)query.uniqueResult();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return tipo;
    }
}
