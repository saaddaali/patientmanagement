package ma.zyn.app.unit.service.impl.admin.healthcare;

import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.dao.facade.core.healthcare.InfirmierPatientDao;
import ma.zyn.app.service.impl.admin.healthcare.InfirmierPatientAdminServiceImpl;

import ma.zyn.app.bean.core.staff.Infirmier ;
import ma.zyn.app.bean.core.patient.Patient ;
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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class InfirmierPatientAdminServiceImplTest {

    @Mock
    private InfirmierPatientDao repository;
    private AutoCloseable autoCloseable;
    @Autowired
    private InfirmierPatientAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new InfirmierPatientAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllInfirmierPatient() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveInfirmierPatient() {
        // Given
        InfirmierPatient toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);
        // When
        underTest.create(toSave);
        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteInfirmierPatient() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetInfirmierPatientById() {
        // Given
        Long idToRetrieve = 1L; // Example InfirmierPatient ID to retrieve
        InfirmierPatient expected = new InfirmierPatient(); // You need to replace InfirmierPatient with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        InfirmierPatient result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private InfirmierPatient constructSample(int i) {
		InfirmierPatient given = new InfirmierPatient();
        given.setPatient(new Patient(1L));
        given.setInfirmier(new Infirmier(1L));
        given.setDateDebut(LocalDateTime.now());
        given.setDateFin(LocalDateTime.now());
        return given;
    }

}
