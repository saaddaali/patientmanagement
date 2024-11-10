package ma.zyn.app.service.impl.admin.patient;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.patient.Patient;
import ma.zyn.app.dao.criteria.core.patient.PatientCriteria;
import ma.zyn.app.dao.facade.core.patient.PatientDao;
import ma.zyn.app.dao.specification.core.patient.PatientSpecification;
import ma.zyn.app.service.facade.admin.patient.PatientAdminService;
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
import ma.zyn.app.service.facade.admin.healthcare.InfirmierPatientAdminService ;
import ma.zyn.app.bean.core.healthcare.InfirmierPatient ;
import ma.zyn.app.service.facade.admin.patient.GenderAdminService ;
import ma.zyn.app.bean.core.patient.Gender ;
import ma.zyn.app.service.facade.admin.staff.DoctorAdminService ;
import ma.zyn.app.bean.core.staff.Doctor ;
import ma.zyn.app.service.facade.admin.warning.WarningPatientAdminService ;
import ma.zyn.app.bean.core.warning.WarningPatient ;
import ma.zyn.app.service.facade.admin.warning.WarningTypeAdminService ;
import ma.zyn.app.bean.core.warning.WarningType ;

import java.time.LocalDateTime;
import ma.zyn.app.zynerator.security.service.facade.UserService;
import ma.zyn.app.zynerator.security.service.facade.RoleService;
import ma.zyn.app.zynerator.security.service.facade.RoleUserService;
import ma.zyn.app.zynerator.security.bean.Role;
import ma.zyn.app.zynerator.security.bean.RoleUser;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class PatientAdminServiceImpl implements PatientAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Patient update(Patient t) {
        Patient loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Patient.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Patient findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Patient findOrSave(Patient t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Patient result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Patient> findAll() {
        return dao.findAll();
    }

    public List<Patient> findByCriteria(PatientCriteria criteria) {
        List<Patient> content = null;
        if (criteria != null) {
            PatientSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private PatientSpecification constructSpecification(PatientCriteria criteria) {
        PatientSpecification mySpecification =  (PatientSpecification) RefelexivityUtil.constructObjectUsingOneParam(PatientSpecification.class, criteria);
        return mySpecification;
    }

    public List<Patient> findPaginatedByCriteria(PatientCriteria criteria, int page, int pageSize, String order, String sortField) {
        PatientSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PatientCriteria criteria) {
        PatientSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Patient> findByGenderId(Long id){
        return dao.findByGenderId(id);
    }
    public int deleteByGenderId(Long id){
        return dao.deleteByGenderId(id);
    }
    public long countByGenderCode(String code){
        return dao.countByGenderCode(code);
    }
    public List<Patient> findByDoctorInChargeId(Long id){
        return dao.findByDoctorInChargeId(id);
    }
    public int deleteByDoctorInChargeId(Long id){
        return dao.deleteByDoctorInChargeId(id);
    }
    public long countByDoctorInChargeEmail(String email){
        return dao.countByDoctorInChargeEmail(email);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        infirmierPatientService.deleteByPatientId(id);
        warningPatientService.deleteByPatientId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Patient> delete(List<Patient> list) {
		List<Patient> result = new ArrayList();
        if (list != null) {
            for (Patient t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }


    public Patient findWithAssociatedLists(Long id){
        Patient result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setInfirmierPatients(infirmierPatientService.findByPatientId(id));
            result.setWarningPatients(warningPatientService.findByPatientId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Patient> update(List<Patient> ts, boolean createIfNotExist) {
        List<Patient> result = new ArrayList<>();
        if (ts != null) {
            for (Patient t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Patient loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Patient t, Patient loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Patient patient){
    if(patient !=null && patient.getId() != null){
        List<List<InfirmierPatient>> resultInfirmierPatients= infirmierPatientService.getToBeSavedAndToBeDeleted(infirmierPatientService.findByPatientId(patient.getId()),patient.getInfirmierPatients());
            infirmierPatientService.delete(resultInfirmierPatients.get(1));
        emptyIfNull(resultInfirmierPatients.get(0)).forEach(e -> e.setPatient(patient));
        infirmierPatientService.update(resultInfirmierPatients.get(0),true);
        List<List<WarningPatient>> resultWarningPatients= warningPatientService.getToBeSavedAndToBeDeleted(warningPatientService.findByPatientId(patient.getId()),patient.getWarningPatients());
            warningPatientService.delete(resultWarningPatients.get(1));
        emptyIfNull(resultWarningPatients.get(0)).forEach(e -> e.setPatient(patient));
        warningPatientService.update(resultWarningPatients.get(0),true);
        }
    }








    public Patient findByReferenceEntity(Patient t){
        return t==null? null : dao.findByEmail(t.getEmail());
    }
    public void findOrSaveAssociatedObject(Patient t){
        if( t != null) {
            t.setGender(genderService.findOrSave(t.getGender()));
            t.setDoctorInCharge(doctorService.findOrSave(t.getDoctorInCharge()));
        }
    }



    public List<Patient> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Patient>> getToBeSavedAndToBeDeleted(List<Patient> oldList, List<Patient> newList) {
        List<List<Patient>> result = new ArrayList<>();
        List<Patient> resultDelete = new ArrayList<>();
        List<Patient> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Patient> oldList, List<Patient> newList, List<Patient> resultUpdateOrSave, List<Patient> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Patient myOld = oldList.get(i);
                Patient t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Patient myNew = newList.get(i);
                Patient t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


    @Override
    public Patient create(Patient t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.PATIENT);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
            t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
            t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Patient mySaved = dao.save(t);

        if (t.getModelPermissionUsers() != null) {
            t.getModelPermissionUsers().forEach(e -> {
                e.setUser(mySaved);
                modelPermissionUserService.create(e);
            });
        }
        if (t.getRoleUsers() != null) {
            t.getRoleUsers().forEach(element-> {
                element.setUser(mySaved);
                roleUserService.create(element);
            });
        }

        if (t.getInfirmierPatients() != null) {
        t.getInfirmierPatients().forEach(element-> {
            element.setPatient(mySaved);
            infirmierPatientService.create(element);
        });
        }
        if (t.getWarningPatients() != null) {
        t.getWarningPatients().forEach(element-> {
            element.setPatient(mySaved);
            warningPatientService.create(element);
        });
        }
        return mySaved;
     }

    public Patient findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }




    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;

    @Autowired
    private InfirmierAdminService infirmierService ;
    @Autowired
    private InfirmierPatientAdminService infirmierPatientService ;
    @Autowired
    private GenderAdminService genderService ;
    @Autowired
    private DoctorAdminService doctorService ;
    @Autowired
    private WarningPatientAdminService warningPatientService ;
    @Autowired
    private WarningTypeAdminService warningTypeService ;

    public PatientAdminServiceImpl(PatientDao dao) {
        this.dao = dao;
    }

    private PatientDao dao;
}
