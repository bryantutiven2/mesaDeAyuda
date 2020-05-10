
package anai.edu.ec.mesaayuda.Entity;

/**
 *
 * @author bryan
 */
public class UsuarioTabla {
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String correo;
    private Integer idDepartamento;
    private String rol;
    private Integer idTipo;

    public UsuarioTabla() {
        super();
    }
    
    public UsuarioTabla(String nombre, String apellido, String username, String password, String correo, Integer idDepartamento, String rol, Integer idTipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.idDepartamento = idDepartamento;
        this.rol = rol;
        this.idTipo = idTipo;
    }
    
    public UsuarioTabla(Integer idUsuario, String nombre, String apellido, String username, String password, String correo, Integer idDepartamento, String rol, Integer idTipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.idDepartamento = idDepartamento;
        this.rol = rol;
        this.idTipo = idTipo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
    
}
