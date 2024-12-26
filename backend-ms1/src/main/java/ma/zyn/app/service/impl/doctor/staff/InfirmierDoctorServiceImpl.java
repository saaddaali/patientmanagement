package ma.zyn.app.service.impl.doctor.staff;


import ma.zyn.app.config.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.dao.criteria.core.staff.InfirmierCriteria;
import ma.zyn.app.dao.facade.core.staff.InfirmierDao;
import ma.zyn.app.dao.specification.core.staff.InfirmierSpecification;
import ma.zyn.app.service.facade.doctor.staff.InfirmierDoctorService;

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

import ma.zyn.app.service.facade.doctor.staff.SpecializationDoctorService ;

import java.time.LocalDateTime;
import ma.zyn.app.config.security.service.facade.UserService;
import ma.zyn.app.config.security.service.facade.RoleService;
import ma.zyn.app.config.security.service.facade.RoleUserService;
import ma.zyn.app.config.security.bean.Role;
import ma.zyn.app.config.security.bean.RoleUser;
import ma.zyn.app.config.security.common.AuthoritiesConstants;
import ma.zyn.app.config.security.service.facade.ModelPermissionUserService;

@Service
public class InfirmierDoctorServiceImpl implements InfirmierDoctorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Infirmier update(Infirmier t) {
        Infirmier loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Infirmier.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Infirmier findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Infirmier findOrSave(Infirmier t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Infirmier result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Infirmier> findAll() {
        return dao.findAll();
    }

    public List<Infirmier> findByCriteria(InfirmierCriteria criteria) {
        List<Infirmier> content = null;
        if (criteria != null) {
            InfirmierSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private InfirmierSpecification constructSpecification(InfirmierCriteria criteria) {
        InfirmierSpecification mySpecification =  (InfirmierSpecification) RefelexivityUtil.constructObjectUsingOneParam(InfirmierSpecification.class, criteria);
        return mySpecification;
    }

    public List<Infirmier> findPaginatedByCriteria(InfirmierCriteria criteria, int page, int pageSize, String order, String sortField) {
        InfirmierSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InfirmierCriteria criteria) {
        InfirmierSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Infirmier> findBySpecializationId(Long id){
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
    public List<Infirmier> delete(List<Infirmier> list) {
		List<Infirmier> result = new ArrayList();
        if (list != null) {
            for (Infirmier t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }


    public Infirmier findWithAssociatedLists(Long id){
        Infirmier result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Infirmier> update(List<Infirmier> ts, boolean createIfNotExist) {
        List<Infirmier> result = new ArrayList<>();
        if (ts != null) {
            for (Infirmier t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Infirmier loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Infirmier t, Infirmier loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Infirmier findByReferenceEntity(Infirmier t){
        return t==null? null : dao.findByEmail(t.getEmail());
    }
    public void findOrSaveAssociatedObject(Infirmier t){
        if( t != null) {
        }
    }



    public List<Infirmier> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Infirmier>> getToBeSavedAndToBeDeleted(List<Infirmier> oldList, List<Infirmier> newList) {
        List<List<Infirmier>> result = new ArrayList<>();
        List<Infirmier> resultDelete = new ArrayList<>();
        List<Infirmier> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Infirmier> oldList, List<Infirmier> newList, List<Infirmier> resultUpdateOrSave, List<Infirmier> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Infirmier myOld = oldList.get(i);
                Infirmier t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Infirmier myNew = newList.get(i);
                Infirmier t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    public Infirmier create(Infirmier t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.INFIRMIER);
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

        Infirmier mySaved = dao.save(t);

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

    public Infirmier findByUsername(String username){
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
    private SpecializationDoctorService specializationService ;

    public InfirmierDoctorServiceImpl(InfirmierDao dao) {
        this.dao = dao;
    }

    private InfirmierDao dao;
}
