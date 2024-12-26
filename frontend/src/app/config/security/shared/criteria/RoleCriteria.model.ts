import {BaseCriteria} from 'src/app/config/criteria/BaseCriteria.model';

export class RoleCriteria  extends BaseCriteria  {

    public id: number;
    public authority: string;
    public authorityLike: string;

}
