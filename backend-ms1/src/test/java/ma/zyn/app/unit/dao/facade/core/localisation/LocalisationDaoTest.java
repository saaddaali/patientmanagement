package ma.zyn.app.unit.dao.facade.core.localisation;

import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.dao.facade.core.localisation.LocalisationDao;

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

import ma.zyn.app.bean.core.sensor.Capteur ;
import ma.zyn.app.bean.core.patient.Patient ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LocalisationDaoTest {

@Autowired
    private LocalisationDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Localisation entity = new Localisation();
        entity.setId(id);
        underTest.save(entity);
        Localisation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Localisation entity = new Localisation();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Localisation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Localisation> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Localisation> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Localisation given = constructSample(1);
        Localisation saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
