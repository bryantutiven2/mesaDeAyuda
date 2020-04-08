
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
    private final String nuevasSolicitudesAdmin = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda "
                                            + "join fetch sa.grupo gru where gru.idGrupo= :grupo and "
                                            + "sa.estadoSolicitud= :estado";
    private final String selectId = "from SolicitudAyuda sa where sa.id= : id";
    private final String buscarSolicitudesPorUser = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda usa "
                                            + "join fetch sa.usuarioByIdUserTecnico "
                                            + "join fetch sa.grupo join fetch sa.tipoGrupo "
                                            + "where usa.idUsuario= :idUserSA";
    private final String buscarSolicitudesPorUser2 = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda usa "
                                            + "join fetch sa.grupo "
                                            + "where sa.estadoSolicitud = 'pendiente' and usa.idUsuario= :idUserSA";
    private final String cargarSolicitudes = "from SolicitudAyuda sa join fetch sa.usuarioByIdUserSolicitaAyuda usa "
                                            + "join fetch sa.grupo "
                                            + "where usa.idUsuario= :idUserSA and sa.estadoSolicitud= :estado";
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
            
            query = session.createQuery(nuevasSolicitudesAdmin, SolicitudAyuda.class);
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
    public List<SolicitudAyuda> buscarSolicitudes(Integer id) {
        List<SolicitudAyuda> listaSolicitud = null;
        Query query = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            
            query = session.createQuery(buscarSolicitudesPorUser, SolicitudAyuda.class);
            query.setParameter("idUserSA", id);
            listaSolicitud = query.getResultList();

            query = session.createQuery(buscarSolicitudesPorUser2, SolicitudAyuda.class);
            query.setParameter("idUserSA", id);
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
    public List<SolicitudAyuda> cargarSolicitudes(String estado, Integer id) {
        List<SolicitudAyuda> listaSolicitud = null;
        Query query = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            
            query = session.createQuery(cargarSolicitudes, SolicitudAyuda.class);
            query.setParameter("estado", estado);
            query.setParameter("idUserSA", id);
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
