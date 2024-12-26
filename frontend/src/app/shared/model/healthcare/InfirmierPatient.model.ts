import {InfirmierDto} from '../staff/Infirmier.model';
import {PatientDto} from '../patient/Patient.model';

import {BaseDto} from 'src/app/config/dto/BaseDto.model';


export class InfirmierPatientDto extends BaseDto{

   public dateDebut: Date;

   public dateFin: Date;

    public patient: PatientDto ;
    public infirmier: InfirmierDto ;


    constructor() {
        super();

        this.dateDebut = null;
        this.dateFin = null;
        this.patient = new PatientDto() ;
        this.infirmier = new InfirmierDto() ;

        }

}
