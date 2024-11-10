package ma.zyn.app.unit.service.impl.doctor.localisation;

import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.dao.facade.core.localisation.SafeZoneDao;
import ma.zyn.app.service.impl.doctor.localisation.SafeZoneDoctorServiceImpl;

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
class SafeZoneDoctorServiceImplTest {

    @Mock
    private SafeZoneDao repository;
    private AutoCloseable autoCloseable;
    private SafeZoneDoctorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SafeZoneDoctorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllSafeZone() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveSafeZone() {
        // Given
        SafeZone toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteSafeZone() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetSafeZoneById() {
        // Given
        Long idToRetrieve = 1L; // Example SafeZone ID to retrieve
        SafeZone expected = new SafeZone(); // You need to replace SafeZone with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        SafeZone result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private SafeZone constructSample(int i) {
		SafeZone given = new SafeZone();
        given.setCentreLongitude(BigDecimal.TEN);
        given.setCentreLatitude(BigDecimal.TEN);
        given.setRayon(BigDecimal.TEN);
        given.setLinkedPatient(new Patient(1L));
        return given;
    }

}
