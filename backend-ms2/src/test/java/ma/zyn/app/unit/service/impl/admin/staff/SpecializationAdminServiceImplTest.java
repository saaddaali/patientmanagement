package ma.zyn.app.unit.service.impl.admin.staff;

import ma.zyn.app.bean.core.staff.Specialization;
import ma.zyn.app.dao.facade.core.staff.SpecializationDao;
import ma.zyn.app.service.impl.admin.staff.SpecializationAdminServiceImpl;

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
class SpecializationAdminServiceImplTest {

    @Mock
    private SpecializationDao repository;
    private AutoCloseable autoCloseable;
    private SpecializationAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SpecializationAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllSpecialization() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveSpecialization() {
        // Given
        Specialization toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteSpecialization() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetSpecializationById() {
        // Given
        Long idToRetrieve = 1L; // Example Specialization ID to retrieve
        Specialization expected = new Specialization(); // You need to replace Specialization with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Specialization result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Specialization constructSample(int i) {
		Specialization given = new Specialization();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
