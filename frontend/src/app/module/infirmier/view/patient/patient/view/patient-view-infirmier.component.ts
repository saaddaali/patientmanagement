import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {PatientInfirmierService} from 'src/app/shared/service/infirmier/patient/PatientInfirmier.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientCriteria} from 'src/app/shared/criteria/patient/PatientCriteria.model';

import {InfirmierDto} from 'src/app/shared/model/staff/Infirmier.model';
import {InfirmierInfirmierService} from 'src/app/shared/service/infirmier/staff/InfirmierInfirmier.service';
import {InfirmierPatientDto} from 'src/app/shared/model/healthcare/InfirmierPatient.model';
import {InfirmierPatientInfirmierService} from 'src/app/shared/service/infirmier/healthcare/InfirmierPatientInfirmier.service';
import {GenderDto} from 'src/app/shared/model/patient/Gender.model';
import {GenderInfirmierService} from 'src/app/shared/service/infirmier/patient/GenderInfirmier.service';
import {DoctorDto} from 'src/app/shared/model/staff/Doctor.model';
import {DoctorInfirmierService} from 'src/app/shared/service/infirmier/staff/DoctorInfirmier.service';
import {WarningPatientDto} from 'src/app/shared/model/warning/WarningPatient.model';
import {WarningPatientInfirmierService} from 'src/app/shared/service/infirmier/warning/WarningPatientInfirmier.service';
import {WarningTypeDto} from 'src/app/shared/model/warning/WarningType.model';
import {WarningTypeInfirmierService} from 'src/app/shared/service/infirmier/warning/WarningTypeInfirmier.service';
@Component({
  selector: 'app-patient-view-infirmier',
  templateUrl: './patient-view-infirmier.component.html'
})
export class PatientViewInfirmierComponent implements OnInit {


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

    constructor(private service: PatientInfirmierService, private infirmierService: InfirmierInfirmierService, private infirmierPatientService: InfirmierPatientInfirmierService, private genderService: GenderInfirmierService, private doctorService: DoctorInfirmierService, private warningPatientService: WarningPatientInfirmierService, private warningTypeService: WarningTypeInfirmierService){
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
