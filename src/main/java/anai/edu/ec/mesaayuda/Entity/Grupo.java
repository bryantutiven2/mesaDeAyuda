package anai.edu.ec.mesaayuda.Entity;
// Generated 19/03/2020 10:04:58 by Hibernate Tools 4.3.1



import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Grupo generated by hbm2java
 */
@Entity
@Table(name="GRUPO"
    ,schema="MA01"
)
public class Grupo  implements java.io.Serializable {


     private String idGrupo;
     private String nombreGrupo;
     private Integer estadoBorrado;
     private Set<SolicitudAyuda> solicitudAyudas = new HashSet<SolicitudAyuda>(0);
     private Set<TipoGrupo> tipoGrupos = new HashSet<TipoGrupo>(0);

    public Grupo() {
    }

	
    public Grupo(String idGrupo, String nombreGrupo) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
    }
    public Grupo(String idGrupo, String nombreGrupo, Integer estadoBorrado, Set<SolicitudAyuda> solicitudAyudas, Set<TipoGrupo> tipoGrupos) {
       this.idGrupo = idGrupo;
       this.nombreGrupo = nombreGrupo;
       this.estadoBorrado = estadoBorrado;
       this.solicitudAyudas = solicitudAyudas;
       this.tipoGrupos = tipoGrupos;
    }
   
    @Id 
    @Column(name="ID_GRUPO", unique=true, nullable=false, length=5)
    public String getIdGrupo() {
        return this.idGrupo;
    }
    
    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    
    @Column(name="NOMBRE_GRUPO", nullable=false, length=50)
    public String getNombreGrupo() {
        return this.nombreGrupo;
    }
    
    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    
    @Column(name="ESTADO_BORRADO", precision=22, scale=0)
    public Integer getEstadoBorrado() {
        return this.estadoBorrado;
    }
    
    public void setEstadoBorrado(Integer estadoBorrado) {
        this.estadoBorrado = estadoBorrado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="grupo")
    public Set<SolicitudAyuda> getSolicitudAyudas() {
        return this.solicitudAyudas;
    }
    
    public void setSolicitudAyudas(Set<SolicitudAyuda> solicitudAyudas) {
        this.solicitudAyudas = solicitudAyudas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="grupo")
    public Set<TipoGrupo> getTipoGrupos() {
        return this.tipoGrupos;
    }
    
    public void setTipoGrupos(Set<TipoGrupo> tipoGrupos) {
        this.tipoGrupos = tipoGrupos;
    }

    @Override
    public String toString() {
        return "Grupo{" + "idGrupo=" + idGrupo + ", nombreGrupo=" + nombreGrupo + ", estadoBorrado=" + estadoBorrado + '}';
    }
    
    
}


