package  ma.zyn.app.ws.dto.patient;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;
import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.staff.InfirmierDto;
import ma.zyn.app.ws.dto.healthcare.InfirmierPatientDto;
import ma.zyn.app.ws.dto.staff.DoctorDto;
import ma.zyn.app.ws.dto.warning.WarningPatientDto;
import ma.zyn.app.ws.dto.warning.WarningTypeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto  extends UserDto {

    private String dateOfBirth ;
    private String address  ;
    private String emergencyContact  ;

    private GenderDto gender ;
    private DoctorDto doctorInCharge ;

    private List<InfirmierPatientDto> infirmierPatients ;
    private List<WarningPatientDto> warningPatients ;


    private Collection<Role> roles;
    public PatientDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateOfBirth(){
        return this.dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }


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










    public GenderDto getGender(){
        return this.gender;
    }

    public void setGender(GenderDto gender){
        this.gender = gender;
    }
    public DoctorDto getDoctorInCharge(){
        return this.doctorInCharge;
    }

    public void setDoctorInCharge(DoctorDto doctorInCharge){
        this.doctorInCharge = doctorInCharge;
    }



    public List<InfirmierPatientDto> getInfirmierPatients(){
        return this.infirmierPatients;
    }

    public void setInfirmierPatients(List<InfirmierPatientDto> infirmierPatients){
        this.infirmierPatients = infirmierPatients;
    }
    public List<WarningPatientDto> getWarningPatients(){
        return this.warningPatients;
    }

    public void setWarningPatients(List<WarningPatientDto> warningPatients){
        this.warningPatients = warningPatients;
    }




    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
