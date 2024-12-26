import {PatientCriteria} from '../patient/PatientCriteria.model';
import {WarningTypeCriteria} from './WarningTypeCriteria.model';

import {BaseCriteria} from 'src/app/config/criteria/BaseCriteria.model';

export class WarningPatientCriteria extends BaseCriteria {

    public id: number;
    public message: string;
    public messageLike: string;
    public dateWarning: Date;
    public dateWarningFrom: Date;
    public dateWarningTo: Date;
  public patient: PatientCriteria ;
  public patients: Array<PatientCriteria> ;
  public warningType: WarningTypeCriteria ;
  public warningTypes: Array<WarningTypeCriteria> ;

}
