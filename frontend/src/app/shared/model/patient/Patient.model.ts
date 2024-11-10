import {InfirmierDto} from '../staff/Infirmier.model';
import {InfirmierPatientDto} from '../healthcare/InfirmierPatient.model';
import {GenderDto} from './Gender.model';
import {DoctorDto} from '../staff/Doctor.model';
import {WarningPatientDto} from '../warning/WarningPatient.model';
import {WarningTypeDto} from '../warning/WarningType.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class PatientDto extends BaseDto{

   public dateOfBirth: Date;

    public address: string;

    public emergencyContact: string;

   public passwordChanged: null | boolean;

   public accountNonLocked: null | boolean;

    public password: string;

    public email: string;

   public enabled: null | boolean;

   public credentialsNonExpired: null | boolean;

   public accountNonExpired: null | boolean;

    public username: string;

    public gender: GenderDto ;
    public doctorInCharge: DoctorDto ;
     public infirmierPatients: Array<InfirmierPatientDto>;
     public warningPatients: Array<WarningPatientDto>;


    constructor() {
        super();

        this.dateOfBirth = null;
        this.address = '';
        this.emergencyContact = '';
        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';
        this.email = '';
        this.enabled = null;
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';
        this.gender = new GenderDto() ;
        this.infirmierPatients = new Array<InfirmierPatientDto>();
        this.warningPatients = new Array<WarningPatientDto>();

        }

}
