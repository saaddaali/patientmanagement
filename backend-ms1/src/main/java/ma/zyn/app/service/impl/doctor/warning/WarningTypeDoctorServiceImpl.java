package ma.zyn.app.service.impl.doctor.warning;


import ma.zyn.app.config.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.warning.WarningType;
import ma.zyn.app.dao.criteria.core.warning.WarningTypeCriteria;
import ma.zyn.app.dao.facade.core.warning.WarningTypeDao;
import ma.zyn.app.dao.specification.core.warning.WarningTypeSpecification;
import ma.zyn.app.service.facade.doctor.warning.WarningTypeDoctorService;

import static ma.zyn.app.config.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.config.util.RefelexivityUtil;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WarningTypeDoctorServiceImpl implements WarningTypeDoctorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public WarningType update(WarningType t) {
        WarningType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{WarningType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public WarningType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public WarningType findOrSave(WarningType t) {
        if (t != null) {
            WarningType result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<WarningType> findAll() {
        return dao.findAll();
    }

    public List<WarningType> findByCriteria(WarningTypeCriteria criteria) {
        List<WarningType> content = null;
        if (criteria != null) {
            WarningTypeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private WarningTypeSpecification constructSpecification(WarningTypeCriteria criteria) {
        WarningTypeSpecification mySpecification =  (WarningTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(WarningTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<WarningType> findPaginatedByCriteria(WarningTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        WarningTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(WarningTypeCriteria criteria) {
        WarningTypeSpecification mySpecification = constructSpecification(criteria);
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
    public List<WarningType> delete(List<WarningType> list) {
		List<WarningType> result = new ArrayList();
        if (list != null) {
            for (WarningType t : list) {
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
    public WarningType create(WarningType t) {
        WarningType loaded = findByReferenceEntity(t);
        WarningType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public WarningType findWithAssociatedLists(Long id){
        WarningType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<WarningType> update(List<WarningType> ts, boolean createIfNotExist) {
        List<WarningType> result = new ArrayList<>();
        if (ts != null) {
            for (WarningType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    WarningType loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, WarningType t, WarningType loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public WarningType findByReferenceEntity(WarningType t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<WarningType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<WarningType>> getToBeSavedAndToBeDeleted(List<WarningType> oldList, List<WarningType> newList) {
        List<List<WarningType>> result = new ArrayList<>();
        List<WarningType> resultDelete = new ArrayList<>();
        List<WarningType> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<WarningType> oldList, List<WarningType> newList, List<WarningType> resultUpdateOrSave, List<WarningType> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                WarningType myOld = oldList.get(i);
                WarningType t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                WarningType myNew = newList.get(i);
                WarningType t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public WarningTypeDoctorServiceImpl(WarningTypeDao dao) {
        this.dao = dao;
    }

    private WarningTypeDao dao;
}
