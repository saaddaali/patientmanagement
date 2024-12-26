package  ma.zyn.app.ws.dto.staff;

import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.config.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.config.security.ws.dto.UserDto;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfirmierDto  extends UserDto {


    private SpecializationDto specialization ;



    private Collection<Role> roles;
    public InfirmierDto(){
        super();
    }












    public SpecializationDto getSpecialization(){
        return this.specialization;
    }

    public void setSpecialization(SpecializationDto specialization){
        this.specialization = specialization;
    }







    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
