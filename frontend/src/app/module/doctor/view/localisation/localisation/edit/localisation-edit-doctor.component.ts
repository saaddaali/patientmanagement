import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/config/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/config/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/config/util/StringUtil.service';
import {ServiceLocator} from 'src/app/config/service/ServiceLocator';




import {LocalisationDoctorService} from 'src/app/shared/service/doctor/localisation/LocalisationDoctor.service';
import {LocalisationDto} from 'src/app/shared/model/localisation/Localisation.model';
import {LocalisationCriteria} from 'src/app/shared/criteria/localisation/LocalisationCriteria.model';


import {CapteurDto} from 'src/app/shared/model/sensor/Capteur.model';
import {CapteurDoctorService} from 'src/app/shared/service/doctor/sensor/CapteurDoctor.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientDoctorService} from 'src/app/shared/service/doctor/patient/PatientDoctor.service';

@Component({
  selector: 'app-localisation-edit-doctor',
  templateUrl: './localisation-edit-doctor.component.html',
    styleUrls: ['./localisation-edit-doctor.component.scss']
})
export class LocalisationEditDoctorComponent implements OnInit {

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







    constructor(private service: LocalisationDoctorService , private capteurService: CapteurDoctorService, private patientService: PatientDoctorService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.patientService.findAll().subscribe((data) => this.patients = data);
        this.capteurService.findAll().subscribe((data) => this.capteurs = data);
    }

    public prepareEdit() {
        this.item.dateLocalisation = this.service.format(this.item.dateLocalisation);
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
            this.item = new LocalisationDto();
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
    get createPatientDialog(): boolean {
        return this.patientService.createDialog;
    }
    set createPatientDialog(value: boolean) {
        this.patientService.createDialog= value;
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
    get createCapteurDialog(): boolean {
        return this.capteurService.createDialog;
    }
    set createCapteurDialog(value: boolean) {
        this.capteurService.createDialog= value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): LocalisationCriteria {
        return this.service.criteria;
    }

    set criteria(value: LocalisationCriteria) {
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
