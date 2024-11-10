package ma.zyn.app.unit.dao.facade.core.patient;

import ma.zyn.app.bean.core.patient.Patient;
import ma.zyn.app.dao.facade.core.patient.PatientDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.patient.Gender ;
import ma.zyn.app.bean.core.staff.Doctor ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PatientDaoTest {

@Autowired
    private PatientDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByEmail(){
        String email = "email-1";
        Patient entity = new Patient();
        entity.setEmail(email);
        underTest.save(entity);
        Patient loaded = underTest.findByEmail(email);
        assertThat(loaded.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldDeleteByEmail() {
        String email = "email-12345678";
        int result = underTest.deleteByEmail(email);

        Patient loaded = underTest.findByEmail(email);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Patient entity = new Patient();
        entity.setId(id);
        underTest.save(entity);
        Patient loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Patient entity = new Patient();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Patient loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Patient> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Patient> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Patient given = constructSample(1);
        Patient saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Patient constructSample(int i) {
		Patient given = new Patient();
        given.setDateOfBirth(LocalDateTime.now());
        given.setGender(new Gender(1L));
        given.setAddress("address-"+i);
        given.setEmergencyContact("emergencyContact-"+i);
        given.setDoctorInCharge(new Doctor(1L));
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
