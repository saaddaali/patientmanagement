package ma.zyn.app.dao.facade.core.warning;

import ma.zyn.app.config.repository.AbstractRepository;
import ma.zyn.app.bean.core.warning.WarningPatient;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface WarningPatientDao extends AbstractRepository<WarningPatient,Long>  {

    List<WarningPatient> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientEmail(String email);
    List<WarningPatient> findByWarningTypeId(Long id);
    int deleteByWarningTypeId(Long id);
    long countByWarningTypeCode(String code);


}
