package ma.zyn.app.config.security.service.facade;

import ma.zyn.app.config.security.bean.ActionPermission;
import ma.zyn.app.config.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zyn.app.config.service.IService;
import java.util.List;


public interface ActionPermissionService extends  IService<ActionPermission,ActionPermissionCriteria>  {

    List<ActionPermission> findAllOptimized();

}
