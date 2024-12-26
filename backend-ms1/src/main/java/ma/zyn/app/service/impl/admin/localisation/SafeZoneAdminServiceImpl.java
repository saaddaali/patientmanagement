package ma.zyn.app.service.impl.admin.localisation;


import ma.zyn.app.config.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.dao.criteria.core.localisation.SafeZoneCriteria;
import ma.zyn.app.dao.facade.core.localisation.SafeZoneDao;
import ma.zyn.app.dao.specification.core.localisation.SafeZoneSpecification;
import ma.zyn.app.service.facade.admin.localisation.SafeZoneAdminService;

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

import ma.zyn.app.service.facade.admin.patient.PatientAdminService ;

@Service
public class SafeZoneAdminServiceImpl implements SafeZoneAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SafeZone update(SafeZone t) {
        SafeZone loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{SafeZone.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public SafeZone findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public SafeZone findOrSave(SafeZone t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            SafeZone result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<SafeZone> findAll() {
        return dao.findAll();
    }

    public List<SafeZone> findByCriteria(SafeZoneCriteria criteria) {
        List<SafeZone> content = null;
        if (criteria != null) {
            SafeZoneSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SafeZoneSpecification constructSpecification(SafeZoneCriteria criteria) {
        SafeZoneSpecification mySpecification =  (SafeZoneSpecification) RefelexivityUtil.constructObjectUsingOneParam(SafeZoneSpecification.class, criteria);
        return mySpecification;
    }

    public List<SafeZone> findPaginatedByCriteria(SafeZoneCriteria criteria, int page, int pageSize, String order, String sortField) {
        SafeZoneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SafeZoneCriteria criteria) {
        SafeZoneSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<SafeZone> findByLinkedPatientId(Long id){
        return dao.findByLinkedPatientId(id);
    }
    public int deleteByLinkedPatientId(Long id){
        return dao.deleteByLinkedPatientId(id);
    }
    public long countByLinkedPatientEmail(String email){
        return dao.countByLinkedPatientEmail(email);
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
    public List<SafeZone> delete(List<SafeZone> list) {
		List<SafeZone> result = new ArrayList();
        if (list != null) {
            for (SafeZone t : list) {
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
    public SafeZone create(SafeZone t) {
        SafeZone loaded = findByReferenceEntity(t);
        SafeZone saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public SafeZone findWithAssociatedLists(Long id){
        SafeZone result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SafeZone> update(List<SafeZone> ts, boolean createIfNotExist) {
        List<SafeZone> result = new ArrayList<>();
        if (ts != null) {
            for (SafeZone t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    SafeZone loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, SafeZone t, SafeZone loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public SafeZone findByReferenceEntity(SafeZone t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(SafeZone t){
        if( t != null) {
            t.setLinkedPatient(patientService.findOrSave(t.getLinkedPatient()));
        }
    }



    public List<SafeZone> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<SafeZone>> getToBeSavedAndToBeDeleted(List<SafeZone> oldList, List<SafeZone> newList) {
        List<List<SafeZone>> result = new ArrayList<>();
        List<SafeZone> resultDelete = new ArrayList<>();
        List<SafeZone> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<SafeZone> oldList, List<SafeZone> newList, List<SafeZone> resultUpdateOrSave, List<SafeZone> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                SafeZone myOld = oldList.get(i);
                SafeZone t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                SafeZone myNew = newList.get(i);
                SafeZone t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private PatientAdminService patientService ;

    public SafeZoneAdminServiceImpl(SafeZoneDao dao) {
        this.dao = dao;
    }

    private SafeZoneDao dao;
}
