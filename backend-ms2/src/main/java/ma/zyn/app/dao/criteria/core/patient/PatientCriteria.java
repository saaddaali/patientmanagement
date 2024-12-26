package  ma.zyn.app.dao.criteria.core.patient;


import ma.zyn.app.dao.criteria.core.staff.DoctorCriteria;

import ma.zyn.app.config.security.dao.criteria.core.UserCriteria;

import java.util.List;
import java.time.LocalDateTime;

public class PatientCriteria extends UserCriteria  {

    private LocalDateTime dateOfBirth;
    private LocalDateTime dateOfBirthFrom;
    private LocalDateTime dateOfBirthTo;
    private String address;
    private String addressLike;
    private String emergencyContact;
    private String emergencyContactLike;
    private Boolean passwordChanged;
    private Boolean accountNonLocked;
    private String password;
    private String passwordLike;
    private String email;
    private String emailLike;
    private Boolean enabled;
    private Boolean credentialsNonExpired;
    private Boolean accountNonExpired;
    private String username;
    private String usernameLike;

    private GenderCriteria gender ;
    private List<GenderCriteria> genders ;
    private DoctorCriteria doctorInCharge ;
    private List<DoctorCriteria> doctorInCharges ;


    public LocalDateTime getDateOfBirth(){
        return this.dateOfBirth;
    }
    public void setDateOfBirth(LocalDateTime dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDateTime getDateOfBirthFrom(){
        return this.dateOfBirthFrom;
    }
    public void setDateOfBirthFrom(LocalDateTime dateOfBirthFrom){
        this.dateOfBirthFrom = dateOfBirthFrom;
    }
    public LocalDateTime getDateOfBirthTo(){
        return this.dateOfBirthTo;
    }
    public void setDateOfBirthTo(LocalDateTime dateOfBirthTo){
        this.dateOfBirthTo = dateOfBirthTo;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddressLike(){
        return this.addressLike;
    }
    public void setAddressLike(String addressLike){
        this.addressLike = addressLike;
    }

    public String getEmergencyContact(){
        return this.emergencyContact;
    }
    public void setEmergencyContact(String emergencyContact){
        this.emergencyContact = emergencyContact;
    }
    public String getEmergencyContactLike(){
        return this.emergencyContactLike;
    }
    public void setEmergencyContactLike(String emergencyContactLike){
        this.emergencyContactLike = emergencyContactLike;
    }

    public Boolean getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(Boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }
    public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPasswordLike(){
        return this.passwordLike;
    }
    public void setPasswordLike(String passwordLike){
        this.passwordLike = passwordLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }
    public Boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public Boolean getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsernameLike(){
        return this.usernameLike;
    }
    public void setUsernameLike(String usernameLike){
        this.usernameLike = usernameLike;
    }


    public GenderCriteria getGender(){
        return this.gender;
    }

    public void setGender(GenderCriteria gender){
        this.gender = gender;
    }
    public List<GenderCriteria> getGenders(){
        return this.genders;
    }

    public void setGenders(List<GenderCriteria> genders){
        this.genders = genders;
    }
    public DoctorCriteria getDoctorInCharge(){
        return this.doctorInCharge;
    }

    public void setDoctorInCharge(DoctorCriteria doctorInCharge){
        this.doctorInCharge = doctorInCharge;
    }
    public List<DoctorCriteria> getDoctorInCharges(){
        return this.doctorInCharges;
    }

    public void setDoctorInCharges(List<DoctorCriteria> doctorInCharges){
        this.doctorInCharges = doctorInCharges;
    }
}
