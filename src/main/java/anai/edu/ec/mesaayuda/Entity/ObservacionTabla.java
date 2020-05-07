
package anai.edu.ec.mesaayuda.Entity;

/**
 *
 * @author bryan
 */
public class ObservacionTabla {
    private Integer id;
    private String mensaje;
    private String usuario;
    private String fecha;

    public ObservacionTabla() {
        super();
    }

    public ObservacionTabla(Integer id, String mensaje, String usuario, String fecha) {
        this.id = id;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public ObservacionTabla(String mensaje, String usuario) {
        this.mensaje = mensaje;
        this.usuario = usuario;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
