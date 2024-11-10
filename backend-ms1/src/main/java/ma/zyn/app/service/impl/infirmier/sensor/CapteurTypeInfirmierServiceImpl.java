package ma.zyn.app.service.impl.infirmier.sensor;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.sensor.CapteurType;
import ma.zyn.app.dao.criteria.core.sensor.CapteurTypeCriteria;
import ma.zyn.app.dao.facade.core.sensor.CapteurTypeDao;
import ma.zyn.app.dao.specification.core.sensor.CapteurTypeSpecification;
import ma.zyn.app.service.facade.infirmier.sensor.CapteurTypeInfirmierService;
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
public class CapteurTypeInfirmierServiceImpl implements CapteurTypeInfirmierService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CapteurType update(CapteurType t) {
        CapteurType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CapteurType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CapteurType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CapteurType findOrSave(CapteurType t) {
        if (t != null) {
            CapteurType result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CapteurType> findAll() {
        return dao.findAll();
    }

    public List<CapteurType> findByCriteria(CapteurTypeCriteria criteria) {
        List<CapteurType> content = null;
        if (criteria != null) {
            CapteurTypeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CapteurTypeSpecification constructSpecification(CapteurTypeCriteria criteria) {
        CapteurTypeSpecification mySpecification =  (CapteurTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(CapteurTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<CapteurType> findPaginatedByCriteria(CapteurTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        CapteurTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CapteurTypeCriteria criteria) {
        CapteurTypeSpecification mySpecification = constructSpecification(criteria);
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
    public List<CapteurType> delete(List<CapteurType> list) {
		List<CapteurType> result = new ArrayList();
        if (list != null) {
            for (CapteurType t : list) {
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
    public CapteurType create(CapteurType t) {
        CapteurType loaded = findByReferenceEntity(t);
        CapteurType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CapteurType findWithAssociatedLists(Long id){
        CapteurType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CapteurType> update(List<CapteurType> ts, boolean createIfNotExist) {
        List<CapteurType> result = new ArrayList<>();
        if (ts != null) {
            for (CapteurType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CapteurType loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CapteurType t, CapteurType loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CapteurType findByReferenceEntity(CapteurType t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CapteurType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CapteurType>> getToBeSavedAndToBeDeleted(List<CapteurType> oldList, List<CapteurType> newList) {
        List<List<CapteurType>> result = new ArrayList<>();
        List<CapteurType> resultDelete = new ArrayList<>();
        List<CapteurType> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CapteurType> oldList, List<CapteurType> newList, List<CapteurType> resultUpdateOrSave, List<CapteurType> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CapteurType myOld = oldList.get(i);
                CapteurType t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CapteurType myNew = newList.get(i);
                CapteurType t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CapteurTypeInfirmierServiceImpl(CapteurTypeDao dao) {
        this.dao = dao;
    }

    private CapteurTypeDao dao;
}
