package  ma.zyn.app.dao.specification.core.staff;

import ma.zyn.app.dao.criteria.core.staff.InfirmierCriteria;
import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.config.specification.AbstractSpecification;


public class InfirmierSpecification extends  AbstractSpecification<InfirmierCriteria, Infirmier>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateFk("specialization","id", criteria.getSpecialization()==null?null:criteria.getSpecialization().getId());
        addPredicateFk("specialization","id", criteria.getSpecializations());
        addPredicateFk("specialization","code", criteria.getSpecialization()==null?null:criteria.getSpecialization().getCode());
    }

    public InfirmierSpecification(InfirmierCriteria criteria) {
        super(criteria);
    }

    public InfirmierSpecification(InfirmierCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
