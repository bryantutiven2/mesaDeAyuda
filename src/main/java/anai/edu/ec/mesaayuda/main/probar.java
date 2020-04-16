
package anai.edu.ec.mesaayuda.main;

import anai.edu.ec.mesaayuda.DAO.IGrupoDao;
import anai.edu.ec.mesaayuda.DAO.ISubtipoDao;
import anai.edu.ec.mesaayuda.DAO.ITipoGrupoDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.GrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SubtipoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.TipoGrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.fechaSolicitud;
import static anai.edu.ec.mesaayuda.Service.fechaSolicitud.convertirFecha;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;

/**
 *
 * @author bryan
 */
public class probar {
    public static void main(String[] args){
        System.out.println(fechaSolicitud.obtenerFecha());
//        System.out.println(convertirFecha("04/04/2020 10:30 pm"));
//        HibernateUtil.construirSessionFactory();
//        
//        
//        ITipoGrupoDao tipoDao = new TipoGrupoImpl();
//        for(TipoGrupo tp : tipoDao.obtenerElementos()){
//            System.out.println(tp.toString());
//        }
//        System.out.println(tipoDao.obtenerElemento(1).toString());
        
//        ISubtipoDao subtipoDao = new SubtipoImpl();
//        System.out.println(subtipoDao.obtenerElementos());
//        IUsuarioDao usuarioDao = new UsuarioImpl();
//        System.out.println(usuarioDao.obtenerElementoUtp(1).toString());
//        for(Usuario usu : usuarioDao.obtenerElementosUtp()){
//            System.out.println(usu.toString());
//        }
        //System.out.println(usuarioDao.obtenerElemento(1).toString());
//        IGrupoDao grupoDao = new GrupoImpl();
//        System.out.println(grupoDao.obtenerElemento("sist").toString());
    }
}
