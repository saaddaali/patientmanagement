import {PatientCriteria} from '../patient/PatientCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class SafeZoneCriteria extends BaseCriteria {

    public id: number;
     public centreLongitude: number;
     public centreLongitudeMin: number;
     public centreLongitudeMax: number;
     public centreLatitude: number;
     public centreLatitudeMin: number;
     public centreLatitudeMax: number;
     public rayon: number;
     public rayonMin: number;
     public rayonMax: number;
  public linkedPatient: PatientCriteria ;
  public linkedPatients: Array<PatientCriteria> ;

}
