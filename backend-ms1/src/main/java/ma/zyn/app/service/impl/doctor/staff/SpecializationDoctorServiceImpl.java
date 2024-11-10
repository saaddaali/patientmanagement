package ma.zyn.app.service.impl.doctor.staff;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.staff.Specialization;
import ma.zyn.app.dao.criteria.core.staff.SpecializationCriteria;
import ma.zyn.app.dao.facade.core.staff.SpecializationDao;
import ma.zyn.app.dao.specification.core.staff.SpecializationSpecification;
import ma.zyn.app.service.facade.doctor.staff.SpecializationDoctorService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class SpecializationDoctorServiceImpl implements SpecializationDoctorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Specialization update(Specialization t) {
        Specialization loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Specialization.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Specialization findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Specialization findOrSave(Specialization t) {
        if (t != null) {
            Specialization result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Specialization> findAll() {
        return dao.findAll();
    }

    public List<Specialization> findByCriteria(SpecializationCriteria criteria) {
        List<Specialization> content = null;
        if (criteria != null) {
            SpecializationSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SpecializationSpecification constructSpecification(SpecializationCriteria criteria) {
        SpecializationSpecification mySpecification =  (SpecializationSpecification) RefelexivityUtil.constructObjectUsingOneParam(SpecializationSpecification.class, criteria);
        return mySpecification;
    }

    public List<Specialization> findPaginatedByCriteria(SpecializationCriteria criteria, int page, int pageSize, String order, String sortField) {
        SpecializationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SpecializationCriteria criteria) {
        SpecializationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Specialization> delete(List<Specialization> list) {
		List<Specialization> result = new ArrayList();
        if (list != null) {
            for (Specialization t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Specialization create(Specialization t) {
        Specialization loaded = findByReferenceEntity(t);
        Specialization saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Specialization findWithAssociatedLists(Long id){
        Specialization result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Specialization> update(List<Specialization> ts, boolean createIfNotExist) {
        List<Specialization> result = new ArrayList<>();
        if (ts != null) {
            for (Specialization t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Specialization loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Specialization t, Specialization loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Specialization findByReferenceEntity(Specialization t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Specialization> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Specialization>> getToBeSavedAndToBeDeleted(List<Specialization> oldList, List<Specialization> newList) {
        List<List<Specialization>> result = new ArrayList<>();
        List<Specialization> resultDelete = new ArrayList<>();
        List<Specialization> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Specialization> oldList, List<Specialization> newList, List<Specialization> resultUpdateOrSave, List<Specialization> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Specialization myOld = oldList.get(i);
                Specialization t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Specialization myNew = newList.get(i);
                Specialization t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public SpecializationDoctorServiceImpl(SpecializationDao dao) {
        this.dao = dao;
    }

    private SpecializationDao dao;
}
