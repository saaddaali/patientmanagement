package ma.zyn.app.unit.service.impl.infirmier.localisation;

import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.dao.facade.core.localisation.LocalisationDao;
import ma.zyn.app.service.impl.infirmier.localisation.LocalisationInfirmierServiceImpl;

import ma.zyn.app.bean.core.sensor.Capteur ;
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



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class LocalisationInfirmierServiceImplTest {

    @Mock
    private LocalisationDao repository;
    private AutoCloseable autoCloseable;
    private LocalisationInfirmierServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new LocalisationInfirmierServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllLocalisation() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveLocalisation() {
        // Given
        Localisation toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteLocalisation() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetLocalisationById() {
        // Given
        Long idToRetrieve = 1L; // Example Localisation ID to retrieve
        Localisation expected = new Localisation(); // You need to replace Localisation with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Localisation result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Localisation constructSample(int i) {
		Localisation given = new Localisation();
        given.setPatient(new Patient(1L));
        given.setDateLocalisation(LocalDateTime.now());
        given.setLongitude(BigDecimal.TEN);
        given.setLatitude(BigDecimal.TEN);
        given.setCapteur(new Capteur(1L));
        return given;
    }

}
