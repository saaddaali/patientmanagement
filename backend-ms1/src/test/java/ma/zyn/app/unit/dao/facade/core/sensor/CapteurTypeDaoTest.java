package ma.zyn.app.unit.dao.facade.core.sensor;

import ma.zyn.app.bean.core.sensor.CapteurType;
import ma.zyn.app.dao.facade.core.sensor.CapteurTypeDao;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CapteurTypeDaoTest {

@Autowired
    private CapteurTypeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        CapteurType entity = new CapteurType();
        entity.setCode(code);
        underTest.save(entity);
        CapteurType loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        CapteurType loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        CapteurType entity = new CapteurType();
        entity.setId(id);
        underTest.save(entity);
        CapteurType loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        CapteurType entity = new CapteurType();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        CapteurType loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<CapteurType> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<CapteurType> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        CapteurType given = constructSample(1);
        CapteurType saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private CapteurType constructSample(int i) {
		CapteurType given = new CapteurType();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
