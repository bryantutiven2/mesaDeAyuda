
package anai.edu.ec.mesaayuda.Entity;

import java.util.Date;

/**
 *
 * @author bryan
 */
public class ConsultaObjeto {
    private String tipoSolicitud;
    private String grupo;
    private String estado;
    private String fechaDesde;
    private String fechaHasta;
    private Integer idUsuarioS;
    private Integer idTecnico;

    public ConsultaObjeto() {
        super();
    }

    public ConsultaObjeto(String tipoSolciitud, String grupo, String estado) {
        this.tipoSolicitud = tipoSolciitud;
        this.grupo = grupo;
        this.estado = estado;
    }

    public ConsultaObjeto(String tipoSolicitud, String grupo, String estado, String fechaDesde, String fechaHasta) {
        this.tipoSolicitud = tipoSolicitud;
        this.grupo = grupo;
        this.estado = estado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public ConsultaObjeto(String tipoSolicitud, String grupo, String estado, String fechaDesde, String fechaHasta, Integer idUsuarioS, Integer idTecnico) {
        this.tipoSolicitud = tipoSolicitud;
        this.grupo = grupo;
        this.estado = estado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.idUsuarioS = idUsuarioS;
        this.idTecnico = idTecnico;
    }
    
    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }
    
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Integer getIdUsuarioS() {
        return idUsuarioS;
    }

    public void setIdUsuarioS(Integer idUsuarioS) {
        this.idUsuarioS = idUsuarioS;
    }

    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    @Override
    public String toString() {
        return "ConsultaObjeto{" + "tipoSolicitud=" + tipoSolicitud + ", grupo=" + grupo + ", estado=" + estado + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", idUsuarioS=" + idUsuarioS + ", idTecnico=" + idTecnico + '}';
    }
    
}
