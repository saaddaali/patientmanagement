package  ma.zyn.app.dao.specification.core.warning;

import ma.zyn.app.dao.criteria.core.warning.WarningTypeCriteria;
import ma.zyn.app.bean.core.warning.WarningType;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class WarningTypeSpecification extends  AbstractSpecification<WarningTypeCriteria, WarningType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public WarningTypeSpecification(WarningTypeCriteria criteria) {
        super(criteria);
    }

    public WarningTypeSpecification(WarningTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
