package ma.zyn.app.service.facade.infirmier.sensor;

import java.util.List;
import ma.zyn.app.bean.core.sensor.CapteurType;
import ma.zyn.app.dao.criteria.core.sensor.CapteurTypeCriteria;


public interface CapteurTypeInfirmierService {







	CapteurType create(CapteurType t);

    CapteurType update(CapteurType t);

    List<CapteurType> update(List<CapteurType> ts,boolean createIfNotExist);

    CapteurType findById(Long id);

    CapteurType findOrSave(CapteurType t);

    CapteurType findByReferenceEntity(CapteurType t);

    CapteurType findWithAssociatedLists(Long id);

    List<CapteurType> findAllOptimized();

    List<CapteurType> findAll();

    List<CapteurType> findByCriteria(CapteurTypeCriteria criteria);

    List<CapteurType> findPaginatedByCriteria(CapteurTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CapteurTypeCriteria criteria);

    List<CapteurType> delete(List<CapteurType> ts);

    boolean deleteById(Long id);

    List<List<CapteurType>> getToBeSavedAndToBeDeleted(List<CapteurType> oldList, List<CapteurType> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
