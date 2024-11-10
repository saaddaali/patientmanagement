package ma.zyn.app.dao.facade.core.staff;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.staff.Doctor;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.staff.Doctor;
import java.util.List;


@Repository
public interface DoctorDao extends AbstractRepository<Doctor,Long>  {
    Doctor findByEmail(String email);
    int deleteByEmail(String email);

    List<Doctor> findBySpecializationId(Long id);
    int deleteBySpecializationId(Long id);
    long countBySpecializationCode(String code);
    Doctor findByUsername(String username);

    @Query("SELECT NEW Doctor(item.id,item.email) FROM Doctor item")
    List<Doctor> findAllOptimized();

}
