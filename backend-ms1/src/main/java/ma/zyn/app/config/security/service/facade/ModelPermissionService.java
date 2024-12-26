package ma.zyn.app.config.security.service.facade;

import ma.zyn.app.config.security.bean.ModelPermission;
import ma.zyn.app.config.security.dao.criteria.core.ModelPermissionCriteria;
import ma.zyn.app.config.service.IService;
import java.util.List;



public interface ModelPermissionService extends  IService<ModelPermission,ModelPermissionCriteria>  {
    List<ModelPermission> findAllOptimized();

}
