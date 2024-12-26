import {BaseDto} from 'src/app/config/dto/BaseDto.model';


export class ActionPermissionDto extends BaseDto {

    public reference: string;

    public libelle: string;


    constructor() {
        super();

        this.reference = '';
        this.libelle = '';

    }

}
