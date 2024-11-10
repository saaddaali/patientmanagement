package  ma.zyn.app.dao.specification.core.patient;

import ma.zyn.app.dao.criteria.core.patient.PatientCriteria;
import ma.zyn.app.bean.core.patient.Patient;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class PatientSpecification extends  AbstractSpecification<PatientCriteria, Patient>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("dateOfBirth", criteria.getDateOfBirth(), criteria.getDateOfBirthFrom(), criteria.getDateOfBirthTo());
        addPredicate("emergencyContact", criteria.getEmergencyContact(),criteria.getEmergencyContactLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateFk("gender","id", criteria.getGender()==null?null:criteria.getGender().getId());
        addPredicateFk("gender","id", criteria.getGenders());
        addPredicateFk("gender","code", criteria.getGender()==null?null:criteria.getGender().getCode());
        addPredicateFk("doctorInCharge","id", criteria.getDoctorInCharge()==null?null:criteria.getDoctorInCharge().getId());
        addPredicateFk("doctorInCharge","id", criteria.getDoctorInCharges());
        addPredicateFk("doctorInCharge","email", criteria.getDoctorInCharge()==null?null:criteria.getDoctorInCharge().getEmail());
    }

    public PatientSpecification(PatientCriteria criteria) {
        super(criteria);
    }

    public PatientSpecification(PatientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
