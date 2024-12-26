package ma.zyn.app.service.facade.admin.staff;

import java.util.List;
import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.dao.criteria.core.staff.InfirmierCriteria;


public interface InfirmierAdminService {


    Infirmier findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Infirmier> findBySpecializationId(Long id);
    int deleteBySpecializationId(Long id);
    long countBySpecializationCode(String code);




	Infirmier create(Infirmier t);

    Infirmier update(Infirmier t);

    List<Infirmier> update(List<Infirmier> ts,boolean createIfNotExist);

    Infirmier findById(Long id);

    Infirmier findOrSave(Infirmier t);

    Infirmier findByReferenceEntity(Infirmier t);

    Infirmier findWithAssociatedLists(Long id);

    List<Infirmier> findAllOptimized();

    List<Infirmier> findAll();

    List<Infirmier> findByCriteria(InfirmierCriteria criteria);

    List<Infirmier> findPaginatedByCriteria(InfirmierCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InfirmierCriteria criteria);

    List<Infirmier> delete(List<Infirmier> ts);

    boolean deleteById(Long id);

    List<List<Infirmier>> getToBeSavedAndToBeDeleted(List<Infirmier> oldList, List<Infirmier> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
