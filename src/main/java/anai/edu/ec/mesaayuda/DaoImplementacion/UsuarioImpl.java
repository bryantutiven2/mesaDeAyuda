
package anai.edu.ec.mesaayuda.DaoImplementacion;

import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author bryan
 */
public class UsuarioImpl implements IUsuarioDao{
    /***
     * Tanto select y selectId sirven para cargar usuarios sin importar si tienen un tipo o no
     */
    private final String select = "from Usuario u";
    private final String selectId = "from Usuario u where u.idUsuario= :id";
    /***
     * Tanto selectUtp y selectIdUtp son para cargar usuarios que tienen un tipo y a su vez pertenece a un grupo
     */
    private final String selectUtp = "from Usuario u join fetch u.tipoGrupo gru join fetch gru.grupo ";
    private final String selectIdUtp = "from Usuario u join fetch u.tipoGrupo gru join fetch gru.grupo where u.idUsuario= :id";
    
    @Override
    public Boolean insertar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean actualizar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> obtenerElementos() {
        List<Usuario> listaUsuarios = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(select,Usuario.class);
            listaUsuarios = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaUsuarios;
    }

    @Override
    public Usuario obtenerElemento(Integer id) {
        Usuario usuario = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectId, Usuario.class);
            query.setParameter("id", id);
            usuario = (Usuario)query.uniqueResult();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return usuario;
    }

    @Override
    public List<Usuario> obtenerElementosUtp() {
        List<Usuario> listaUsuarios = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectUtp,Usuario.class);
            listaUsuarios = query.getResultList();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return listaUsuarios;
    }

    @Override
    public Usuario obtenerElementoUtp(Integer id) {
        Usuario usuario = null;
        try{
            HibernateUtil.abrirSession();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery(selectIdUtp, Usuario.class);
            query.setParameter("id", id);
            usuario = (Usuario)query.uniqueResult();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        finally{
            HibernateUtil.cerrarSession();
        }
        return usuario;
    } 
}
