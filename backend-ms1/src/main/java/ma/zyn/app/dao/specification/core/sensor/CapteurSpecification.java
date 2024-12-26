package  ma.zyn.app.dao.specification.core.sensor;

import ma.zyn.app.dao.criteria.core.sensor.CapteurCriteria;
import ma.zyn.app.bean.core.sensor.Capteur;
import ma.zyn.app.config.specification.AbstractSpecification;


public class CapteurSpecification extends  AbstractSpecification<CapteurCriteria, Capteur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("capteurType","id", criteria.getCapteurType()==null?null:criteria.getCapteurType().getId());
        addPredicateFk("capteurType","id", criteria.getCapteurTypes());
        addPredicateFk("capteurType","code", criteria.getCapteurType()==null?null:criteria.getCapteurType().getCode());
    }

    public CapteurSpecification(CapteurCriteria criteria) {
        super(criteria);
    }

    public CapteurSpecification(CapteurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
