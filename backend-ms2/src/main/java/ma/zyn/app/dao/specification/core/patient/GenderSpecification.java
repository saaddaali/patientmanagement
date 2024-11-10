package  ma.zyn.app.dao.specification.core.patient;

import ma.zyn.app.dao.criteria.core.patient.GenderCriteria;
import ma.zyn.app.bean.core.patient.Gender;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class GenderSpecification extends  AbstractSpecification<GenderCriteria, Gender>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public GenderSpecification(GenderCriteria criteria) {
        super(criteria);
    }

    public GenderSpecification(GenderCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
