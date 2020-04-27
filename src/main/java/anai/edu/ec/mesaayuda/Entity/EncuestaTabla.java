
package anai.edu.ec.mesaayuda.Entity;

/**
 *
 * @author bryan
 */
public class EncuestaTabla {
    private Integer idEncuesta;
    private String nombre;
    private String descripcion;
    private String codigoEmbebido;
    private String codigoRegistro;
    private Integer estadoBorrado;
    private String idSolicitud;

    public EncuestaTabla() {
        super();
    }

    public EncuestaTabla(Integer idEncuesta, String nombre, String descripcion, String codigoEmbebido, String codigoRegistro, Integer estadoBorrado, String idSolicitud) {
        this.idEncuesta = idEncuesta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigoEmbebido = codigoEmbebido;
        this.codigoRegistro = codigoRegistro;
        this.estadoBorrado = estadoBorrado;
        this.idSolicitud = idSolicitud;
    }

    
    public EncuestaTabla(Integer idEncuesta, String nombre, String descripcion, String codigoEmbebido, String codigoRegistro, Integer estadoBorrado) {
        this.idEncuesta = idEncuesta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigoEmbebido = codigoEmbebido;
        this.codigoRegistro = codigoRegistro;
        this.estadoBorrado = estadoBorrado;
    }
    
    
    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoEmbebido() {
        return codigoEmbebido;
    }

    public void setCodigoEmbebido(String codigoEmbebido) {
        this.codigoEmbebido = codigoEmbebido;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public Integer getEstadoBorrado() {
        return estadoBorrado;
    }

    public void setEstadoBorrado(Integer estadoBorrado) {
        this.estadoBorrado = estadoBorrado;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    
}
