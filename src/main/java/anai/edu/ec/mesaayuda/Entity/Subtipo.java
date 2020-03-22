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
 * Subtipo generated by hbm2java
 */
@Entity
@Table(name="SUBTIPO"
    ,schema="MA01"
    , uniqueConstraints = @UniqueConstraint(columnNames="NOMBRE_SUBTIPO") 
)
public class Subtipo  implements java.io.Serializable {


     private Integer idSubtipo;
     private TipoGrupo tipoGrupo;
     private String nombreSubtipo;
     private Integer estadoBorrado;
     private Set<SolicitudAyuda> solicitudAyudas = new HashSet<SolicitudAyuda>(0);

    public Subtipo() {
    }

	
    public Subtipo(Integer idSubtipo, TipoGrupo tipoGrupo, String nombreSubtipo) {
        this.idSubtipo = idSubtipo;
        this.tipoGrupo = tipoGrupo;
        this.nombreSubtipo = nombreSubtipo;
    }
    public Subtipo(Integer idSubtipo, TipoGrupo tipoGrupo, String nombreSubtipo, Integer estadoBorrado, Set<SolicitudAyuda> solicitudAyudas) {
       this.idSubtipo = idSubtipo;
       this.tipoGrupo = tipoGrupo;
       this.nombreSubtipo = nombreSubtipo;
       this.estadoBorrado = estadoBorrado;
       this.solicitudAyudas = solicitudAyudas;
    }
   
     @Id 

    
    @Column(name="ID_SUBTIPO", unique=true, nullable=false, precision=22, scale=0)
    public Integer getIdSubtipo() {
        return this.idSubtipo;
    }
    
    public void setIdSubtipo(Integer idSubtipo) {
        this.idSubtipo = idSubtipo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TIPO", nullable=false)
    public TipoGrupo getTipoGrupo() {
        return this.tipoGrupo;
    }
    
    public void setTipoGrupo(TipoGrupo tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    
    @Column(name="NOMBRE_SUBTIPO", unique=true, nullable=false, length=50)
    public String getNombreSubtipo() {
        return this.nombreSubtipo;
    }
    
    public void setNombreSubtipo(String nombreSubtipo) {
        this.nombreSubtipo = nombreSubtipo;
    }

    
    @Column(name="ESTADO_BORRADO", precision=22, scale=0)
    public Integer getEstadoBorrado() {
        return this.estadoBorrado;
    }
    
    public void setEstadoBorrado(Integer estadoBorrado) {
        this.estadoBorrado = estadoBorrado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="subtipo")
    public Set<SolicitudAyuda> getSolicitudAyudas() {
        return this.solicitudAyudas;
    }
    
    public void setSolicitudAyudas(Set<SolicitudAyuda> solicitudAyudas) {
        this.solicitudAyudas = solicitudAyudas;
    }

    @Override
    public String toString() {
        return "Subtipo{" + "idSubtipo=" + idSubtipo + ", tipoGrupo=" + tipoGrupo.getIdTipo() + ", nombreSubtipo=" + nombreSubtipo + ", estadoBorrado=" + estadoBorrado + '}';
    }
    
    
}


