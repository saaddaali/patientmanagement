package ma.zyn.app.unit.dao.facade.core.staff;

import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.dao.facade.core.staff.InfirmierDao;

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

import ma.zyn.app.bean.core.staff.Specialization ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class InfirmierDaoTest {

@Autowired
    private InfirmierDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByEmail(){
        String email = "email-1";
        Infirmier entity = new Infirmier();
        entity.setEmail(email);
        underTest.save(entity);
        Infirmier loaded = underTest.findByEmail(email);
        assertThat(loaded.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldDeleteByEmail() {
        String email = "email-12345678";
        int result = underTest.deleteByEmail(email);

        Infirmier loaded = underTest.findByEmail(email);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Infirmier entity = new Infirmier();
        entity.setId(id);
        underTest.save(entity);
        Infirmier loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Infirmier entity = new Infirmier();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Infirmier loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Infirmier> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Infirmier> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Infirmier given = constructSample(1);
        Infirmier saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
