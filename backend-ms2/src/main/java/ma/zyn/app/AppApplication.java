package ma.zyn.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;


import ma.zyn.app.bean.core.staff.Doctor;
import ma.zyn.app.service.facade.admin.staff.DoctorAdminService;
import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.service.facade.admin.staff.InfirmierAdminService;
import ma.zyn.app.config.security.bean.*;
import ma.zyn.app.config.security.common.AuthoritiesConstants;
import ma.zyn.app.config.security.service.facade.*;


import ma.zyn.app.config.security.bean.User;
import ma.zyn.app.config.security.bean.Role;

@SpringBootApplication
@EnableDiscoveryClient
public class AppApplication {
    public static ConfigurableApplicationContext ctx;


    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(AppApplication.class, args);
    }


    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , DoctorAdminService doctorService, InfirmierAdminService infirmierService) {
    return (args) -> {
        if(true){


        /*
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));
        */

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Doctor
        Doctor userForDoctor = new Doctor("doctor");
		userForDoctor.setPassword("123");
		// Role Doctor
		Role roleForDoctor = new Role();
		roleForDoctor.setAuthority(AuthoritiesConstants.DOCTOR);
		roleForDoctor.setCreatedAt(LocalDateTime.now());
		Role roleForDoctorSaved = roleService.create(roleForDoctor);
		RoleUser roleUserForDoctor = new RoleUser();
		roleUserForDoctor.setRole(roleForDoctorSaved);
		if (userForDoctor.getRoleUsers() == null)
			userForDoctor.setRoleUsers(new ArrayList<>());

		userForDoctor.getRoleUsers().add(roleUserForDoctor);


        userForDoctor.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        doctorService.create(userForDoctor);

		// User Infirmier
        Infirmier userForInfirmier = new Infirmier("infirmier");
		userForInfirmier.setPassword("123");
		// Role Infirmier
		Role roleForInfirmier = new Role();
		roleForInfirmier.setAuthority(AuthoritiesConstants.INFIRMIER);
		roleForInfirmier.setCreatedAt(LocalDateTime.now());
		Role roleForInfirmierSaved = roleService.create(roleForInfirmier);
		RoleUser roleUserForInfirmier = new RoleUser();
		roleUserForInfirmier.setRole(roleForInfirmierSaved);
		if (userForInfirmier.getRoleUsers() == null)
			userForInfirmier.setRoleUsers(new ArrayList<>());

		userForInfirmier.getRoleUsers().add(roleUserForInfirmier);


        userForInfirmier.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        infirmierService.create(userForInfirmier);

            }
        };
    }




    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("WarningType"));
        modelPermissions.add(new ModelPermission("Infirmier"));
        modelPermissions.add(new ModelPermission("Specialization"));
        modelPermissions.add(new ModelPermission("InfirmierPatient"));
        modelPermissions.add(new ModelPermission("WarningPatient"));
        modelPermissions.add(new ModelPermission("Doctor"));
        modelPermissions.add(new ModelPermission("Patient"));
        modelPermissions.add(new ModelPermission("Gender"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


}


