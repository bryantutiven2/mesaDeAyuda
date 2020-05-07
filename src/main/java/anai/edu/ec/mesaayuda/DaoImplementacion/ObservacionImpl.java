
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.IObservacionDao;
import anai.edu.ec.mesaayuda.Entity.Observacion;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author bryan
 */
public class ObservacionImpl implements IObservacionDao{
    private final String selectObservaciones = "from Observacion ob join fetch ob.solicitudAyuda sol join fetch sol.id sid "
                                             + "join fetch ob.usuario where sid.idSolicitud= :id order by ob.idObservacion asc";
    
    @Override
    public Boolean insertar(Observacion o) {
        Transaction transaction = null;
        Boolean retorno = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.save(o);
            transaction = session.beginTransaction();
            transaction.commit();
            retorno = true;
        }
        catch(Exception e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
            retorno = false;
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return retorno;
    }

    @Override
    public List<Observacion> obtenerElementos(Integer id) {
        List<Observacion> listaObservacion = null;
        Query query = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            query = session.createQuery(selectObservaciones, Observacion.class);
            query.setParameter("id", id);
            listaObservacion = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaObservacion;
    }
    
}
