package  ma.zyn.app.dao.specification.core.localisation;

import ma.zyn.app.dao.criteria.core.localisation.SafeZoneCriteria;
import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.config.specification.AbstractSpecification;


public class SafeZoneSpecification extends  AbstractSpecification<SafeZoneCriteria, SafeZone>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("centreLongitude", criteria.getCentreLongitude(), criteria.getCentreLongitudeMin(), criteria.getCentreLongitudeMax());
        addPredicateBigDecimal("centreLatitude", criteria.getCentreLatitude(), criteria.getCentreLatitudeMin(), criteria.getCentreLatitudeMax());
        addPredicateBigDecimal("rayon", criteria.getRayon(), criteria.getRayonMin(), criteria.getRayonMax());
        addPredicateFk("linkedPatient","id", criteria.getLinkedPatient()==null?null:criteria.getLinkedPatient().getId());
        addPredicateFk("linkedPatient","id", criteria.getLinkedPatients());
        addPredicateFk("linkedPatient","email", criteria.getLinkedPatient()==null?null:criteria.getLinkedPatient().getEmail());
    }

    public SafeZoneSpecification(SafeZoneCriteria criteria) {
        super(criteria);
    }

    public SafeZoneSpecification(SafeZoneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
