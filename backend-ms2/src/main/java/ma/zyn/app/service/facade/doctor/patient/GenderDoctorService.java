package ma.zyn.app.service.facade.doctor.patient;

import java.util.List;
import ma.zyn.app.bean.core.patient.Gender;
import ma.zyn.app.dao.criteria.core.patient.GenderCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface GenderDoctorService {







	Gender create(Gender t);

    Gender update(Gender t);

    List<Gender> update(List<Gender> ts,boolean createIfNotExist);

    Gender findById(Long id);

    Gender findOrSave(Gender t);

    Gender findByReferenceEntity(Gender t);

    Gender findWithAssociatedLists(Long id);

    List<Gender> findAllOptimized();

    List<Gender> findAll();

    List<Gender> findByCriteria(GenderCriteria criteria);

    List<Gender> findPaginatedByCriteria(GenderCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(GenderCriteria criteria);

    List<Gender> delete(List<Gender> ts);

    boolean deleteById(Long id);

    List<List<Gender>> getToBeSavedAndToBeDeleted(List<Gender> oldList, List<Gender> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
