package ma.zyn.app.dao.facade.core.sensor;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.sensor.CapteurType;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.sensor.CapteurType;
import java.util.List;


@Repository
public interface CapteurTypeDao extends AbstractRepository<CapteurType,Long>  {
    CapteurType findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CapteurType(item.id,item.libelle) FROM CapteurType item")
    List<CapteurType> findAllOptimized();

}
