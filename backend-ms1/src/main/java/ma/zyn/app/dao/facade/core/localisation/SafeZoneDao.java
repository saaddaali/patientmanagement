package ma.zyn.app.dao.facade.core.localisation;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.localisation.SafeZone;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SafeZoneDao extends AbstractRepository<SafeZone,Long>  {

    List<SafeZone> findByLinkedPatientId(Long id);
    int deleteByLinkedPatientId(Long id);
    long countByLinkedPatientEmail(String email);


}
