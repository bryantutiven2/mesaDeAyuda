
package anai.edu.ec.mesaayuda.Entity;

/**
 *
 * @author bryan
 */
public class ConsultaObjeto {
    private String tipoSolicitud;
    private String grupo;
    private String estado;

    public ConsultaObjeto() {
        super();
    }

    public ConsultaObjeto(String tipoSolciitud, String grupo, String estado) {
        this.tipoSolicitud = tipoSolciitud;
        this.grupo = grupo;
        this.estado = estado;
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
    
    
}
