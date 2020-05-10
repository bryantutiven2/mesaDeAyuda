
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.IDepartamentoDao;
import anai.edu.ec.mesaayuda.Entity.Departamento;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author bryan
 */
public class DepartamentoImpl implements IDepartamentoDao{
    private final String select = "from Departamento";
    private final String selectId = "from Departamento d where d.idDepartamento= :id";
    
    
    @Override
    public Boolean insertar(Departamento o) {
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
    public List<Departamento> obtenerElementos() {
        List<Departamento> listaDepartamentos = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(select,Departamento.class);
            listaDepartamentos = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaDepartamentos;
    }
    
    @Override
    public Departamento obtenerElemento(Integer id){
        Departamento departamento = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectId,Departamento.class);
            query.setParameter("id", id);
            departamento = (Departamento)query.uniqueResult();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return departamento;
    }
}
