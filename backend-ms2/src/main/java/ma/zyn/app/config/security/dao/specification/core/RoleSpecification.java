package ma.zyn.app.config.security.dao.specification.core;

import ma.zyn.app.config.security.bean.Role;
import ma.zyn.app.config.security.dao.criteria.core.RoleCriteria;
import ma.zyn.app.config.specification.AbstractSpecification;


public class RoleSpecification extends  AbstractSpecification<RoleCriteria, Role>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("authority", criteria.getAuthority(),criteria.getAuthorityLike());
    }

    public RoleSpecification(RoleCriteria criteria) {
        super(criteria);
    }

    public RoleSpecification(RoleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
