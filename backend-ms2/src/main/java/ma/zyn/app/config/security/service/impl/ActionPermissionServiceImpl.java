package ma.zyn.app.config.security.service.impl;


import ma.zyn.app.config.security.bean.ActionPermission;
import ma.zyn.app.config.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zyn.app.config.security.dao.facade.core.ActionPermissionDao;
import ma.zyn.app.config.security.dao.specification.core.ActionPermissionSpecification;
import ma.zyn.app.config.security.service.facade.ActionPermissionService;
import ma.zyn.app.config.service.AbstractServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionPermissionServiceImpl extends AbstractServiceImpl<ActionPermission, ActionPermissionCriteria, ActionPermissionDao> implements ActionPermissionService {





    public ActionPermission findByReferenceEntity(ActionPermission t){
        return  dao.findByReference(t.getReference());
    }


    public List<ActionPermission> findAllOptimized() {
        return dao.findAllOptimized();
    }





    public void configure() {
        super.configure(ActionPermission.class, ActionPermissionSpecification.class);
    }



    public ActionPermissionServiceImpl(ActionPermissionDao dao) {
        super(dao);
    }

}
