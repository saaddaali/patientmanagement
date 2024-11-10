
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CapteurTypeDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;



    constructor() {
        super();

        this.code = '';
        this.libelle = '';
        this.description = '';

        }

}
