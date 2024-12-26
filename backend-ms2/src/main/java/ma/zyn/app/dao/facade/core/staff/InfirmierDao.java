package ma.zyn.app.dao.facade.core.staff;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.config.repository.AbstractRepository;
import ma.zyn.app.bean.core.staff.Infirmier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InfirmierDao extends AbstractRepository<Infirmier,Long>  {
    Infirmier findByEmail(String email);
    int deleteByEmail(String email);

    List<Infirmier> findBySpecializationId(Long id);
    int deleteBySpecializationId(Long id);
    long countBySpecializationCode(String code);
    Infirmier findByUsername(String username);

    @Query("SELECT NEW Infirmier(item.id,item.email) FROM Infirmier item")
    List<Infirmier> findAllOptimized();

}
