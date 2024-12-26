package ma.zyn.app.dao.facade.core.patient;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.config.repository.AbstractRepository;
import ma.zyn.app.bean.core.patient.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientDao extends AbstractRepository<Patient,Long>  {
    Patient findByEmail(String email);
    int deleteByEmail(String email);

    List<Patient> findByGenderId(Long id);
    int deleteByGenderId(Long id);
    long countByGenderCode(String code);
    List<Patient> findByDoctorInChargeId(Long id);
    int deleteByDoctorInChargeId(Long id);
    long countByDoctorInChargeEmail(String email);
    Patient findByUsername(String username);

    @Query("SELECT NEW Patient(item.id,item.email) FROM Patient item")
    List<Patient> findAllOptimized();

}
