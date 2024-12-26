package ma.zyn.app.bean.core.localisation;


import java.time.LocalDateTime;


import ma.zyn.app.bean.core.sensor.Capteur;
import ma.zyn.app.bean.core.patient.Patient;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.config.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "localisation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="localisation_seq",sequenceName="localisation_seq",allocationSize=1, initialValue = 1)
public class Localisation  extends BaseEntity     {




    private LocalDateTime dateLocalisation ;

    private BigDecimal longitude = BigDecimal.ZERO;

    private BigDecimal latitude = BigDecimal.ZERO;

    private Patient patient ;
    private Capteur capteur ;


    public Localisation(){
        super();
    }

    public Localisation(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="localisation_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient")
    public Patient getPatient(){
        return this.patient;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }
    public LocalDateTime getDateLocalisation(){
        return this.dateLocalisation;
    }
    public void setDateLocalisation(LocalDateTime dateLocalisation){
        this.dateLocalisation = dateLocalisation;
    }
    public BigDecimal getLongitude(){
        return this.longitude;
    }
    public void setLongitude(BigDecimal longitude){
        this.longitude = longitude;
    }
    public BigDecimal getLatitude(){
        return this.latitude;
    }
    public void setLatitude(BigDecimal latitude){
        this.latitude = latitude;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capteur")
    public Capteur getCapteur(){
        return this.capteur;
    }
    public void setCapteur(Capteur capteur){
        this.capteur = capteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localisation localisation = (Localisation) o;
        return id != null && id.equals(localisation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

