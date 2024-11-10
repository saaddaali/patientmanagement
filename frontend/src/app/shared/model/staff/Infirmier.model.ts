import {SpecializationDto} from './Specialization.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class InfirmierDto extends BaseDto{

   public passwordChanged: null | boolean;

   public accountNonLocked: null | boolean;

    public password: string;

    public email: string;

   public enabled: null | boolean;

   public credentialsNonExpired: null | boolean;

   public accountNonExpired: null | boolean;

    public username: string;

    public specialization: SpecializationDto ;


    constructor() {
        super();

        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';
        this.email = '';
        this.enabled = null;
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';

        }

}
