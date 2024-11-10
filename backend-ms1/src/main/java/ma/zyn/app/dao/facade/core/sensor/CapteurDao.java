package ma.zyn.app.dao.facade.core.sensor;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.sensor.Capteur;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.sensor.Capteur;
import java.util.List;


@Repository
public interface CapteurDao extends AbstractRepository<Capteur,Long>  {
    Capteur findByCode(String code);
    int deleteByCode(String code);

    List<Capteur> findByCapteurTypeId(Long id);
    int deleteByCapteurTypeId(Long id);
    long countByCapteurTypeCode(String code);

    @Query("SELECT NEW Capteur(item.id,item.libelle) FROM Capteur item")
    List<Capteur> findAllOptimized();

}
