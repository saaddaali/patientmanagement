package ma.zyn.app.service.facade.admin.localisation;

import java.util.List;
import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.dao.criteria.core.localisation.LocalisationCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface LocalisationAdminService {



    List<Localisation> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientEmail(String email);
    List<Localisation> findByCapteurId(Long id);
    int deleteByCapteurId(Long id);
    long countByCapteurCode(String code);




	Localisation create(Localisation t);

    Localisation update(Localisation t);

    List<Localisation> update(List<Localisation> ts,boolean createIfNotExist);

    Localisation findById(Long id);

    Localisation findOrSave(Localisation t);

    Localisation findByReferenceEntity(Localisation t);

    Localisation findWithAssociatedLists(Long id);

    List<Localisation> findAllOptimized();

    List<Localisation> findAll();

    List<Localisation> findByCriteria(LocalisationCriteria criteria);

    List<Localisation> findPaginatedByCriteria(LocalisationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(LocalisationCriteria criteria);

    List<Localisation> delete(List<Localisation> ts);

    boolean deleteById(Long id);

    List<List<Localisation>> getToBeSavedAndToBeDeleted(List<Localisation> oldList, List<Localisation> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
