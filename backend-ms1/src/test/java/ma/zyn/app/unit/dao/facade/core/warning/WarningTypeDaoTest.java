package ma.zyn.app.unit.dao.facade.core.warning;

import ma.zyn.app.bean.core.warning.WarningType;
import ma.zyn.app.dao.facade.core.warning.WarningTypeDao;

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
public class WarningTypeDaoTest {

@Autowired
    private WarningTypeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        WarningType entity = new WarningType();
        entity.setCode(code);
        underTest.save(entity);
        WarningType loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        WarningType loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        WarningType entity = new WarningType();
        entity.setId(id);
        underTest.save(entity);
        WarningType loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        WarningType entity = new WarningType();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        WarningType loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<WarningType> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<WarningType> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        WarningType given = constructSample(1);
        WarningType saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private WarningType constructSample(int i) {
		WarningType given = new WarningType();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
