package ma.zyn.app.bean.core.warning;


import java.time.LocalDateTime;


import ma.zyn.app.bean.core.patient.Patient;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.config.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "warning_patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="warning_patient_seq",sequenceName="warning_patient_seq",allocationSize=1, initialValue = 1)
public class WarningPatient  extends BaseEntity     {




    private String message;

    private LocalDateTime dateWarning ;

    private Patient patient ;
    private WarningType warningType ;


    public WarningPatient(){
        super();
    }

    public WarningPatient(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="warning_patient_seq")
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
      @Column(columnDefinition="TEXT")
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warning_type")
    public WarningType getWarningType(){
        return this.warningType;
    }
    public void setWarningType(WarningType warningType){
        this.warningType = warningType;
    }
    public LocalDateTime getDateWarning(){
        return this.dateWarning;
    }
    public void setDateWarning(LocalDateTime dateWarning){
        this.dateWarning = dateWarning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarningPatient warningPatient = (WarningPatient) o;
        return id != null && id.equals(warningPatient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

