package ma.zyn.app.config.security.service.facade;

import ma.zyn.app.config.security.bean.Role;
import ma.zyn.app.config.security.dao.criteria.core.RoleCriteria;
import ma.zyn.app.config.service.IService;



public interface RoleService extends  IService<Role,RoleCriteria>  {
    Role findByAuthority(String authority);
    int deleteByAuthority(String authority);


    



}
