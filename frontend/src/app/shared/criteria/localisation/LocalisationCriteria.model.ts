import {CapteurCriteria} from '../sensor/CapteurCriteria.model';
import {PatientCriteria} from '../patient/PatientCriteria.model';

import {BaseCriteria} from 'src/app/config/criteria/BaseCriteria.model';

export class LocalisationCriteria extends BaseCriteria {

    public id: number;
    public dateLocalisation: Date;
    public dateLocalisationFrom: Date;
    public dateLocalisationTo: Date;
     public longitude: number;
     public longitudeMin: number;
     public longitudeMax: number;
     public latitude: number;
     public latitudeMin: number;
     public latitudeMax: number;

}
