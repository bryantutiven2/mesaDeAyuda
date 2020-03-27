/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author bryan
 */
public class SolicitudImpl implements ISolicitudDao{
    private final String generarIdSolicitud = "select solicitud_seq.nextval from dual";
    private final String obtenerSolicitudes = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda "
                                            + "join fetch sa.grupo gru where gru.idGrupo= :grupo and "
                                            + "sa.estadoSolicitud= :estado";
    
    @Override
    public Boolean insertar(SolicitudAyuda o) {
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
    public Boolean actualizar(SolicitudAyuda o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(SolicitudAyuda o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SolicitudAyuda> obtenerElementos(String grupo, String estado) {
        List<SolicitudAyuda> listaSolicitud = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(obtenerSolicitudes, SolicitudAyuda.class);
            query.setParameter("grupo", grupo);
            query.setParameter("estado", estado);
            listaSolicitud = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaSolicitud;
    }

    @Override
    public SolicitudAyuda obtenerElemento(SolicitudAyudaId id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer generarIdSolicitud() {
        Integer id = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            SQLQuery query = session.createSQLQuery(generarIdSolicitud);
            id = Integer.parseInt(query.uniqueResult().toString());
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return id;
    }
    
}
