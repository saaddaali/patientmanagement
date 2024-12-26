package ma.zyn.app.config.security.dao.criteria.core;


import ma.zyn.app.config.criteria.BaseCriteria;

import java.util.List;

public class RoleUserCriteria extends  BaseCriteria  {


    private RoleCriteria role ;
    private List<RoleCriteria> roles ;
    private UserCriteria user ;
    private List<UserCriteria> utilisateurs ;


    public RoleUserCriteria(){}


    public RoleCriteria getRole(){
        return this.role;
    }

    public void setRole(RoleCriteria role){
        this.role = role;
    }
    public List<RoleCriteria> getRoles(){
        return this.roles;
    }

    public void setRoles(List<RoleCriteria> roles){
        this.roles = roles;
    }
    public UserCriteria getUser(){
        return this.user;
    }

    public void setUser(UserCriteria user){
        this.user = user;
    }
    public List<UserCriteria> getUsers(){
        return this.utilisateurs;
    }

    public void setUsers(List<UserCriteria> utilisateurs){
        this.utilisateurs = utilisateurs;
    }
}
