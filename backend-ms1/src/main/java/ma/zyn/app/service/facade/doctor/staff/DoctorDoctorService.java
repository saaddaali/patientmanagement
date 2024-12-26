package ma.zyn.app.service.facade.doctor.staff;

import java.util.List;
import ma.zyn.app.bean.core.staff.Doctor;
import ma.zyn.app.dao.criteria.core.staff.DoctorCriteria;


public interface DoctorDoctorService {


    Doctor findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Doctor> findBySpecializationId(Long id);
    int deleteBySpecializationId(Long id);
    long countBySpecializationCode(String code);




	Doctor create(Doctor t);

    Doctor update(Doctor t);

    List<Doctor> update(List<Doctor> ts,boolean createIfNotExist);

    Doctor findById(Long id);

    Doctor findOrSave(Doctor t);

    Doctor findByReferenceEntity(Doctor t);

    Doctor findWithAssociatedLists(Long id);

    List<Doctor> findAllOptimized();

    List<Doctor> findAll();

    List<Doctor> findByCriteria(DoctorCriteria criteria);

    List<Doctor> findPaginatedByCriteria(DoctorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DoctorCriteria criteria);

    List<Doctor> delete(List<Doctor> ts);

    boolean deleteById(Long id);

    List<List<Doctor>> getToBeSavedAndToBeDeleted(List<Doctor> oldList, List<Doctor> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
