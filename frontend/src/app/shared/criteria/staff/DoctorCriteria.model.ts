import {SpecializationCriteria} from './SpecializationCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class DoctorCriteria extends BaseCriteria {

    public passwordChanged: null | boolean;
    public accountNonLocked: null | boolean;
    public password: string;
    public passwordLike: string;
    public email: string;
    public emailLike: string;
    public enabled: null | boolean;
    public credentialsNonExpired: null | boolean;
    public accountNonExpired: null | boolean;
    public username: string;
    public usernameLike: string;
  public specialization: SpecializationCriteria ;
  public specializations: Array<SpecializationCriteria> ;

}
