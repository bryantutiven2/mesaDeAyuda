
package anai.edu.ec.mesaayuda.Controller;

import anai.edu.ec.mesaayuda.DAO.ISolicitudDao;
import anai.edu.ec.mesaayuda.DAO.ITipoGrupoDao;
import anai.edu.ec.mesaayuda.DAO.IUsuarioDao;
import anai.edu.ec.mesaayuda.DaoImplementacion.SolicitudImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.TipoGrupoImpl;
import anai.edu.ec.mesaayuda.DaoImplementacion.UsuarioImpl;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyuda;
import anai.edu.ec.mesaayuda.Entity.SolicitudAyudaId;
import anai.edu.ec.mesaayuda.Entity.SolicitudTabla;
import anai.edu.ec.mesaayuda.Entity.TipoGrupo;
import anai.edu.ec.mesaayuda.Entity.Usuario;
import anai.edu.ec.mesaayuda.Enum.EstadoSolicitud;
import static anai.edu.ec.mesaayuda.Service.SessionUsuario.obtenerSessionUsuario;
import static anai.edu.ec.mesaayuda.Service.fechaSolicitud.convertirFecha;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bryan
 */
@Controller
public class adminController {
    private ModelAndView model = new ModelAndView();
    private Usuario usuario;
    private ISolicitudDao solicitudDao = new SolicitudImpl();
    private ITipoGrupoDao tipoDao = new TipoGrupoImpl();
    private IUsuarioDao usuarioDao = new UsuarioImpl();
    private List<SolicitudAyuda> listaSolicitudAyuda;
    
    @RequestMapping(value = { "gestionarNuevaSolicitud"}, method = RequestMethod.GET)
    public ModelAndView gestionarNuevaSolicitud(){
        
        return model;
    }
    
    
    @RequestMapping(value = { "/nuevasSolicitudes"}, method = RequestMethod.GET)
    public ModelAndView nuevasSolicitudes(HttpServletRequest request, HttpServletResponse response){
        List<SolicitudTabla> listaTabla = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        usuario = obtenerSessionUsuario(request, response);
        String grupo = usuario.getRol().split("_")[1];
        try{
            Map<String, String> mapaSolicitud = new HashMap<>();
            mapaSolicitud.put("grupo", grupo);
            mapaSolicitud.put("estado", String.valueOf(EstadoSolicitud.pendiente));
            listaSolicitudAyuda = solicitudDao.obtenerElementos(mapaSolicitud);
            if(listaSolicitudAyuda != null){
                for(SolicitudAyuda lista : listaSolicitudAyuda){
                    listaTabla.add(
                            new SolicitudTabla(lista.getId().getIdSolicitud(), lista.getAyudaNVez(), lista.getIdsSolicitudNVez(),
                                    lista.getDescripcion(),
                                    lista.getUsuarioByIdUserSolicitaAyuda().getNombre()+" "+ lista.getUsuarioByIdUserSolicitaAyuda().getApellido(),
                                    String.valueOf(dateFormat.format(lista.getFechaInicio()))));
                }
                List<TipoGrupo> listaTipos = tipoDao.obtenerElementos(grupo);
                if(listaTipos != null)
                    model.addObject("listarTiposSN",listaTipos);
                List<Usuario> listaTecnicos = usuarioDao.obtenerElementos(grupo);
                if(listaTecnicos != null)
                    model.addObject("listarTecnicoSN", listaTecnicos);
                model.addObject("listaNuevasSolicitudes",listaTabla);
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
        datosUsuario();
        model.addObject("viewMain","solicitudesNuevasAdmin");
        model.setViewName("menuUsuario");
        return model;
    }
    
    @RequestMapping(value = { "/actualizarSolicitud"}, method = RequestMethod.POST)
    public ModelAndView actualizarSolicitud(HttpServletRequest request, HttpServletResponse response){
        try{
            String cod = request.getParameter("codigo_sn");
            String tipo_grupo = request.getParameter("tipoGrupo_sn");
            String fechaFin = request.getParameter("fechaFin_sn");
            String tecnico = request.getParameter("tecnico_sn");
            
            Usuario userTecnico = usuarioDao.obtenerElemento(Integer.parseInt(tecnico));
            TipoGrupo tipo = tipoDao.obtenerElemento(Integer.parseInt(tipo_grupo));
            String grupoTipo = tipo.getGrupo().getIdGrupo();
            SolicitudAyudaId idSolicitud = new SolicitudAyudaId(Integer.parseInt(cod), grupoTipo);
            Date date = convertirFecha(fechaFin);
            
            if(userTecnico != null && tipo != null && date != null){
                System.out.println(date);
                SolicitudAyuda objetoSolicitud = solicitudDao.obtenerElemento(idSolicitud);
                objetoSolicitud.setId(idSolicitud);
                objetoSolicitud.setTipoGrupo(tipo);
                objetoSolicitud.setFechaFin(date);
                objetoSolicitud.setUsuarioByIdUserTecnico(userTecnico);
                objetoSolicitud.setUsuarioByIdUserAdmin(usuario);
                objetoSolicitud.setEstadoSolicitud("asignada");
                Boolean retorno = solicitudDao.actualizar(objetoSolicitud);
                if(retorno.equals(true))
                    System.out.println(retorno);
            }
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
//        nuevasSolicitudes(request, response);
        return nuevasSolicitudes(request, response);
    }
    
    private void datosUsuario(){
        
        String rol = usuario.getRol();
        model.addObject("rol",rol);
        model.addObject("username", usuario.getNombre() + " " + usuario.getApellido());
        model.addObject("usuario", usuario.getUsuario());
        model.addObject("correo", usuario.getCorreo());
    }
}
