
package anai.edu.ec.mesaayuda.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author bryan
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    public static synchronized void construirSessionFactory() {
        try{
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources sources = new MetadataSources(registry);
            Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        catch(Exception e){
            System.err.println("Exception while initializing hibernate util.. " + e.getMessage());
            e.printStackTrace();
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