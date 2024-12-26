import {Component, OnInit} from '@angular/core';


import {AbstractViewController} from 'src/app/config/controller/AbstractViewController';
import { environment } from 'src/environments/environment';

import {RoleService} from 'src/app/config/security/shared/service/Role.service';
import {RoleDto} from 'src/app/config/security/shared/model/Role.model';
import {RoleCriteria} from 'src/app/config/security/shared/criteria/RoleCriteria.model';

@Component({
  selector: 'app-role-view',
  templateUrl: './role-view.component.html'
})
export class RoleViewComponent extends AbstractViewController<RoleDto, RoleCriteria, RoleService> implements OnInit {


    constructor(private roleservice: RoleService){
        super(roleservice);
    }

    ngOnInit(): void {
    }




}
