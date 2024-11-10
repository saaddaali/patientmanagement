package ma.zyn.app.unit.service.impl.doctor.sensor;

import ma.zyn.app.bean.core.sensor.Capteur;
import ma.zyn.app.dao.facade.core.sensor.CapteurDao;
import ma.zyn.app.service.impl.doctor.sensor.CapteurDoctorServiceImpl;

import ma.zyn.app.bean.core.sensor.CapteurType ;
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
class CapteurDoctorServiceImplTest {

    @Mock
    private CapteurDao repository;
    private AutoCloseable autoCloseable;
    private CapteurDoctorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CapteurDoctorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCapteur() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCapteur() {
        // Given
        Capteur toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCapteur() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCapteurById() {
        // Given
        Long idToRetrieve = 1L; // Example Capteur ID to retrieve
        Capteur expected = new Capteur(); // You need to replace Capteur with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Capteur result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Capteur constructSample(int i) {
		Capteur given = new Capteur();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setCapteurType(new CapteurType(1L));
        return given;
    }

}
