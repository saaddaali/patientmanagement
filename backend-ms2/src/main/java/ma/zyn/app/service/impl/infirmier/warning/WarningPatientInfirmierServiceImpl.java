package ma.zyn.app.service.impl.infirmier.warning;


import ma.zyn.app.config.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.dao.criteria.core.warning.WarningPatientCriteria;
import ma.zyn.app.dao.facade.core.warning.WarningPatientDao;
import ma.zyn.app.dao.specification.core.warning.WarningPatientSpecification;
import ma.zyn.app.service.facade.infirmier.warning.WarningPatientInfirmierService;

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

import ma.zyn.app.service.facade.infirmier.patient.PatientInfirmierService ;
import ma.zyn.app.service.facade.infirmier.warning.WarningTypeInfirmierService ;

@Service
public class WarningPatientInfirmierServiceImpl implements WarningPatientInfirmierService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public WarningPatient update(WarningPatient t) {
        WarningPatient loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{WarningPatient.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public WarningPatient findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public WarningPatient findOrSave(WarningPatient t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            WarningPatient result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<WarningPatient> findAll() {
        return dao.findAll();
    }

    public List<WarningPatient> findByCriteria(WarningPatientCriteria criteria) {
        List<WarningPatient> content = null;
        if (criteria != null) {
            WarningPatientSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private WarningPatientSpecification constructSpecification(WarningPatientCriteria criteria) {
        WarningPatientSpecification mySpecification =  (WarningPatientSpecification) RefelexivityUtil.constructObjectUsingOneParam(WarningPatientSpecification.class, criteria);
        return mySpecification;
    }

    public List<WarningPatient> findPaginatedByCriteria(WarningPatientCriteria criteria, int page, int pageSize, String order, String sortField) {
        WarningPatientSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(WarningPatientCriteria criteria) {
        WarningPatientSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<WarningPatient> findByPatientId(Long id){
        return dao.findByPatientId(id);
    }
    public int deleteByPatientId(Long id){
        return dao.deleteByPatientId(id);
    }
    public long countByPatientEmail(String email){
        return dao.countByPatientEmail(email);
    }
    public List<WarningPatient> findByWarningTypeId(Long id){
        return dao.findByWarningTypeId(id);
    }
    public int deleteByWarningTypeId(Long id){
        return dao.deleteByWarningTypeId(id);
    }
    public long countByWarningTypeCode(String code){
        return dao.countByWarningTypeCode(code);
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
    public List<WarningPatient> delete(List<WarningPatient> list) {
		List<WarningPatient> result = new ArrayList();
        if (list != null) {
            for (WarningPatient t : list) {
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
    public WarningPatient create(WarningPatient t) {
        if (t.getPatient() != null) {
            t.getPatient().setId(null);
            t.setPatient(patientService.create(t.getPatient()));
        }
        WarningPatient loaded = findByReferenceEntity(t);
        WarningPatient saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public WarningPatient findWithAssociatedLists(Long id){
        WarningPatient result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<WarningPatient> update(List<WarningPatient> ts, boolean createIfNotExist) {
        List<WarningPatient> result = new ArrayList<>();
        if (ts != null) {
            for (WarningPatient t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    WarningPatient loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, WarningPatient t, WarningPatient loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public WarningPatient findByReferenceEntity(WarningPatient t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(WarningPatient t){
        if( t != null) {
            t.setPatient(patientService.findOrSave(t.getPatient()));
        }
    }



    public List<WarningPatient> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<WarningPatient>> getToBeSavedAndToBeDeleted(List<WarningPatient> oldList, List<WarningPatient> newList) {
        List<List<WarningPatient>> result = new ArrayList<>();
        List<WarningPatient> resultDelete = new ArrayList<>();
        List<WarningPatient> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<WarningPatient> oldList, List<WarningPatient> newList, List<WarningPatient> resultUpdateOrSave, List<WarningPatient> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                WarningPatient myOld = oldList.get(i);
                WarningPatient t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                WarningPatient myNew = newList.get(i);
                WarningPatient t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private PatientInfirmierService patientService ;
    @Autowired
    private WarningTypeInfirmierService warningTypeService ;

    public WarningPatientInfirmierServiceImpl(WarningPatientDao dao) {
        this.dao = dao;
    }

    private WarningPatientDao dao;
}
