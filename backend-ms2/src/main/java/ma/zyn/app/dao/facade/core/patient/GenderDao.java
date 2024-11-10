package ma.zyn.app.dao.facade.core.patient;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.patient.Gender;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.patient.Gender;
import java.util.List;


@Repository
public interface GenderDao extends AbstractRepository<Gender,Long>  {
    Gender findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Gender(item.id,item.libelle) FROM Gender item")
    List<Gender> findAllOptimized();

}
