package ma.zyn.app.unit.service.impl.admin.patient;

import ma.zyn.app.bean.core.patient.Gender;
import ma.zyn.app.dao.facade.core.patient.GenderDao;
import ma.zyn.app.service.impl.admin.patient.GenderAdminServiceImpl;

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
class GenderAdminServiceImplTest {

    @Mock
    private GenderDao repository;
    private AutoCloseable autoCloseable;
    private GenderAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new GenderAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllGender() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveGender() {
        // Given
        Gender toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteGender() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetGenderById() {
        // Given
        Long idToRetrieve = 1L; // Example Gender ID to retrieve
        Gender expected = new Gender(); // You need to replace Gender with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Gender result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Gender constructSample(int i) {
		Gender given = new Gender();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
