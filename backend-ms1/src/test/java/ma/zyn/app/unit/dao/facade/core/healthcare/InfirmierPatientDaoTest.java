package ma.zyn.app.unit.dao.facade.core.healthcare;

import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.dao.facade.core.healthcare.InfirmierPatientDao;

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

import ma.zyn.app.bean.core.staff.Infirmier ;
import ma.zyn.app.bean.core.patient.Patient ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class InfirmierPatientDaoTest {

@Autowired
    private InfirmierPatientDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        InfirmierPatient entity = new InfirmierPatient();
        entity.setId(id);
        underTest.save(entity);
        InfirmierPatient loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        InfirmierPatient entity = new InfirmierPatient();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        InfirmierPatient loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<InfirmierPatient> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<InfirmierPatient> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        InfirmierPatient given = constructSample(1);
        InfirmierPatient saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
