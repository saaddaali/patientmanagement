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


import {SafeZoneDoctorService} from 'src/app/shared/service/doctor/localisation/SafeZoneDoctor.service';
import {SafeZoneDto} from 'src/app/shared/model/localisation/SafeZone.model';
import {SafeZoneCriteria} from 'src/app/shared/criteria/localisation/SafeZoneCriteria.model';

import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientDoctorService} from 'src/app/shared/service/doctor/patient/PatientDoctor.service';
@Component({
  selector: 'app-safe-zone-view-doctor',
  templateUrl: './safe-zone-view-doctor.component.html',
    styleUrls: ['./safe-zone-view-doctor.component.scss']
})
export class SafeZoneViewDoctorComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: SafeZoneDoctorService, private patientService: PatientDoctorService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get linkedPatient(): PatientDto {
        return this.patientService.item;
    }
    set linkedPatient(value: PatientDto) {
        this.patientService.item = value;
    }
    get linkedPatients(): Array<PatientDto> {
        return this.patientService.items;
    }
    set linkedPatients(value: Array<PatientDto>) {
        this.patientService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<SafeZoneDto> {
        return this.service.items;
    }

    set items(value: Array<SafeZoneDto>) {
        this.service.items = value;
    }

    get item(): SafeZoneDto {
        return this.service.item;
    }

    set item(value: SafeZoneDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): SafeZoneCriteria {
        return this.service.criteria;
    }

    set criteria(value: SafeZoneCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
