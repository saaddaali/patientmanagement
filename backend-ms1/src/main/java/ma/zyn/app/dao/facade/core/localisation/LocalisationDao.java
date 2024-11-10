package ma.zyn.app.dao.facade.core.localisation;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.localisation.Localisation;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LocalisationDao extends AbstractRepository<Localisation,Long>  {

    List<Localisation> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientEmail(String email);
    List<Localisation> findByCapteurId(Long id);
    int deleteByCapteurId(Long id);
    long countByCapteurCode(String code);


}
