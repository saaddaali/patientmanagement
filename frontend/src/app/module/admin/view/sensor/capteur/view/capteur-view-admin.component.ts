import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/config/security/shared/service/Role.service';
import {AbstractService} from 'src/app/config/service/AbstractService';
import {BaseDto} from 'src/app/config/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/config/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/config/util/StringUtil.service';
import {ServiceLocator} from 'src/app/config/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/config/dto/FileTempDto.model';


import {CapteurAdminService} from 'src/app/shared/service/admin/sensor/CapteurAdmin.service';
import {CapteurDto} from 'src/app/shared/model/sensor/Capteur.model';
import {CapteurCriteria} from 'src/app/shared/criteria/sensor/CapteurCriteria.model';

import {CapteurTypeDto} from 'src/app/shared/model/sensor/CapteurType.model';
import {CapteurTypeAdminService} from 'src/app/shared/service/admin/sensor/CapteurTypeAdmin.service';
@Component({
  selector: 'app-capteur-view-admin',
  templateUrl: './capteur-view-admin.component.html'
})
export class CapteurViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: CapteurAdminService, private capteurTypeService: CapteurTypeAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get capteurType(): CapteurTypeDto {
        return this.capteurTypeService.item;
    }
    set capteurType(value: CapteurTypeDto) {
        this.capteurTypeService.item = value;
    }
    get capteurTypes(): Array<CapteurTypeDto> {
        return this.capteurTypeService.items;
    }
    set capteurTypes(value: Array<CapteurTypeDto>) {
        this.capteurTypeService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<CapteurDto> {
        return this.service.items;
    }

    set items(value: Array<CapteurDto>) {
        this.service.items = value;
    }

    get item(): CapteurDto {
        return this.service.item;
    }

    set item(value: CapteurDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): CapteurCriteria {
        return this.service.criteria;
    }

    set criteria(value: CapteurCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
