import {BaseCriteria} from 'src/app/config/criteria/BaseCriteria.model';

export class ActionPermissionCriteria  extends BaseCriteria  {

    public id: number;
    public reference: string;
    public referenceLike: string;
    public libelle: string;
    public libelleLike: string;

}
