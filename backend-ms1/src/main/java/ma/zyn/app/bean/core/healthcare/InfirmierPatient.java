package ma.zyn.app.bean.core.healthcare;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.bean.core.patient.Patient;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "infirmier_patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="infirmier_patient_seq",sequenceName="infirmier_patient_seq",allocationSize=1, initialValue = 1)
public class InfirmierPatient  extends BaseEntity     {




    private LocalDateTime dateDebut ;

    private LocalDateTime dateFin ;

    private Patient patient ;
    private Infirmier infirmier ;


    public InfirmierPatient(){
        super();
    }

    public InfirmierPatient(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="infirmier_patient_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infirmier")
    public Infirmier getInfirmier(){
        return this.infirmier;
    }
    public void setInfirmier(Infirmier infirmier){
        this.infirmier = infirmier;
    }
    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfirmierPatient infirmierPatient = (InfirmierPatient) o;
        return id != null && id.equals(infirmierPatient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

