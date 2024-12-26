package ma.zyn.app.bean.core.localisation;






import ma.zyn.app.bean.core.patient.Patient;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.config.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "safe_zone")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="safe_zone_seq",sequenceName="safe_zone_seq",allocationSize=1, initialValue = 1)
public class SafeZone  extends BaseEntity     {




    private BigDecimal centreLongitude = BigDecimal.ZERO;

    private BigDecimal centreLatitude = BigDecimal.ZERO;

    private BigDecimal rayon = BigDecimal.ZERO;

    private Patient linkedPatient ;


    public SafeZone(){
        super();
    }

    public SafeZone(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="safe_zone_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public BigDecimal getCentreLongitude(){
        return this.centreLongitude;
    }
    public void setCentreLongitude(BigDecimal centreLongitude){
        this.centreLongitude = centreLongitude;
    }
    public BigDecimal getCentreLatitude(){
        return this.centreLatitude;
    }
    public void setCentreLatitude(BigDecimal centreLatitude){
        this.centreLatitude = centreLatitude;
    }
    public BigDecimal getRayon(){
        return this.rayon;
    }
    public void setRayon(BigDecimal rayon){
        this.rayon = rayon;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linked_patient")
    public Patient getLinkedPatient(){
        return this.linkedPatient;
    }
    public void setLinkedPatient(Patient linkedPatient){
        this.linkedPatient = linkedPatient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SafeZone safeZone = (SafeZone) o;
        return id != null && id.equals(safeZone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

