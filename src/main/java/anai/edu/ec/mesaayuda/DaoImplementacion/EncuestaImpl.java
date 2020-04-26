/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.IEncuestaDao;
import anai.edu.ec.mesaayuda.Entity.Encuesta;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author bryan
 */
public class EncuestaImpl implements IEncuestaDao{
    private final String select = "from Encuesta";
    private final String selectId = "from Encuesta e where e.idEncuesta= :id";

    @Override
    public Boolean insertar(Encuesta o) {
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
    public Boolean actualizar(Encuesta o) {
        Transaction transaction = null;
        Boolean retorno = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.update(o);
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
    public Boolean eliminar(Encuesta o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Encuesta> obtenerElementos() {
        List<Encuesta> listaEncuestas = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(select,Encuesta.class);
            listaEncuestas = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaEncuestas;
    }

    @Override
    public Encuesta obtenerElemento(Encuesta id) {
        Encuesta encuesta = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectId,Encuesta.class);
            query.setParameter("id", id);
            encuesta = (Encuesta)query.uniqueResult();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return encuesta;
    }
    
}
