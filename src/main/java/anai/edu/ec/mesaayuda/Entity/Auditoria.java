package anai.edu.ec.mesaayuda.Entity;
// Generated 19/03/2020 10:04:58 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Auditoria generated by hbm2java
 */
@Entity
@Table(name="AUDITORIA"
    ,schema="MA01"
)
public class Auditoria  implements java.io.Serializable {


     private Integer idAuditoria;
     private String tipoEvento;
     private Date fechaTransaccion;
     private Date fechaGra;
     private String tablaMod;
     private String campoMod;
     private String datoAnterior;
     private String datoNuevo;
     private String usuario;
     private String pcid;
     private String ip;

    public Auditoria() {
    }

	
    public Auditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }
    public Auditoria(Integer idAuditoria, String tipoEvento, Date fechaTransaccion, Date fechaGra, String tablaMod, String campoMod, String datoAnterior, String datoNuevo, String usuario, String pcid, String ip) {
       this.idAuditoria = idAuditoria;
       this.tipoEvento = tipoEvento;
       this.fechaTransaccion = fechaTransaccion;
       this.fechaGra = fechaGra;
       this.tablaMod = tablaMod;
       this.campoMod = campoMod;
       this.datoAnterior = datoAnterior;
       this.datoNuevo = datoNuevo;
       this.usuario = usuario;
       this.pcid = pcid;
       this.ip = ip;
    }
   
     @Id 

    
    @Column(name="ID_AUDITORIA", unique=true, nullable=false, precision=22, scale=0)
    public Integer getIdAuditoria() {
        return this.idAuditoria;
    }
    
    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    
    @Column(name="TIPO_EVENTO", length=50)
    public String getTipoEvento() {
        return this.tipoEvento;
    }
    
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_TRANSACCION", length=7)
    public Date getFechaTransaccion() {
        return this.fechaTransaccion;
    }
    
    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_GRA", length=7)
    public Date getFechaGra() {
        return this.fechaGra;
    }
    
    public void setFechaGra(Date fechaGra) {
        this.fechaGra = fechaGra;
    }

    
    @Column(name="TABLA_MOD", length=50)
    public String getTablaMod() {
        return this.tablaMod;
    }
    
    public void setTablaMod(String tablaMod) {
        this.tablaMod = tablaMod;
    }

    
    @Column(name="CAMPO_MOD", length=50)
    public String getCampoMod() {
        return this.campoMod;
    }
    
    public void setCampoMod(String campoMod) {
        this.campoMod = campoMod;
    }

    
    @Column(name="DATO_ANTERIOR", length=1000)
    public String getDatoAnterior() {
        return this.datoAnterior;
    }
    
    public void setDatoAnterior(String datoAnterior) {
        this.datoAnterior = datoAnterior;
    }

    
    @Column(name="DATO_NUEVO", length=1000)
    public String getDatoNuevo() {
        return this.datoNuevo;
    }
    
    public void setDatoNuevo(String datoNuevo) {
        this.datoNuevo = datoNuevo;
    }

    
    @Column(name="USUARIO", length=15)
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="PCID", length=50)
    public String getPcid() {
        return this.pcid;
    }
    
    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    
    @Column(name="IP", length=50)
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }




}


