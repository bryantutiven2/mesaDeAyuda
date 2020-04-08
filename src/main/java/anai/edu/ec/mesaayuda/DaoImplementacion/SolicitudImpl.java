/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Enum.EstadoSolicitud;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import java.util.Map;
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
    private final String nuevasSolicitudes = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda "
                                            + "join fetch sa.grupo gru where gru.idGrupo= :grupo and "
                                            + "sa.estadoSolicitud= :estado";
    private final String obtenerSolicitudesUserSa = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda usa "
                                            + "join fetch sa.grupo join fetch sa.tipoGrupo "
                                            + "where usa.idUsuario= :id and sa.estadoSolicitud = 'finalizada'";
    private final String selectId = "from SolicitudAyuda sa where sa.id= : id";
    private final String buscarPorGrupo1 = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda usa "
                                            + "join fetch sa.usuarioByIdUserTecnico "
                                            + "join fetch sa.grupo gru join fetch sa.tipoGrupo "
                                            + "where gru.idGrupo= :grupo and usa.idUsuario= :idUserSA";
    private final String buscarPorGrupo2 = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda usa "
                                            + "join fetch sa.grupo gru "
                                            + "where gru.idGrupo= :grupo and usa.idUsuario= :idUserSA";
    
    private final String bucsarPorEstado1 = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda usa "
                                            + "join fetch sa.usuarioByIdUserTecnico "
                                            + "join fetch sa.grupo join fetch sa.tipoGrupo "
                                            + "where sa.estadoSolicitud= :estado and usa.idUsuario= :idUserSA";
    private final String bucsarPorEstado2 = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda usa "
                                            + "join fetch sa.grupo "
                                            + "where sa.estadoSolicitud= :estado and usa.idUsuario= :idUserSA";
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
    public Boolean eliminar(SolicitudAyuda o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SolicitudAyuda> nuevasSolicitudes(String grupo, String estado) {
        List<SolicitudAyuda> listaSolicitud = null;
        Query query = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            query = session.createQuery(nuevasSolicitudes, SolicitudAyuda.class);
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
        SolicitudAyuda solicitud = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectId, SolicitudAyuda.class);
            query.setParameter("id", id);
            solicitud = (SolicitudAyuda)query.uniqueResult();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return solicitud;
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

    @Override
    public List<SolicitudAyuda> buscarPorGrupo(String grupo, Integer idUserSA) {
        List<SolicitudAyuda> listaSolicitud = null;
        Query query = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            query = session.createQuery(buscarPorGrupo1, SolicitudAyuda.class);
            query.setParameter("grupo", grupo);
            query.setParameter("idUserSA", idUserSA);
            listaSolicitud = query.getResultList();
            
            query = session.createQuery(buscarPorGrupo2, SolicitudAyuda.class);
            query.setParameter("grupo", grupo);
            query.setParameter("idUserSA", idUserSA);
            listaSolicitud.addAll(query.getResultList()); 
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
    public List<SolicitudAyuda> buscarPorEstado(String estado, Integer idUserSA) {
        List<SolicitudAyuda> listaSolicitud = null;
        Query query = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            query = session.createQuery(bucsarPorEstado1, SolicitudAyuda.class);
            query.setParameter("estado", estado);
            query.setParameter("idUserSA", idUserSA);
            listaSolicitud = query.getResultList();
            
            query = session.createQuery(bucsarPorEstado2, SolicitudAyuda.class);
            query.setParameter("estado", estado);
            query.setParameter("idUserSA", idUserSA);
            listaSolicitud.addAll(query.getResultList()); 
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
    public List<SolicitudAyuda> obtenerElementos(Integer id) {
        List<SolicitudAyuda> listaSolicitud = null;
        Query query = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            query = session.createQuery(obtenerSolicitudesUserSa, SolicitudAyuda.class);
            query.setParameter("id", id);
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
    
}
