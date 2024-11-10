package ma.zyn.app.unit.dao.facade.core.localisation;

import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.dao.facade.core.localisation.SafeZoneDao;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SafeZoneDaoTest {

@Autowired
    private SafeZoneDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        SafeZone entity = new SafeZone();
        entity.setId(id);
        underTest.save(entity);
        SafeZone loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        SafeZone entity = new SafeZone();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        SafeZone loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<SafeZone> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<SafeZone> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        SafeZone given = constructSample(1);
        SafeZone saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
