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


import {DoctorAdminService} from 'src/app/shared/service/admin/staff/DoctorAdmin.service';
import {DoctorDto} from 'src/app/shared/model/staff/Doctor.model';
import {DoctorCriteria} from 'src/app/shared/criteria/staff/DoctorCriteria.model';

import {SpecializationDto} from 'src/app/shared/model/staff/Specialization.model';
import {SpecializationAdminService} from 'src/app/shared/service/admin/staff/SpecializationAdmin.service';
@Component({
  selector: 'app-doctor-view-admin',
  templateUrl: './doctor-view-admin.component.html'
})
export class DoctorViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: DoctorAdminService, private specializationService: SpecializationAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get specialization(): SpecializationDto {
        return this.specializationService.item;
    }
    set specialization(value: SpecializationDto) {
        this.specializationService.item = value;
    }
    get specializations(): Array<SpecializationDto> {
        return this.specializationService.items;
    }
    set specializations(value: Array<SpecializationDto>) {
        this.specializationService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<DoctorDto> {
        return this.service.items;
    }

    set items(value: Array<DoctorDto>) {
        this.service.items = value;
    }

    get item(): DoctorDto {
        return this.service.item;
    }

    set item(value: DoctorDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): DoctorCriteria {
        return this.service.criteria;
    }

    set criteria(value: DoctorCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
