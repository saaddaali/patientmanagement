import {PatientDto} from '../patient/Patient.model';
import {WarningTypeDto} from './WarningType.model';

import {BaseDto} from 'src/app/config/dto/BaseDto.model';


export class WarningPatientDto extends BaseDto{

    public message: string;

   public dateWarning: Date;

    public patient: PatientDto ;
    public warningType: WarningTypeDto ;


    constructor() {
        super();

        this.message = '';
        this.dateWarning = null;
        this.patient = new PatientDto() ;
        this.warningType = new WarningTypeDto() ;

        }

}
