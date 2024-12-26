package ma.zyn.app.service.facade.infirmier.sensor;

import java.util.List;
import ma.zyn.app.bean.core.sensor.Capteur;
import ma.zyn.app.dao.criteria.core.sensor.CapteurCriteria;


public interface CapteurInfirmierService {



    List<Capteur> findByCapteurTypeId(Long id);
    int deleteByCapteurTypeId(Long id);
    long countByCapteurTypeCode(String code);




	Capteur create(Capteur t);

    Capteur update(Capteur t);

    List<Capteur> update(List<Capteur> ts,boolean createIfNotExist);

    Capteur findById(Long id);

    Capteur findOrSave(Capteur t);

    Capteur findByReferenceEntity(Capteur t);

    Capteur findWithAssociatedLists(Long id);

    List<Capteur> findAllOptimized();

    List<Capteur> findAll();

    List<Capteur> findByCriteria(CapteurCriteria criteria);

    List<Capteur> findPaginatedByCriteria(CapteurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CapteurCriteria criteria);

    List<Capteur> delete(List<Capteur> ts);

    boolean deleteById(Long id);

    List<List<Capteur>> getToBeSavedAndToBeDeleted(List<Capteur> oldList, List<Capteur> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
