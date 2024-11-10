package ma.zyn.app.unit.dao.facade.core.warning;

import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.dao.facade.core.warning.WarningPatientDao;

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

import ma.zyn.app.bean.core.patient.Patient ;
import ma.zyn.app.bean.core.warning.WarningType ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class WarningPatientDaoTest {

@Autowired
    private WarningPatientDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        WarningPatient entity = new WarningPatient();
        entity.setId(id);
        underTest.save(entity);
        WarningPatient loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        WarningPatient entity = new WarningPatient();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        WarningPatient loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<WarningPatient> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<WarningPatient> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        WarningPatient given = constructSample(1);
        WarningPatient saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
