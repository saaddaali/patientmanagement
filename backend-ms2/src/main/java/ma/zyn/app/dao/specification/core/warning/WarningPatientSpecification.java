package  ma.zyn.app.dao.specification.core.warning;

import ma.zyn.app.dao.criteria.core.warning.WarningPatientCriteria;
import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class WarningPatientSpecification extends  AbstractSpecification<WarningPatientCriteria, WarningPatient>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("dateWarning", criteria.getDateWarning(), criteria.getDateWarningFrom(), criteria.getDateWarningTo());
        addPredicateFk("patient","id", criteria.getPatient()==null?null:criteria.getPatient().getId());
        addPredicateFk("patient","id", criteria.getPatients());
        addPredicateFk("patient","email", criteria.getPatient()==null?null:criteria.getPatient().getEmail());
        addPredicateFk("warningType","id", criteria.getWarningType()==null?null:criteria.getWarningType().getId());
        addPredicateFk("warningType","id", criteria.getWarningTypes());
        addPredicateFk("warningType","code", criteria.getWarningType()==null?null:criteria.getWarningType().getCode());
    }

    public WarningPatientSpecification(WarningPatientCriteria criteria) {
        super(criteria);
    }

    public WarningPatientSpecification(WarningPatientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
