package ma.zyn.app.bean.core.patient;

import java.util.List;

import java.time.LocalDateTime;


import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.bean.core.staff.Doctor;
import ma.zyn.app.bean.core.warning.WarningPatient;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.config.security.bean.User;

@Entity
@Table(name = "patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="patient_seq",sequenceName="patient_seq",allocationSize=1, initialValue = 1)
public class Patient  extends User    {


    public Patient(String username) {
        super(username);
    }


    private LocalDateTime dateOfBirth ;

    private String address;

    @Column(length = 500)
    private String emergencyContact;









    private Gender gender ;
    private Doctor doctorInCharge ;

    private List<InfirmierPatient> infirmierPatients ;
    private List<WarningPatient> warningPatients ;

    public Patient(){
        super();
    }

    public Patient(Long id){
        this.id = id;
    }

    public Patient(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="patient_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public LocalDateTime getDateOfBirth(){
        return this.dateOfBirth;
    }
    public void setDateOfBirth(LocalDateTime dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender")
    public Gender getGender(){
        return this.gender;
    }
    public void setGender(Gender gender){
        this.gender = gender;
    }
      @Column(columnDefinition="TEXT")
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getEmergencyContact(){
        return this.emergencyContact;
    }
    public void setEmergencyContact(String emergencyContact){
        this.emergencyContact = emergencyContact;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_in_charge")
    public Doctor getDoctorInCharge(){
        return this.doctorInCharge;
    }
    public void setDoctorInCharge(Doctor doctorInCharge){
        this.doctorInCharge = doctorInCharge;
    }
    @OneToMany(mappedBy = "patient")
    public List<InfirmierPatient> getInfirmierPatients(){
        return this.infirmierPatients;
    }

    public void setInfirmierPatients(List<InfirmierPatient> infirmierPatients){
        this.infirmierPatients = infirmierPatients;
    }
    @OneToMany(mappedBy = "patient")
    public List<WarningPatient> getWarningPatients(){
        return this.warningPatients;
    }

    public void setWarningPatients(List<WarningPatient> warningPatients){
        this.warningPatients = warningPatients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id != null && id.equals(patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

