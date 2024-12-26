package ma.zyn.app.service.impl.doctor.sensor;


import ma.zyn.app.config.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.sensor.Capteur;
import ma.zyn.app.dao.criteria.core.sensor.CapteurCriteria;
import ma.zyn.app.dao.facade.core.sensor.CapteurDao;
import ma.zyn.app.dao.specification.core.sensor.CapteurSpecification;
import ma.zyn.app.service.facade.doctor.sensor.CapteurDoctorService;

import static ma.zyn.app.config.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.config.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.doctor.sensor.CapteurTypeDoctorService ;

@Service
public class CapteurDoctorServiceImpl implements CapteurDoctorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Capteur update(Capteur t) {
        Capteur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Capteur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Capteur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Capteur findOrSave(Capteur t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Capteur result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Capteur> findAll() {
        return dao.findAll();
    }

    public List<Capteur> findByCriteria(CapteurCriteria criteria) {
        List<Capteur> content = null;
        if (criteria != null) {
            CapteurSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CapteurSpecification constructSpecification(CapteurCriteria criteria) {
        CapteurSpecification mySpecification =  (CapteurSpecification) RefelexivityUtil.constructObjectUsingOneParam(CapteurSpecification.class, criteria);
        return mySpecification;
    }

    public List<Capteur> findPaginatedByCriteria(CapteurCriteria criteria, int page, int pageSize, String order, String sortField) {
        CapteurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CapteurCriteria criteria) {
        CapteurSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Capteur> findByCapteurTypeId(Long id){
        return dao.findByCapteurTypeId(id);
    }
    public int deleteByCapteurTypeId(Long id){
        return dao.deleteByCapteurTypeId(id);
    }
    public long countByCapteurTypeCode(String code){
        return dao.countByCapteurTypeCode(code);
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
    public List<Capteur> delete(List<Capteur> list) {
		List<Capteur> result = new ArrayList();
        if (list != null) {
            for (Capteur t : list) {
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
    public Capteur create(Capteur t) {
        Capteur loaded = findByReferenceEntity(t);
        Capteur saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Capteur findWithAssociatedLists(Long id){
        Capteur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Capteur> update(List<Capteur> ts, boolean createIfNotExist) {
        List<Capteur> result = new ArrayList<>();
        if (ts != null) {
            for (Capteur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Capteur loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Capteur t, Capteur loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Capteur findByReferenceEntity(Capteur t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Capteur t){
        if( t != null) {
        }
    }



    public List<Capteur> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Capteur>> getToBeSavedAndToBeDeleted(List<Capteur> oldList, List<Capteur> newList) {
        List<List<Capteur>> result = new ArrayList<>();
        List<Capteur> resultDelete = new ArrayList<>();
        List<Capteur> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Capteur> oldList, List<Capteur> newList, List<Capteur> resultUpdateOrSave, List<Capteur> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Capteur myOld = oldList.get(i);
                Capteur t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Capteur myNew = newList.get(i);
                Capteur t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private CapteurTypeDoctorService capteurTypeService ;

    public CapteurDoctorServiceImpl(CapteurDao dao) {
        this.dao = dao;
    }

    private CapteurDao dao;
}
