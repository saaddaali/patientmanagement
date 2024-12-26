import {InfirmierCriteria} from '../staff/InfirmierCriteria.model';
import {InfirmierPatientCriteria} from '../healthcare/InfirmierPatientCriteria.model';
import {GenderCriteria} from './GenderCriteria.model';
import {DoctorCriteria} from '../staff/DoctorCriteria.model';
import {WarningPatientCriteria} from '../warning/WarningPatientCriteria.model';
import {WarningTypeCriteria} from '../warning/WarningTypeCriteria.model';

import {BaseCriteria} from 'src/app/config/criteria/BaseCriteria.model';

export class PatientCriteria extends BaseCriteria {

    public dateOfBirth: Date;
    public dateOfBirthFrom: Date;
    public dateOfBirthTo: Date;
    public address: string;
    public addressLike: string;
    public emergencyContact: string;
    public emergencyContactLike: string;
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
  public gender: GenderCriteria ;
  public genders: Array<GenderCriteria> ;
      public infirmierPatients: Array<InfirmierPatientCriteria>;
      public warningPatients: Array<WarningPatientCriteria>;

}
