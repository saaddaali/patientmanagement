package  ma.zyn.app.dao.specification.core.healthcare;

import ma.zyn.app.dao.criteria.core.healthcare.InfirmierPatientCriteria;
import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.config.specification.AbstractSpecification;


public class InfirmierPatientSpecification extends  AbstractSpecification<InfirmierPatientCriteria, InfirmierPatient>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
        addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
        addPredicateFk("patient","id", criteria.getPatient()==null?null:criteria.getPatient().getId());
        addPredicateFk("patient","id", criteria.getPatients());
        addPredicateFk("patient","email", criteria.getPatient()==null?null:criteria.getPatient().getEmail());
        addPredicateFk("infirmier","id", criteria.getInfirmier()==null?null:criteria.getInfirmier().getId());
        addPredicateFk("infirmier","id", criteria.getInfirmiers());
        addPredicateFk("infirmier","email", criteria.getInfirmier()==null?null:criteria.getInfirmier().getEmail());
    }

    public InfirmierPatientSpecification(InfirmierPatientCriteria criteria) {
        super(criteria);
    }

    public InfirmierPatientSpecification(InfirmierPatientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
