package ma.zyn.app.service.facade.doctor.staff;

import java.util.List;
import ma.zyn.app.bean.core.staff.Specialization;
import ma.zyn.app.dao.criteria.core.staff.SpecializationCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface SpecializationDoctorService {







	Specialization create(Specialization t);

    Specialization update(Specialization t);

    List<Specialization> update(List<Specialization> ts,boolean createIfNotExist);

    Specialization findById(Long id);

    Specialization findOrSave(Specialization t);

    Specialization findByReferenceEntity(Specialization t);

    Specialization findWithAssociatedLists(Long id);

    List<Specialization> findAllOptimized();

    List<Specialization> findAll();

    List<Specialization> findByCriteria(SpecializationCriteria criteria);

    List<Specialization> findPaginatedByCriteria(SpecializationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SpecializationCriteria criteria);

    List<Specialization> delete(List<Specialization> ts);

    boolean deleteById(Long id);

    List<List<Specialization>> getToBeSavedAndToBeDeleted(List<Specialization> oldList, List<Specialization> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
