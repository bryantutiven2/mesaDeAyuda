
package anai.edu.ec.mesaayuda.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author bryan
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;
    
    public static synchronized void construirSessionFactory() {
        
        try{
            registry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources sources = new MetadataSources(registry);
            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        catch(Exception e){
            e.printStackTrace();
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        
    }
    
    public static void abrirSession() {
        Session session = sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory==null)  {
            construirSessionFactory();
        }
        return sessionFactory;
    }
    
    public static void cerrarSession() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        if (session!=null) {
            session.close();
        }
    }
    
    public static void cerrarSessionFactory() {
        if ((sessionFactory!=null) && (sessionFactory.isClosed()==false)) {
            sessionFactory.close();
        }
    }
    
}