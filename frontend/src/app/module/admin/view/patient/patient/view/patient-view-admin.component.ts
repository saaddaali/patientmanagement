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


import {PatientAdminService} from 'src/app/shared/service/admin/patient/PatientAdmin.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientCriteria} from 'src/app/shared/criteria/patient/PatientCriteria.model';

import {InfirmierDto} from 'src/app/shared/model/staff/Infirmier.model';
import {InfirmierAdminService} from 'src/app/shared/service/admin/staff/InfirmierAdmin.service';
import {InfirmierPatientDto} from 'src/app/shared/model/healthcare/InfirmierPatient.model';
import {InfirmierPatientAdminService} from 'src/app/shared/service/admin/healthcare/InfirmierPatientAdmin.service';
import {GenderDto} from 'src/app/shared/model/patient/Gender.model';
import {GenderAdminService} from 'src/app/shared/service/admin/patient/GenderAdmin.service';
import {DoctorDto} from 'src/app/shared/model/staff/Doctor.model';
import {DoctorAdminService} from 'src/app/shared/service/admin/staff/DoctorAdmin.service';
import {WarningPatientDto} from 'src/app/shared/model/warning/WarningPatient.model';
import {WarningPatientAdminService} from 'src/app/shared/service/admin/warning/WarningPatientAdmin.service';
import {WarningTypeDto} from 'src/app/shared/model/warning/WarningType.model';
import {WarningTypeAdminService} from 'src/app/shared/service/admin/warning/WarningTypeAdmin.service';
@Component({
  selector: 'app-patient-view-admin',
  templateUrl: './patient-view-admin.component.html'
})
export class PatientViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    infirmierPatients = new InfirmierPatientDto();
    infirmierPatientss: Array<InfirmierPatientDto> = [];
    warningPatients = new WarningPatientDto();
    warningPatientss: Array<WarningPatientDto> = [];

    constructor(private service: PatientAdminService, private infirmierService: InfirmierAdminService, private infirmierPatientService: InfirmierPatientAdminService, private genderService: GenderAdminService, private doctorService: DoctorAdminService, private warningPatientService: WarningPatientAdminService, private warningTypeService: WarningTypeAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get doctorInCharge(): DoctorDto {
        return this.doctorService.item;
    }
    set doctorInCharge(value: DoctorDto) {
        this.doctorService.item = value;
    }
    get doctorInCharges(): Array<DoctorDto> {
        return this.doctorService.items;
    }
    set doctorInCharges(value: Array<DoctorDto>) {
        this.doctorService.items = value;
    }
    get gender(): GenderDto {
        return this.genderService.item;
    }
    set gender(value: GenderDto) {
        this.genderService.item = value;
    }
    get genders(): Array<GenderDto> {
        return this.genderService.items;
    }
    set genders(value: Array<GenderDto>) {
        this.genderService.items = value;
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
    get infirmier(): InfirmierDto {
        return this.infirmierService.item;
    }
    set infirmier(value: InfirmierDto) {
        this.infirmierService.item = value;
    }
    get infirmiers(): Array<InfirmierDto> {
        return this.infirmierService.items;
    }
    set infirmiers(value: Array<InfirmierDto>) {
        this.infirmierService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<PatientDto> {
        return this.service.items;
    }

    set items(value: Array<PatientDto>) {
        this.service.items = value;
    }

    get item(): PatientDto {
        return this.service.item;
    }

    set item(value: PatientDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): PatientCriteria {
        return this.service.criteria;
    }

    set criteria(value: PatientCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
