package anai.edu.ec.mesaayuda.Entity;
// Generated 19/03/2020 10:04:58 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="USUARIO"
    ,schema="MA01"
    , uniqueConstraints = {@UniqueConstraint(columnNames="CORREO"), @UniqueConstraint(columnNames="USUARIO")} 
)
public class Usuario  implements java.io.Serializable {


     private Integer idUsuario;
     private TipoGrupo tipoGrupo;
     private String nombre;
     private String apellido;
     private String usuario;
     private String contrasena;
     private String correo;
     private String departamento;
     private String rol;
     private Integer estadoBorrado;
     private Set<SolicitudAyuda> solicitudAyudasForIdUserTecnico = new HashSet<SolicitudAyuda>(0);
     private Set<SolicitudAyuda> solicitudAyudasForIdUserAdmin = new HashSet<SolicitudAyuda>(0);
     private Set<SolicitudAyuda> solicitudAyudasForIdUserSolicitaAyuda = new HashSet<SolicitudAyuda>(0);

    public Usuario() {
    }

	
    public Usuario(Integer idUsuario, String nombre, String apellido, String usuario, String contrasena, String correo, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.rol = rol;
    }
    public Usuario(Integer idUsuario, TipoGrupo tipoGrupo, String nombre, String apellido, String usuario, String contrasena, String correo, String departamento, String rol, Integer estadoBorrado, Set<SolicitudAyuda> solicitudAyudasForIdUserTecnico, Set<SolicitudAyuda> solicitudAyudasForIdUserAdmin, Set<SolicitudAyuda> solicitudAyudasForIdUserSolicitaAyuda) {
       this.idUsuario = idUsuario;
       this.tipoGrupo = tipoGrupo;
       this.nombre = nombre;
       this.apellido = apellido;
       this.usuario = usuario;
       this.contrasena = contrasena;
       this.correo = correo;
       this.departamento = departamento;
       this.rol = rol;
       this.estadoBorrado = estadoBorrado;
       this.solicitudAyudasForIdUserTecnico = solicitudAyudasForIdUserTecnico;
       this.solicitudAyudasForIdUserAdmin = solicitudAyudasForIdUserAdmin;
       this.solicitudAyudasForIdUserSolicitaAyuda = solicitudAyudasForIdUserSolicitaAyuda;
    }
   
     @Id 

    
    @Column(name="ID_USUARIO", unique=true, nullable=false, precision=22, scale=0)
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TIPO")
    public TipoGrupo getTipoGrupo() {
        return this.tipoGrupo;
    }
    
    public void setTipoGrupo(TipoGrupo tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    
    @Column(name="NOMBRE", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="APELLIDO", nullable=false, length=50)
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
    @Column(name="USUARIO", unique=true, nullable=false, length=50)
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="CONTRASENA", nullable=false, length=50)
    public String getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    @Column(name="CORREO", unique=true, nullable=false, length=200)
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    @Column(name="DEPARTAMENTO", length=50)
    public String getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    
    @Column(name="ROL", nullable=false, length=50)
    public String getRol() {
        return this.rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }

    
    @Column(name="ESTADO_BORRADO", precision=22, scale=0)
    public Integer getEstadoBorrado() {
        return this.estadoBorrado;
    }
    
    public void setEstadoBorrado(Integer estadoBorrado) {
        this.estadoBorrado = estadoBorrado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarioByIdUserTecnico")
    public Set<SolicitudAyuda> getSolicitudAyudasForIdUserTecnico() {
        return this.solicitudAyudasForIdUserTecnico;
    }
    
    public void setSolicitudAyudasForIdUserTecnico(Set<SolicitudAyuda> solicitudAyudasForIdUserTecnico) {
        this.solicitudAyudasForIdUserTecnico = solicitudAyudasForIdUserTecnico;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarioByIdUserAdmin")
    public Set<SolicitudAyuda> getSolicitudAyudasForIdUserAdmin() {
        return this.solicitudAyudasForIdUserAdmin;
    }
    
    public void setSolicitudAyudasForIdUserAdmin(Set<SolicitudAyuda> solicitudAyudasForIdUserAdmin) {
        this.solicitudAyudasForIdUserAdmin = solicitudAyudasForIdUserAdmin;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarioByIdUserSolicitaAyuda")
    public Set<SolicitudAyuda> getSolicitudAyudasForIdUserSolicitaAyuda() {
        return this.solicitudAyudasForIdUserSolicitaAyuda;
    }
    
    public void setSolicitudAyudasForIdUserSolicitaAyuda(Set<SolicitudAyuda> solicitudAyudasForIdUserSolicitaAyuda) {
        this.solicitudAyudasForIdUserSolicitaAyuda = solicitudAyudasForIdUserSolicitaAyuda;
    }

    @Override
    public String toString() {
        if(tipoGrupo == null)
            return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", usuario=" + usuario + ", contrasena=" + contrasena + ", correo=" + correo + ", departamento=" + departamento + ", rol=" + rol + ", estadoBorrado=" + estadoBorrado + '}';
        return "Usuario{" + "idUsuario=" + idUsuario + ", tipoGrupo=" + tipoGrupo.toString() + ", nombre=" + nombre + ", apellido=" + apellido + ", usuario=" + usuario + ", contrasena=" + contrasena + ", correo=" + correo + ", departamento=" + departamento + ", rol=" + rol + ", estadoBorrado=" + estadoBorrado + '}';
    }
    
    
}

