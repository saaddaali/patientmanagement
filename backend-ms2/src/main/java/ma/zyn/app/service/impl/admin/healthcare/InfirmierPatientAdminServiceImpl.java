package ma.zyn.app.service.impl.admin.healthcare;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.dao.criteria.core.healthcare.InfirmierPatientCriteria;
import ma.zyn.app.dao.facade.core.healthcare.InfirmierPatientDao;
import ma.zyn.app.dao.specification.core.healthcare.InfirmierPatientSpecification;
import ma.zyn.app.service.facade.admin.healthcare.InfirmierPatientAdminService;
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

import ma.zyn.app.service.facade.admin.staff.InfirmierAdminService ;
import ma.zyn.app.bean.core.staff.Infirmier ;
import ma.zyn.app.service.facade.admin.patient.PatientAdminService ;
import ma.zyn.app.bean.core.patient.Patient ;

import java.util.List;
@Service
public class InfirmierPatientAdminServiceImpl implements InfirmierPatientAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InfirmierPatient update(InfirmierPatient t) {
        InfirmierPatient loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{InfirmierPatient.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public InfirmierPatient findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public InfirmierPatient findOrSave(InfirmierPatient t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            InfirmierPatient result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<InfirmierPatient> findAll() {
        return dao.findAll();
    }

    public List<InfirmierPatient> findByCriteria(InfirmierPatientCriteria criteria) {
        List<InfirmierPatient> content = null;
        if (criteria != null) {
            InfirmierPatientSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private InfirmierPatientSpecification constructSpecification(InfirmierPatientCriteria criteria) {
        InfirmierPatientSpecification mySpecification =  (InfirmierPatientSpecification) RefelexivityUtil.constructObjectUsingOneParam(InfirmierPatientSpecification.class, criteria);
        return mySpecification;
    }

    public List<InfirmierPatient> findPaginatedByCriteria(InfirmierPatientCriteria criteria, int page, int pageSize, String order, String sortField) {
        InfirmierPatientSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InfirmierPatientCriteria criteria) {
        InfirmierPatientSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<InfirmierPatient> findByPatientId(Long id){
        return dao.findByPatientId(id);
    }
    public int deleteByPatientId(Long id){
        return dao.deleteByPatientId(id);
    }
    public long countByPatientEmail(String email){
        return dao.countByPatientEmail(email);
    }
    public List<InfirmierPatient> findByInfirmierId(Long id){
        return dao.findByInfirmierId(id);
    }
    public int deleteByInfirmierId(Long id){
        return dao.deleteByInfirmierId(id);
    }
    public long countByInfirmierEmail(String email){
        return dao.countByInfirmierEmail(email);
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
    public List<InfirmierPatient> delete(List<InfirmierPatient> list) {
		List<InfirmierPatient> result = new ArrayList();
        if (list != null) {
            for (InfirmierPatient t : list) {
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
    public InfirmierPatient create(InfirmierPatient t) {
        if (t.getPatient() != null) {
            t.getPatient().setId(null);
            t.setPatient(patientService.create(t.getPatient()));
        }
        InfirmierPatient loaded = findByReferenceEntity(t);
        InfirmierPatient saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public InfirmierPatient findWithAssociatedLists(Long id){
        InfirmierPatient result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InfirmierPatient> update(List<InfirmierPatient> ts, boolean createIfNotExist) {
        List<InfirmierPatient> result = new ArrayList<>();
        if (ts != null) {
            for (InfirmierPatient t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    InfirmierPatient loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, InfirmierPatient t, InfirmierPatient loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public InfirmierPatient findByReferenceEntity(InfirmierPatient t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(InfirmierPatient t){
        if( t != null) {
            t.setPatient(patientService.findOrSave(t.getPatient()));
            t.setInfirmier(infirmierService.findOrSave(t.getInfirmier()));
        }
    }



    public List<InfirmierPatient> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<InfirmierPatient>> getToBeSavedAndToBeDeleted(List<InfirmierPatient> oldList, List<InfirmierPatient> newList) {
        List<List<InfirmierPatient>> result = new ArrayList<>();
        List<InfirmierPatient> resultDelete = new ArrayList<>();
        List<InfirmierPatient> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<InfirmierPatient> oldList, List<InfirmierPatient> newList, List<InfirmierPatient> resultUpdateOrSave, List<InfirmierPatient> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                InfirmierPatient myOld = oldList.get(i);
                InfirmierPatient t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                InfirmierPatient myNew = newList.get(i);
                InfirmierPatient t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private InfirmierAdminService infirmierService ;
    @Autowired
    private PatientAdminService patientService ;

    public InfirmierPatientAdminServiceImpl(InfirmierPatientDao dao) {
        this.dao = dao;
    }

    private InfirmierPatientDao dao;
}
