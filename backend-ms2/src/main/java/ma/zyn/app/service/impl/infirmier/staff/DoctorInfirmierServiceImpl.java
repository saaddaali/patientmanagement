package ma.zyn.app.service.impl.infirmier.staff;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.staff.Doctor;
import ma.zyn.app.dao.criteria.core.staff.DoctorCriteria;
import ma.zyn.app.dao.facade.core.staff.DoctorDao;
import ma.zyn.app.dao.specification.core.staff.DoctorSpecification;
import ma.zyn.app.service.facade.infirmier.staff.DoctorInfirmierService;
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

import ma.zyn.app.service.facade.infirmier.staff.SpecializationInfirmierService ;
import ma.zyn.app.bean.core.staff.Specialization ;

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
public class DoctorInfirmierServiceImpl implements DoctorInfirmierService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Doctor update(Doctor t) {
        Doctor loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Doctor.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Doctor findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Doctor findOrSave(Doctor t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Doctor result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Doctor> findAll() {
        return dao.findAll();
    }

    public List<Doctor> findByCriteria(DoctorCriteria criteria) {
        List<Doctor> content = null;
        if (criteria != null) {
            DoctorSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DoctorSpecification constructSpecification(DoctorCriteria criteria) {
        DoctorSpecification mySpecification =  (DoctorSpecification) RefelexivityUtil.constructObjectUsingOneParam(DoctorSpecification.class, criteria);
        return mySpecification;
    }

    public List<Doctor> findPaginatedByCriteria(DoctorCriteria criteria, int page, int pageSize, String order, String sortField) {
        DoctorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DoctorCriteria criteria) {
        DoctorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Doctor> findBySpecializationId(Long id){
        return dao.findBySpecializationId(id);
    }
    public int deleteBySpecializationId(Long id){
        return dao.deleteBySpecializationId(id);
    }
    public long countBySpecializationCode(String code){
        return dao.countBySpecializationCode(code);
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
    public List<Doctor> delete(List<Doctor> list) {
		List<Doctor> result = new ArrayList();
        if (list != null) {
            for (Doctor t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }


    public Doctor findWithAssociatedLists(Long id){
        Doctor result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Doctor> update(List<Doctor> ts, boolean createIfNotExist) {
        List<Doctor> result = new ArrayList<>();
        if (ts != null) {
            for (Doctor t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Doctor loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Doctor t, Doctor loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Doctor findByReferenceEntity(Doctor t){
        return t==null? null : dao.findByEmail(t.getEmail());
    }
    public void findOrSaveAssociatedObject(Doctor t){
        if( t != null) {
        }
    }



    public List<Doctor> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Doctor>> getToBeSavedAndToBeDeleted(List<Doctor> oldList, List<Doctor> newList) {
        List<List<Doctor>> result = new ArrayList<>();
        List<Doctor> resultDelete = new ArrayList<>();
        List<Doctor> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Doctor> oldList, List<Doctor> newList, List<Doctor> resultUpdateOrSave, List<Doctor> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Doctor myOld = oldList.get(i);
                Doctor t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Doctor myNew = newList.get(i);
                Doctor t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    public Doctor create(Doctor t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.DOCTOR);
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

        Doctor mySaved = dao.save(t);

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

        return mySaved;
     }

    public Doctor findByUsername(String username){
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
    private SpecializationInfirmierService specializationService ;

    public DoctorInfirmierServiceImpl(DoctorDao dao) {
        this.dao = dao;
    }

    private DoctorDao dao;
}
