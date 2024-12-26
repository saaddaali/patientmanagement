package ma.zyn.app.config.service;

import ma.zyn.app.config.bean.BaseEntity;
import ma.zyn.app.config.criteria.BaseCriteria;
import ma.zyn.app.config.repository.AbstractRepository;

public abstract class AbstractReferentielServiceImpl<T extends BaseEntity, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImpl<T, CRITERIA, REPO> {

    public AbstractReferentielServiceImpl(REPO dao) {
        super(dao);
    }

}
