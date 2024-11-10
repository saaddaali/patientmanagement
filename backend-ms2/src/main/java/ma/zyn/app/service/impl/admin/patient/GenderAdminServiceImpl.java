package ma.zyn.app.service.impl.admin.patient;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.patient.Gender;
import ma.zyn.app.dao.criteria.core.patient.GenderCriteria;
import ma.zyn.app.dao.facade.core.patient.GenderDao;
import ma.zyn.app.dao.specification.core.patient.GenderSpecification;
import ma.zyn.app.service.facade.admin.patient.GenderAdminService;
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
public class GenderAdminServiceImpl implements GenderAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Gender update(Gender t) {
        Gender loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Gender.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Gender findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Gender findOrSave(Gender t) {
        if (t != null) {
            Gender result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Gender> findAll() {
        return dao.findAll();
    }

    public List<Gender> findByCriteria(GenderCriteria criteria) {
        List<Gender> content = null;
        if (criteria != null) {
            GenderSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private GenderSpecification constructSpecification(GenderCriteria criteria) {
        GenderSpecification mySpecification =  (GenderSpecification) RefelexivityUtil.constructObjectUsingOneParam(GenderSpecification.class, criteria);
        return mySpecification;
    }

    public List<Gender> findPaginatedByCriteria(GenderCriteria criteria, int page, int pageSize, String order, String sortField) {
        GenderSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(GenderCriteria criteria) {
        GenderSpecification mySpecification = constructSpecification(criteria);
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
    public List<Gender> delete(List<Gender> list) {
		List<Gender> result = new ArrayList();
        if (list != null) {
            for (Gender t : list) {
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
    public Gender create(Gender t) {
        Gender loaded = findByReferenceEntity(t);
        Gender saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Gender findWithAssociatedLists(Long id){
        Gender result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Gender> update(List<Gender> ts, boolean createIfNotExist) {
        List<Gender> result = new ArrayList<>();
        if (ts != null) {
            for (Gender t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Gender loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Gender t, Gender loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Gender findByReferenceEntity(Gender t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Gender> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Gender>> getToBeSavedAndToBeDeleted(List<Gender> oldList, List<Gender> newList) {
        List<List<Gender>> result = new ArrayList<>();
        List<Gender> resultDelete = new ArrayList<>();
        List<Gender> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Gender> oldList, List<Gender> newList, List<Gender> resultUpdateOrSave, List<Gender> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Gender myOld = oldList.get(i);
                Gender t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Gender myNew = newList.get(i);
                Gender t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public GenderAdminServiceImpl(GenderDao dao) {
        this.dao = dao;
    }

    private GenderDao dao;
}
