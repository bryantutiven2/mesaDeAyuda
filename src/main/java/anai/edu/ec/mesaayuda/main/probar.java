
package anai.edu.ec.mesaayuda.main;

import anai.edu.ec.mesaayuda.DAO.IGrupoDao;
import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DAO.ISubtipoDao;
import anai.edu.ec.mesaayuda.DAO.ITipoGrupoDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.GrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.SubtipoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.TipoGrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Service.fechaSolicitud;
import static anai.edu.ec.mesaayuda.Service.fechaSolicitud.convertirFecha;
import anai.edu.ec.mesaayuda.Util.HibernateUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bryan
 */
public class probar {
    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static void main(String[] args){
        
        Map<Usuario,List<SolicitudTabla>> listaTecnicoS = new HashMap<>();
        HibernateUtil.construirSessionFactory();
        ISolicitudDao dao = new SolicitudImpl();
        List<SolicitudAyuda> listaSolicitud = dao.solicitudesDashboardTecnico("sist");
        for(SolicitudAyuda sol : listaSolicitud){
            if(!listaTecnicoS.containsKey(sol.getUsuarioByIdUserTecnico())){
                List<SolicitudTabla> st = new ArrayList<>();
                SolicitudTabla solicitud = retornoSolicitud(sol);
                if(solicitud != null){
                    st.add(solicitud);
                    listaTecnicoS.put(sol.getUsuarioByIdUserTecnico(), st);
                }
                
            }
            else if(listaTecnicoS.containsKey(sol.getUsuarioByIdUserTecnico())){
                SolicitudTabla solicitud = retornoSolicitud(sol);
                if(solicitud != null){
                    List<SolicitudTabla> st = listaTecnicoS.get(sol.getUsuarioByIdUserTecnico());
                    st.add(solicitud);
                    listaTecnicoS.put(sol.getUsuarioByIdUserTecnico(), st);
                }
            }
        }
        Iterator it = listaTecnicoS.keySet().iterator();
        while(it.hasNext()){
          Usuario key = (Usuario) it.next();
          for(SolicitudTabla so : listaTecnicoS.get(key)){
              System.out.println("Clave: " + key.getNombre()+" "+key.getApellido() + " -> Valor: " + so.toString());
          }
        }
    }
    public static SolicitudTabla retornoSolicitud(SolicitudAyuda sol){
        SolicitudTabla solicitud = new SolicitudTabla();
        try{
                solicitud.setId(sol.getId().getIdSolicitud());
                solicitud.setUserSolicitaAyuda(sol.getUsuarioByIdUserSolicitaAyuda().getNombre()+" "+sol.getUsuarioByIdUserSolicitaAyuda().getApellido());
                solicitud.setDescripcion(sol.getDescripcion());
                solicitud.setFechaInicio(dateFormat.format(sol.getFechaInicio()));
                solicitud.setFechaFin(dateFormat.format(sol.getFechaFin()));
                solicitud.setEstadoSolicitudTecnico(sol.getEstadoSolicitudTecnico());
                if(sol.getFechaInicioTecnico() == null || String.valueOf(sol.getFechaInicioTecnico()).equals("null"))
                    solicitud.setFechaInicioTecnico("");
                else 
                    solicitud.setFechaInicioTecnico(dateFormat.format(sol.getFechaInicioTecnico()));
                if(sol.getFechaFinTecnico()== null || String.valueOf(sol.getFechaFinTecnico()).equals("null"))
                    solicitud.setFechaFinTecnico("");
                else
                    solicitud.setFechaFinTecnico(dateFormat.format(sol.getFechaFinTecnico()));
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        return solicitud;
    }
}
