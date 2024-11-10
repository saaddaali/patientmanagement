package ma.zyn.app.unit.service.impl.infirmier.sensor;

import ma.zyn.app.bean.core.sensor.CapteurType;
import ma.zyn.app.dao.facade.core.sensor.CapteurTypeDao;
import ma.zyn.app.service.impl.infirmier.sensor.CapteurTypeInfirmierServiceImpl;

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
class CapteurTypeInfirmierServiceImplTest {

    @Mock
    private CapteurTypeDao repository;
    private AutoCloseable autoCloseable;
    private CapteurTypeInfirmierServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CapteurTypeInfirmierServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCapteurType() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCapteurType() {
        // Given
        CapteurType toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCapteurType() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCapteurTypeById() {
        // Given
        Long idToRetrieve = 1L; // Example CapteurType ID to retrieve
        CapteurType expected = new CapteurType(); // You need to replace CapteurType with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CapteurType result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CapteurType constructSample(int i) {
		CapteurType given = new CapteurType();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
