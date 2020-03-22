
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.IGrupoDao;
import anai.edu.ec.mesaayuda.Entity.Grupo;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author bryan
 */
public class GrupoImpl implements IGrupoDao{
    private final String select = "from Grupo";
    private final String selectId = "from Grupo g where g.idGrupo= :id";
    
    @Override
    public Boolean insertar(Grupo o) {
        Boolean retorno = null;
        Transaction transaction = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
            retorno = true;
        }
        catch(Exception exc){
            retorno = false;
            if(transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return retorno;
    }

    @Override
    public Boolean actualizar(Grupo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(Grupo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Grupo> obtenerElementos() {
        List<Grupo> listaGrupos = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(select,Grupo.class);
            listaGrupos = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaGrupos;
    }

    @Override
    public Grupo obtenerElemento(String id){
        Grupo grupo = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectId,Grupo.class);
            query.setParameter("id", id);
            grupo = (Grupo)query.uniqueResult();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return grupo;
    }
    
}
