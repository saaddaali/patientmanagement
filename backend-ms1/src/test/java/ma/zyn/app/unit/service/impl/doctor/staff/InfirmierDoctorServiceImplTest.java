package ma.zyn.app.unit.service.impl.doctor.staff;

import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.dao.facade.core.staff.InfirmierDao;
import ma.zyn.app.service.impl.doctor.staff.InfirmierDoctorServiceImpl;

import ma.zyn.app.bean.core.staff.Specialization ;
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
class InfirmierDoctorServiceImplTest {

    @Mock
    private InfirmierDao repository;
    private AutoCloseable autoCloseable;
    private InfirmierDoctorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new InfirmierDoctorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllInfirmier() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveInfirmier() {
        // Given
        Infirmier toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteInfirmier() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetInfirmierById() {
        // Given
        Long idToRetrieve = 1L; // Example Infirmier ID to retrieve
        Infirmier expected = new Infirmier(); // You need to replace Infirmier with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Infirmier result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Infirmier constructSample(int i) {
		Infirmier given = new Infirmier();
        given.setSpecialization(new Specialization(1L));
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
