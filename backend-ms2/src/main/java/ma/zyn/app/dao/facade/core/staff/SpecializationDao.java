package ma.zyn.app.dao.facade.core.staff;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.config.repository.AbstractRepository;
import ma.zyn.app.bean.core.staff.Specialization;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpecializationDao extends AbstractRepository<Specialization,Long>  {
    Specialization findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Specialization(item.id,item.libelle) FROM Specialization item")
    List<Specialization> findAllOptimized();

}
