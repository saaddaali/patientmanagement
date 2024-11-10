import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {SafeZoneInfirmierService} from 'src/app/shared/service/infirmier/localisation/SafeZoneInfirmier.service';
import {SafeZoneDto} from 'src/app/shared/model/localisation/SafeZone.model';
import {SafeZoneCriteria} from 'src/app/shared/criteria/localisation/SafeZoneCriteria.model';


import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientInfirmierService} from 'src/app/shared/service/infirmier/patient/PatientInfirmier.service';

@Component({
  selector: 'app-safe-zone-edit-infirmier',
  templateUrl: './safe-zone-edit-infirmier.component.html'
})
export class SafeZoneEditInfirmierComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;







    constructor(private service: SafeZoneInfirmierService , private patientService: PatientInfirmierService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.patientService.findAll().subscribe((data) => this.linkedPatients = data);
    }

    public prepareEdit() {
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new SafeZoneDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
    }


    public validateForm(): void{
        this.errorMessages = new Array<string>();
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
    get createLinkedPatientDialog(): boolean {
        return this.patientService.createDialog;
    }
    set createLinkedPatientDialog(value: boolean) {
        this.patientService.createDialog= value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): SafeZoneCriteria {
        return this.service.criteria;
    }

    set criteria(value: SafeZoneCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }


}
