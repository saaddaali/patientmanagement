import {CapteurDto} from '../sensor/Capteur.model';
import {PatientDto} from '../patient/Patient.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class LocalisationDto extends BaseDto{

   public dateLocalisation: Date;

    public longitude: null | number;

    public latitude: null | number;

    public patient: PatientDto ;
    public capteur: CapteurDto ;


    constructor() {
        super();

        this.dateLocalisation = null;
        this.longitude = null;
        this.latitude = null;

        }

}
