package ma.zyn.app.config.security.service.facade;

import ma.zyn.app.config.security.bean.RoleUser;
import ma.zyn.app.config.security.dao.criteria.core.RoleUserCriteria;
import ma.zyn.app.config.service.IService;

import java.util.List;



public interface RoleUserService extends  IService<RoleUser,RoleUserCriteria>  {

    List<RoleUser> findByRoleId(Long id);
    int deleteByRoleId(Long id);
    long countByRoleAuthority(String authority);
    List<RoleUser> findByUserId(Long id);
    int deleteByUserId(Long id);
    long countByUserEmail(String email);



}
