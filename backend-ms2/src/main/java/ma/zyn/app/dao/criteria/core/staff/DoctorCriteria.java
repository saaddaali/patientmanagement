package  ma.zyn.app.dao.criteria.core.staff;



import ma.zyn.app.config.security.dao.criteria.core.UserCriteria;

import java.util.List;

public class DoctorCriteria extends UserCriteria  {

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

    private SpecializationCriteria specialization ;
    private List<SpecializationCriteria> specializations ;


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


    public SpecializationCriteria getSpecialization(){
        return this.specialization;
    }

    public void setSpecialization(SpecializationCriteria specialization){
        this.specialization = specialization;
    }
    public List<SpecializationCriteria> getSpecializations(){
        return this.specializations;
    }

    public void setSpecializations(List<SpecializationCriteria> specializations){
        this.specializations = specializations;
    }
}
