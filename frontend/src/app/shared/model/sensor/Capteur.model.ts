import {CapteurTypeDto} from './CapteurType.model';

import {BaseDto} from 'src/app/config/dto/BaseDto.model';


export class CapteurDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

    public capteurType: CapteurTypeDto ;


    constructor() {
        super();

        this.code = '';
        this.libelle = '';
        this.description = '';
        this.capteurType = new CapteurTypeDto() ;

        }

}
