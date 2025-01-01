package ma.zyn.app.unit.service.impl.infirmier.patient;

import ma.zyn.app.bean.core.patient.Patient;
import ma.zyn.app.dao.facade.core.patient.PatientDao;
import ma.zyn.app.service.impl.infirmier.patient.PatientInfirmierServiceImpl;

import ma.zyn.app.bean.core.staff.Infirmier ;
import ma.zyn.app.bean.core.healthcare.InfirmierPatient ;
import ma.zyn.app.bean.core.patient.Patient ;
import ma.zyn.app.bean.core.patient.Gender ;
import ma.zyn.app.bean.core.staff.Doctor ;
import ma.zyn.app.bean.core.warning.WarningPatient ;
import ma.zyn.app.bean.core.warning.WarningType ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class PatientInfirmierServiceImplTest {

    @Mock
    private PatientDao repository;
    private AutoCloseable autoCloseable;
    private PatientInfirmierServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PatientInfirmierServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPatient() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

//    @Test
//    void itShouldSavePatient() {
//        // Given
//        Patient toSave = constructSample(1);
//        when(repository.save(toSave)).thenReturn(toSave);
//
//        // When
//        underTest.create(toSave);
//
//        // Then
//        verify(repository).save(toSave);
//    }
//
//    @Test
//    void itShouldDeletePatient() {
//        // Given
//        Long idToDelete = 1L;
//        when(repository.existsById(idToDelete)).thenReturn(true);
//
//        // When
//        underTest.deleteById(idToDelete);
//
//        // Then
//        verify(repository).deleteById(idToDelete);
//    }
    @Test
    void itShouldGetPatientById() {
        // Given
        Long idToRetrieve = 1L; // Example Patient ID to retrieve
        Patient expected = new Patient(); // You need to replace Patient with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Patient result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Patient constructSample(int i) {
		Patient given = new Patient();
        given.setDateOfBirth(LocalDateTime.now());
        given.setGender(new Gender(1L));
        given.setAddress("address-"+i);
        given.setEmergencyContact("emergencyContact-"+i);
        given.setDoctorInCharge(new Doctor(1L));
        List<InfirmierPatient> infirmierPatients = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                InfirmierPatient element = new InfirmierPatient();
                                                element.setId((long)id);
                                                element.setPatient(new Patient(Long.valueOf(1)));
                                                element.setInfirmier(new Infirmier(Long.valueOf(2)));
                                                element.setDateDebut(LocalDateTime.now());
                                                element.setDateFin(LocalDateTime.now());
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setInfirmierPatients(infirmierPatients);
        List<WarningPatient> warningPatients = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                WarningPatient element = new WarningPatient();
                                                element.setId((long)id);
                                                element.setPatient(new Patient(Long.valueOf(1)));
                                                element.setMessage("message"+id);
                                                element.setWarningType(new WarningType(Long.valueOf(3)));
                                                element.setDateWarning(LocalDateTime.now());
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setWarningPatients(warningPatients);
        given.setPasswordChanged(false);
        given.setAccountNonLocked(false);
        given.setPassword("password-"+i);
        given.setEmail("email-"+i);
        given.setEnabled(false);
        given.setCredentialsNonExpired(false);
        given.setAccountNonExpired(false);
        given.setUsername("username-"+i);
        return given;
    }

}
