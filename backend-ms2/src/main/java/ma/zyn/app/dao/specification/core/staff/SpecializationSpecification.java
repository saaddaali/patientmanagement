package  ma.zyn.app.dao.specification.core.staff;

import ma.zyn.app.dao.criteria.core.staff.SpecializationCriteria;
import ma.zyn.app.bean.core.staff.Specialization;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class SpecializationSpecification extends  AbstractSpecification<SpecializationCriteria, Specialization>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public SpecializationSpecification(SpecializationCriteria criteria) {
        super(criteria);
    }

    public SpecializationSpecification(SpecializationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
