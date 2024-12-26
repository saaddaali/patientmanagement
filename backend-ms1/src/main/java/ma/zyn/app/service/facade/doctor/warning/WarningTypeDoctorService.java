package ma.zyn.app.service.facade.doctor.warning;

import java.util.List;
import ma.zyn.app.bean.core.warning.WarningType;
import ma.zyn.app.dao.criteria.core.warning.WarningTypeCriteria;


public interface WarningTypeDoctorService {







	WarningType create(WarningType t);

    WarningType update(WarningType t);

    List<WarningType> update(List<WarningType> ts,boolean createIfNotExist);

    WarningType findById(Long id);

    WarningType findOrSave(WarningType t);

    WarningType findByReferenceEntity(WarningType t);

    WarningType findWithAssociatedLists(Long id);

    List<WarningType> findAllOptimized();

    List<WarningType> findAll();

    List<WarningType> findByCriteria(WarningTypeCriteria criteria);

    List<WarningType> findPaginatedByCriteria(WarningTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(WarningTypeCriteria criteria);

    List<WarningType> delete(List<WarningType> ts);

    boolean deleteById(Long id);

    List<List<WarningType>> getToBeSavedAndToBeDeleted(List<WarningType> oldList, List<WarningType> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
