package ma.zyn.app.service.facade.doctor.warning;

import java.util.List;
import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.dao.criteria.core.warning.WarningPatientCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface WarningPatientDoctorService {



    List<WarningPatient> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientEmail(String email);
    List<WarningPatient> findByWarningTypeId(Long id);
    int deleteByWarningTypeId(Long id);
    long countByWarningTypeCode(String code);




	WarningPatient create(WarningPatient t);

    WarningPatient update(WarningPatient t);

    List<WarningPatient> update(List<WarningPatient> ts,boolean createIfNotExist);

    WarningPatient findById(Long id);

    WarningPatient findOrSave(WarningPatient t);

    WarningPatient findByReferenceEntity(WarningPatient t);

    WarningPatient findWithAssociatedLists(Long id);

    List<WarningPatient> findAllOptimized();

    List<WarningPatient> findAll();

    List<WarningPatient> findByCriteria(WarningPatientCriteria criteria);

    List<WarningPatient> findPaginatedByCriteria(WarningPatientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(WarningPatientCriteria criteria);

    List<WarningPatient> delete(List<WarningPatient> ts);

    boolean deleteById(Long id);

    List<List<WarningPatient>> getToBeSavedAndToBeDeleted(List<WarningPatient> oldList, List<WarningPatient> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
