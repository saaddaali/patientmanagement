package ma.zyn.app.dao.facade.core.healthcare;

import ma.zyn.app.config.repository.AbstractRepository;
import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InfirmierPatientDao extends AbstractRepository<InfirmierPatient,Long>  {

    List<InfirmierPatient> findByPatientId(Long id);
    int deleteByPatientId(Long id);
    long countByPatientEmail(String email);
    List<InfirmierPatient> findByInfirmierId(Long id);
    int deleteByInfirmierId(Long id);
    long countByInfirmierEmail(String email);


}
