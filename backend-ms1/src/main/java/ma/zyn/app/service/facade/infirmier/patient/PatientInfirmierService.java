package ma.zyn.app.service.facade.infirmier.patient;

import java.util.List;
import ma.zyn.app.bean.core.patient.Patient;
import ma.zyn.app.dao.criteria.core.patient.PatientCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface PatientInfirmierService {


    Patient findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Patient> findByGenderId(Long id);
    int deleteByGenderId(Long id);
    long countByGenderCode(String code);
    List<Patient> findByDoctorInChargeId(Long id);
    int deleteByDoctorInChargeId(Long id);
    long countByDoctorInChargeEmail(String email);




	Patient create(Patient t);

    Patient update(Patient t);

    List<Patient> update(List<Patient> ts,boolean createIfNotExist);

    Patient findById(Long id);

    Patient findOrSave(Patient t);

    Patient findByReferenceEntity(Patient t);

    Patient findWithAssociatedLists(Long id);

    List<Patient> findAllOptimized();

    List<Patient> findAll();

    List<Patient> findByCriteria(PatientCriteria criteria);

    List<Patient> findPaginatedByCriteria(PatientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PatientCriteria criteria);

    List<Patient> delete(List<Patient> ts);

    boolean deleteById(Long id);

    List<List<Patient>> getToBeSavedAndToBeDeleted(List<Patient> oldList, List<Patient> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
