package  ma.zyn.app.dao.specification.core.sensor;

import ma.zyn.app.dao.criteria.core.sensor.CapteurTypeCriteria;
import ma.zyn.app.bean.core.sensor.CapteurType;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CapteurTypeSpecification extends  AbstractSpecification<CapteurTypeCriteria, CapteurType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CapteurTypeSpecification(CapteurTypeCriteria criteria) {
        super(criteria);
    }

    public CapteurTypeSpecification(CapteurTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
