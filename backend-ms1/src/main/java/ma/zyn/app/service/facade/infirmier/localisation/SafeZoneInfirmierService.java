package ma.zyn.app.service.facade.infirmier.localisation;

import java.util.List;
import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.dao.criteria.core.localisation.SafeZoneCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface SafeZoneInfirmierService {



    List<SafeZone> findByLinkedPatientId(Long id);
    int deleteByLinkedPatientId(Long id);
    long countByLinkedPatientEmail(String email);




	SafeZone create(SafeZone t);

    SafeZone update(SafeZone t);

    List<SafeZone> update(List<SafeZone> ts,boolean createIfNotExist);

    SafeZone findById(Long id);

    SafeZone findOrSave(SafeZone t);

    SafeZone findByReferenceEntity(SafeZone t);

    SafeZone findWithAssociatedLists(Long id);

    List<SafeZone> findAllOptimized();

    List<SafeZone> findAll();

    List<SafeZone> findByCriteria(SafeZoneCriteria criteria);

    List<SafeZone> findPaginatedByCriteria(SafeZoneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SafeZoneCriteria criteria);

    List<SafeZone> delete(List<SafeZone> ts);

    boolean deleteById(Long id);

    List<List<SafeZone>> getToBeSavedAndToBeDeleted(List<SafeZone> oldList, List<SafeZone> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
