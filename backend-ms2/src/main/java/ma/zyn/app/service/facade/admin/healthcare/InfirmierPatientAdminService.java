package ma.zyn.app.service.facade.admin.healthcare;

import java.util.List;
import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.dao.criteria.core.healthcare.InfirmierPatientCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface InfirmierPatientAdminService {



    List<InfirmierPatient> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientEmail(String email);
    List<InfirmierPatient> findByInfirmierId(Long id);
    int deleteByInfirmierId(Long id);
    long countByInfirmierEmail(String email);




	InfirmierPatient create(InfirmierPatient t);

    InfirmierPatient update(InfirmierPatient t);

    List<InfirmierPatient> update(List<InfirmierPatient> ts,boolean createIfNotExist);

    InfirmierPatient findById(Long id);

    InfirmierPatient findOrSave(InfirmierPatient t);

    InfirmierPatient findByReferenceEntity(InfirmierPatient t);

    InfirmierPatient findWithAssociatedLists(Long id);

    List<InfirmierPatient> findAllOptimized();

    List<InfirmierPatient> findAll();

    List<InfirmierPatient> findByCriteria(InfirmierPatientCriteria criteria);

    List<InfirmierPatient> findPaginatedByCriteria(InfirmierPatientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InfirmierPatientCriteria criteria);

    List<InfirmierPatient> delete(List<InfirmierPatient> ts);

    boolean deleteById(Long id);

    List<List<InfirmierPatient>> getToBeSavedAndToBeDeleted(List<InfirmierPatient> oldList, List<InfirmierPatient> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
