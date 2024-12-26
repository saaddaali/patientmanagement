
import {BaseCriteria} from 'src/app/config/criteria/BaseCriteria.model';

export class GenderCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;

}
