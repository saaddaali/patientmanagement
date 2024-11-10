import {PatientDto} from '../patient/Patient.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class SafeZoneDto extends BaseDto{

    public centreLongitude: null | number;

    public centreLatitude: null | number;

    public rayon: null | number;

    public linkedPatient: PatientDto ;


    constructor() {
        super();

        this.centreLongitude = null;
        this.centreLatitude = null;
        this.rayon = null;
        this.linkedPatient = new PatientDto() ;

        }

}
