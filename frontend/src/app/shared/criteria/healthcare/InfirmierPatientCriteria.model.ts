import {InfirmierCriteria} from '../staff/InfirmierCriteria.model';
import {PatientCriteria} from '../patient/PatientCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class InfirmierPatientCriteria extends BaseCriteria {

    public id: number;
    public dateDebut: Date;
    public dateDebutFrom: Date;
    public dateDebutTo: Date;
    public dateFin: Date;
    public dateFinFrom: Date;
    public dateFinTo: Date;
  public patient: PatientCriteria ;
  public patients: Array<PatientCriteria> ;
  public infirmier: InfirmierCriteria ;
  public infirmiers: Array<InfirmierCriteria> ;

}
