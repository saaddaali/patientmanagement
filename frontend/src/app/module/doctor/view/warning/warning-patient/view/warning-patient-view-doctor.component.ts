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


import {WarningPatientDoctorService} from 'src/app/shared/service/doctor/warning/WarningPatientDoctor.service';
import {WarningPatientDto} from 'src/app/shared/model/warning/WarningPatient.model';
import {WarningPatientCriteria} from 'src/app/shared/criteria/warning/WarningPatientCriteria.model';

import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientDoctorService} from 'src/app/shared/service/doctor/patient/PatientDoctor.service';
import {WarningTypeDto} from 'src/app/shared/model/warning/WarningType.model';
import {WarningTypeDoctorService} from 'src/app/shared/service/doctor/warning/WarningTypeDoctor.service';
@Component({
  selector: 'app-warning-patient-view-doctor',
  templateUrl: './warning-patient-view-doctor.component.html',
    styleUrls: ['./warning-patient-view-doctor.component.scss']
})
export class WarningPatientViewDoctorComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: WarningPatientDoctorService, private patientService: PatientDoctorService, private warningTypeService: WarningTypeDoctorService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get patient(): PatientDto {
        return this.patientService.item;
    }
    set patient(value: PatientDto) {
        this.patientService.item = value;
    }
    get patients(): Array<PatientDto> {
        return this.patientService.items;
    }
    set patients(value: Array<PatientDto>) {
        this.patientService.items = value;
    }
    get warningType(): WarningTypeDto {
        return this.warningTypeService.item;
    }
    set warningType(value: WarningTypeDto) {
        this.warningTypeService.item = value;
    }
    get warningTypes(): Array<WarningTypeDto> {
        return this.warningTypeService.items;
    }
    set warningTypes(value: Array<WarningTypeDto>) {
        this.warningTypeService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<WarningPatientDto> {
        return this.service.items;
    }

    set items(value: Array<WarningPatientDto>) {
        this.service.items = value;
    }

    get item(): WarningPatientDto {
        return this.service.item;
    }

    set item(value: WarningPatientDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): WarningPatientCriteria {
        return this.service.criteria;
    }

    set criteria(value: WarningPatientCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
