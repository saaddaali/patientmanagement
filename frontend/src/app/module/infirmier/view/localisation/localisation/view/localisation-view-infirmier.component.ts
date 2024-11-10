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


import {LocalisationInfirmierService} from 'src/app/shared/service/infirmier/localisation/LocalisationInfirmier.service';
import {LocalisationDto} from 'src/app/shared/model/localisation/Localisation.model';
import {LocalisationCriteria} from 'src/app/shared/criteria/localisation/LocalisationCriteria.model';

import {CapteurDto} from 'src/app/shared/model/sensor/Capteur.model';
import {CapteurInfirmierService} from 'src/app/shared/service/infirmier/sensor/CapteurInfirmier.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientInfirmierService} from 'src/app/shared/service/infirmier/patient/PatientInfirmier.service';
@Component({
  selector: 'app-localisation-view-infirmier',
  templateUrl: './localisation-view-infirmier.component.html'
})
export class LocalisationViewInfirmierComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: LocalisationInfirmierService, private capteurService: CapteurInfirmierService, private patientService: PatientInfirmierService){
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
    get capteur(): CapteurDto {
        return this.capteurService.item;
    }
    set capteur(value: CapteurDto) {
        this.capteurService.item = value;
    }
    get capteurs(): Array<CapteurDto> {
        return this.capteurService.items;
    }
    set capteurs(value: Array<CapteurDto>) {
        this.capteurService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<LocalisationDto> {
        return this.service.items;
    }

    set items(value: Array<LocalisationDto>) {
        this.service.items = value;
    }

    get item(): LocalisationDto {
        return this.service.item;
    }

    set item(value: LocalisationDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): LocalisationCriteria {
        return this.service.criteria;
    }

    set criteria(value: LocalisationCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
