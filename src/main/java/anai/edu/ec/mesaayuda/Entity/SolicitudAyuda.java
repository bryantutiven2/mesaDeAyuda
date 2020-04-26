package anai.edu.ec.mesaayuda.Entity;
// Generated 19/03/2020 10:04:58 by Hibernate Tools 4.3.1



import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SolicitudAyuda generated by hbm2java
 */
@Entity
@Table(name="SOLICITUD_AYUDA"
    ,schema="MA01"
)
public class SolicitudAyuda  implements java.io.Serializable {

     private SolicitudAyudaId id;
     private Grupo grupo;
     private TipoGrupo tipoGrupo;
     private Subtipo subtipo;
     private Usuario usuarioByIdUserTecnico;
     private Usuario usuarioByIdUserAdmin;
     private Usuario usuarioByIdUserSolicitaAyuda;
     private Encuesta encuesta;
     private String descripcion;
     private String mensajeUserTecnico;
     private Integer ayudaNVez;
     private String idsSolicitudNVez;
     private Date fechaInicio;
     private Date fechaFin;
     private Date fechaInicioTecnico;
     private Date fechaFinTecnico;
     private String estadoSolicitud;
     private String estadoSolicitudTecnico;
     private Integer estadoBorrado;
     private Integer estadoEncuesta;

    public SolicitudAyuda() {
    }

    public SolicitudAyuda(SolicitudAyudaId id, Grupo grupo, TipoGrupo tipoGrupo, Subtipo subtipo, Usuario usuarioByIdUserTecnico, Usuario usuarioByIdUserAdmin, Usuario usuarioByIdUserSolicitaAyuda, String descripcion, Integer ayudaNVez, String idsSolicitudNVez, Date fechaInicio, Date fechaFin, String estadoSolicitud, Integer estadoBorrado) {
        this.id = id;
        this.grupo = grupo;
        this.tipoGrupo = tipoGrupo;
        this.subtipo = subtipo;
        this.usuarioByIdUserTecnico = usuarioByIdUserTecnico;
        this.usuarioByIdUserAdmin = usuarioByIdUserAdmin;
        this.usuarioByIdUserSolicitaAyuda = usuarioByIdUserSolicitaAyuda;
        this.descripcion = descripcion;
        this.ayudaNVez = ayudaNVez;
        this.idsSolicitudNVez = idsSolicitudNVez;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoSolicitud = estadoSolicitud;
        this.estadoBorrado = estadoBorrado;
    }
    
    
    public SolicitudAyuda(SolicitudAyudaId id, TipoGrupo tipoGrupo, Usuario usuarioByIdUserTecnico, Date fechaFin, String estadoSolicitud) {
        this.id = id;
        this.tipoGrupo = tipoGrupo;
        this.usuarioByIdUserTecnico = usuarioByIdUserTecnico;
        this.fechaFin = fechaFin;
        this.estadoSolicitud = estadoSolicitud;
    }
    
    public SolicitudAyuda(SolicitudAyudaId id, Grupo grupo, String descripcion, Integer ayudaNVez, String idsSolicitudNVez, Date fechaInicio) {
        this.id = id;
        this.grupo = grupo;
        this.descripcion = descripcion;
        this.ayudaNVez = ayudaNVez;
        this.idsSolicitudNVez = idsSolicitudNVez;
        this.fechaInicio = fechaInicio;
    }
    public SolicitudAyuda(SolicitudAyudaId id, Grupo grupo, TipoGrupo tipoGrupo, Subtipo subtipo, 
            Usuario usuarioByIdUserTecnico, Usuario usuarioByIdUserAdmin, Usuario usuarioByIdUserSolicitaAyuda, 
            String descripcion, String mensajeUserTecnico, Integer ayudaNVez, String idsSolicitudNVez, 
            Date fechaInicio, Date fechaFin, Date fechaInicioTecnico, Date fechaFinTecnico,
            String estadoSolicitud, String estadoSolicitudTecnico, Integer estadoBorrado) {
       this.id = id;
       this.grupo = grupo;
       this.tipoGrupo = tipoGrupo;
       this.subtipo = subtipo;
       this.usuarioByIdUserTecnico = usuarioByIdUserTecnico;
       this.usuarioByIdUserAdmin = usuarioByIdUserAdmin;
       this.usuarioByIdUserSolicitaAyuda = usuarioByIdUserSolicitaAyuda;
       this.descripcion = descripcion;
       this.mensajeUserTecnico = mensajeUserTecnico;
       this.ayudaNVez = ayudaNVez;
       this.idsSolicitudNVez = idsSolicitudNVez;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.fechaInicioTecnico = fechaInicioTecnico;
       this.fechaFinTecnico = fechaFinTecnico;
       this.estadoSolicitud = estadoSolicitud;
       this.estadoSolicitudTecnico = estadoSolicitudTecnico;
       this.estadoBorrado = estadoBorrado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idSolicitud", column=@Column(name="ID_SOLICITUD", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="idGrupo", column=@Column(name="ID_GRUPO", nullable=false, length=5) ) } )
    public SolicitudAyudaId getId() {
        return this.id;
    }
    
    public void setId(SolicitudAyudaId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_GRUPO", nullable=false, insertable=false, updatable=false)
    public Grupo getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TIPO")
    public TipoGrupo getTipoGrupo() {
        return this.tipoGrupo;
    }
    
    public void setTipoGrupo(TipoGrupo tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_SUBTIPO")
    public Subtipo getSubtipo() {
        return this.subtipo;
    }
    
    public void setSubtipo(Subtipo subtipo) {
        this.subtipo = subtipo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_USER_TECNICO")
    public Usuario getUsuarioByIdUserTecnico() {
        return this.usuarioByIdUserTecnico;
    }
    
    public void setUsuarioByIdUserTecnico(Usuario usuarioByIdUserTecnico) {
        this.usuarioByIdUserTecnico = usuarioByIdUserTecnico;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_USER_ADMIN")
    public Usuario getUsuarioByIdUserAdmin() {
        return this.usuarioByIdUserAdmin;
    }
    
    public void setUsuarioByIdUserAdmin(Usuario usuarioByIdUserAdmin) {
        this.usuarioByIdUserAdmin = usuarioByIdUserAdmin;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_USER_SOLICITA_AYUDA")
    public Usuario getUsuarioByIdUserSolicitaAyuda() {
        return this.usuarioByIdUserSolicitaAyuda;
    }
    
    public void setUsuarioByIdUserSolicitaAyuda(Usuario usuarioByIdUserSolicitaAyuda) {
        this.usuarioByIdUserSolicitaAyuda = usuarioByIdUserSolicitaAyuda;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ENCUESTA")
    public Encuesta getEncuesta() {
        return this.encuesta;
    }
    
    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }
    
    @Column(name="DESCRIPCION", nullable=false, length=1000)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="MENSAJE_USER_TECNICO", length=1000)
    public String getMensajeUserTecnico() {
        return this.mensajeUserTecnico;
    }
    
    public void setMensajeUserTecnico(String mensajeUserTecnico) {
        this.mensajeUserTecnico = mensajeUserTecnico;
    }

    
    @Column(name="AYUDA_N_VEZ", nullable=false, precision=22, scale=0)
    public Integer getAyudaNVez() {
        return this.ayudaNVez;
    }
    
    public void setAyudaNVez(Integer ayudaNVez) {
        this.ayudaNVez = ayudaNVez;
    }

    
    @Column(name="IDS_SOLICITUD_N_VEZ", nullable=false, length=500)
    public String getIdsSolicitudNVez() {
        return this.idsSolicitudNVez;
    }
    
    public void setIdsSolicitudNVez(String idsSolicitudNVez) {
        this.idsSolicitudNVez = idsSolicitudNVez;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_INICIO", nullable=false, length=7)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_FIN", length=7)
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_INICIO_TECNICO", length=7)
    public Date getFechaInicioTecnico() {
        return this.fechaInicioTecnico;
    }
    
    public void setFechaInicioTecnico(Date fechaInicioTecnico) {
        this.fechaInicioTecnico = fechaInicioTecnico;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_FIN_TECNICO", length=7)
    public Date getFechaFinTecnico() {
        return this.fechaFinTecnico;
    }
    
    
    public void setFechaFinTecnico(Date fechaFinTecnico) {
        this.fechaFinTecnico = fechaFinTecnico;
    }
    
    @Column(name="ESTADO_SOLICITUD", length=25)
    public String getEstadoSolicitud() {
        return this.estadoSolicitud;
    }
    
    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
    
    @Column(name="ESTADO_SOLICITUD_TECNICO", length=25)
    public String getEstadoSolicitudTecnico() {
        return this.estadoSolicitudTecnico;
    }
    
    public void setEstadoSolicitudTecnico(String estadoSolicitudTecnico) {
        this.estadoSolicitudTecnico = estadoSolicitudTecnico;
    }
    
    @Column(name="ESTADO_BORRADO", precision=22, scale=0)
    public Integer getEstadoBorrado() {
        return this.estadoBorrado;
    }
    
    public void setEstadoBorrado(Integer estadoBorrado) {
        this.estadoBorrado = estadoBorrado;
    }

    @Column(name="ESTADO_ENCUESTA", precision=22, scale=0)
        public Integer getEstadoEncuesta() {
            return this.estadoEncuesta;
        }

        public void setEstadoEncuesta(Integer estadoEncuesta) {
            this.estadoEncuesta = estadoEncuesta;
        }


}


