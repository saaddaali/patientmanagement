package ma.zyn.app.dao.facade.core.warning;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.warning.WarningType;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.warning.WarningType;
import java.util.List;


@Repository
public interface WarningTypeDao extends AbstractRepository<WarningType,Long>  {
    WarningType findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW WarningType(item.id,item.libelle) FROM WarningType item")
    List<WarningType> findAllOptimized();

}
