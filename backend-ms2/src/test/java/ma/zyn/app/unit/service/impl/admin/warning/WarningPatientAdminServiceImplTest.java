package ma.zyn.app.unit.service.impl.admin.warning;

import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.dao.facade.core.warning.WarningPatientDao;
import ma.zyn.app.service.impl.admin.warning.WarningPatientAdminServiceImpl;

import ma.zyn.app.bean.core.patient.Patient ;
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
class WarningPatientAdminServiceImplTest {

    @Mock
    private WarningPatientDao repository;
    private AutoCloseable autoCloseable;
    private WarningPatientAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new WarningPatientAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllWarningPatient() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveWarningPatient() {
        // Given
        WarningPatient toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteWarningPatient() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetWarningPatientById() {
        // Given
        Long idToRetrieve = 1L; // Example WarningPatient ID to retrieve
        WarningPatient expected = new WarningPatient(); // You need to replace WarningPatient with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        WarningPatient result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private WarningPatient constructSample(int i) {
		WarningPatient given = new WarningPatient();
        given.setPatient(new Patient(1L));
        given.setMessage("message-"+i);
        given.setWarningType(new WarningType(1L));
        given.setDateWarning(LocalDateTime.now());
        return given;
    }

}
