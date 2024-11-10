package  ma.zyn.app.dao.specification.core.localisation;

import ma.zyn.app.dao.criteria.core.localisation.LocalisationCriteria;
import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class LocalisationSpecification extends  AbstractSpecification<LocalisationCriteria, Localisation>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("dateLocalisation", criteria.getDateLocalisation(), criteria.getDateLocalisationFrom(), criteria.getDateLocalisationTo());
        addPredicateBigDecimal("longitude", criteria.getLongitude(), criteria.getLongitudeMin(), criteria.getLongitudeMax());
        addPredicateBigDecimal("latitude", criteria.getLatitude(), criteria.getLatitudeMin(), criteria.getLatitudeMax());
        addPredicateFk("patient","id", criteria.getPatient()==null?null:criteria.getPatient().getId());
        addPredicateFk("patient","id", criteria.getPatients());
        addPredicateFk("patient","email", criteria.getPatient()==null?null:criteria.getPatient().getEmail());
        addPredicateFk("capteur","id", criteria.getCapteur()==null?null:criteria.getCapteur().getId());
        addPredicateFk("capteur","id", criteria.getCapteurs());
        addPredicateFk("capteur","code", criteria.getCapteur()==null?null:criteria.getCapteur().getCode());
    }

    public LocalisationSpecification(LocalisationCriteria criteria) {
        super(criteria);
    }

    public LocalisationSpecification(LocalisationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
