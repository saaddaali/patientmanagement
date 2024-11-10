package ma.zyn.app.service.impl.admin.localisation;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.dao.criteria.core.localisation.LocalisationCriteria;
import ma.zyn.app.dao.facade.core.localisation.LocalisationDao;
import ma.zyn.app.dao.specification.core.localisation.LocalisationSpecification;
import ma.zyn.app.service.facade.admin.localisation.LocalisationAdminService;
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

import ma.zyn.app.service.facade.admin.sensor.CapteurAdminService ;
import ma.zyn.app.bean.core.sensor.Capteur ;
import ma.zyn.app.service.facade.admin.patient.PatientAdminService ;
import ma.zyn.app.bean.core.patient.Patient ;

import java.util.List;
@Service
public class LocalisationAdminServiceImpl implements LocalisationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Localisation update(Localisation t) {
        Localisation loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Localisation.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Localisation findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Localisation findOrSave(Localisation t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Localisation result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Localisation> findAll() {
        return dao.findAll();
    }

    public List<Localisation> findByCriteria(LocalisationCriteria criteria) {
        List<Localisation> content = null;
        if (criteria != null) {
            LocalisationSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private LocalisationSpecification constructSpecification(LocalisationCriteria criteria) {
        LocalisationSpecification mySpecification =  (LocalisationSpecification) RefelexivityUtil.constructObjectUsingOneParam(LocalisationSpecification.class, criteria);
        return mySpecification;
    }

    public List<Localisation> findPaginatedByCriteria(LocalisationCriteria criteria, int page, int pageSize, String order, String sortField) {
        LocalisationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(LocalisationCriteria criteria) {
        LocalisationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Localisation> findByPatientId(Long id){
        return dao.findByPatientId(id);
    }
    public int deleteByPatientId(Long id){
        return dao.deleteByPatientId(id);
    }
    public long countByPatientEmail(String email){
        return dao.countByPatientEmail(email);
    }
    public List<Localisation> findByCapteurId(Long id){
        return dao.findByCapteurId(id);
    }
    public int deleteByCapteurId(Long id){
        return dao.deleteByCapteurId(id);
    }
    public long countByCapteurCode(String code){
        return dao.countByCapteurCode(code);
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
    public List<Localisation> delete(List<Localisation> list) {
		List<Localisation> result = new ArrayList();
        if (list != null) {
            for (Localisation t : list) {
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
    public Localisation create(Localisation t) {
        Localisation loaded = findByReferenceEntity(t);
        Localisation saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Localisation findWithAssociatedLists(Long id){
        Localisation result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Localisation> update(List<Localisation> ts, boolean createIfNotExist) {
        List<Localisation> result = new ArrayList<>();
        if (ts != null) {
            for (Localisation t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Localisation loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Localisation t, Localisation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Localisation findByReferenceEntity(Localisation t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Localisation t){
        if( t != null) {
            t.setPatient(patientService.findOrSave(t.getPatient()));
            t.setCapteur(capteurService.findOrSave(t.getCapteur()));
        }
    }



    public List<Localisation> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Localisation>> getToBeSavedAndToBeDeleted(List<Localisation> oldList, List<Localisation> newList) {
        List<List<Localisation>> result = new ArrayList<>();
        List<Localisation> resultDelete = new ArrayList<>();
        List<Localisation> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Localisation> oldList, List<Localisation> newList, List<Localisation> resultUpdateOrSave, List<Localisation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Localisation myOld = oldList.get(i);
                Localisation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Localisation myNew = newList.get(i);
                Localisation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CapteurAdminService capteurService ;
    @Autowired
    private PatientAdminService patientService ;

    public LocalisationAdminServiceImpl(LocalisationDao dao) {
        this.dao = dao;
    }

    private LocalisationDao dao;
}
