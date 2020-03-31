
package anai.edu.ec.mesaayuda.Entity;

/**
 *
 * @author bryan
 */
public class SolicitudTabla {
    private Integer id;
    private String grupo;
    private String tipo;
    private Integer n_vez;
    private String ids_n_vez;
    private String descripcion;
    private String userAdmin;
    private String userTecnico;
    private String userSolicitaAyuda;
    private String fechaInicio;
    private String fechaFin;
    private String estadoSolicitud;

    public SolicitudTabla(Integer id, String grupo, String tipo, String descripcion) {
        this.id = id;
        this.grupo = grupo;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    public SolicitudTabla(Integer id, Integer n_vez, String ids_n_vez, String descripcion, String userSolicitaAyuda, String fechaInicio) {
        this.id = id;
        this.n_vez = n_vez;
        this.ids_n_vez = ids_n_vez;
        this.descripcion = descripcion;
        this.userSolicitaAyuda = userSolicitaAyuda;
        this.fechaInicio = fechaInicio;
    }
    
    
    public SolicitudTabla(Integer id, String descripcion, String userSolicitaAyuda) {
        this.id = id;
        this.descripcion = descripcion;
        this.userSolicitaAyuda = userSolicitaAyuda;
    }
    
    public SolicitudTabla(Integer id, String grupo, String tipo, String descripcion, String userTecnico, String fechaInicio, String fechaFin, String estadoSolicitud) {
        this.id = id;
        this.grupo = grupo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.userTecnico = userTecnico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoSolicitud = estadoSolicitud;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getUserTecnico() {
        return userTecnico;
    }

    public void setUserTecnico(String userTecnico) {
        this.userTecnico = userTecnico;
    }

    public String getUserSolicitaAyuda() {
        return userSolicitaAyuda;
    }

    public void setUserSolicitaAyuda(String userSolicitaAyuda) {
        this.userSolicitaAyuda = userSolicitaAyuda;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Integer getN_vez() {
        return n_vez;
    }

    public void setN_vez(Integer n_vez) {
        this.n_vez = n_vez;
    }

    public String getIds_n_vez() {
        return ids_n_vez;
    }

    public void setIds_n_vez(String ids_n_vez) {
        this.ids_n_vez = ids_n_vez;
    }
    
}
