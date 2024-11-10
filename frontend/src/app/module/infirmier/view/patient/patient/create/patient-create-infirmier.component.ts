import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-patient-create-infirmier',
  templateUrl: './patient-create-infirmier.component.html'
})
export class PatientCreateInfirmierComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    protected infirmierPatientsIndex = -1;
    protected warningPatientsIndex = -1;

    private _infirmierPatientsElement = new InfirmierPatientDto();
    private _warningPatientsElement = new WarningPatientDto();



	constructor(private service: PatientInfirmierService , private infirmierService: InfirmierInfirmierService, private infirmierPatientService: InfirmierPatientInfirmierService, private genderService: GenderInfirmierService, private doctorService: DoctorInfirmierService, private warningPatientService: WarningPatientInfirmierService, private warningTypeService: WarningTypeInfirmierService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.infirmierPatientsElement.infirmier = new InfirmierDto();
        this.infirmierService.findAll().subscribe((data) => this.infirmiers = data);
        this.warningPatientsElement.warningType = new WarningTypeDto();
        this.warningTypeService.findAll().subscribe((data) => this.warningTypes = data);
        this.genderService.findAll().subscribe((data) => this.genders = data);
        this.doctorService.findAll().subscribe((data) => this.doctorInCharges = data);
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new PatientDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }



    validateInfirmierPatients(){
        this.errorMessages = new Array();
    }
    validateWarningPatients(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
    }

    public addInfirmierPatients() {
        if( this.item.infirmierPatients == null )
            this.item.infirmierPatients = new Array<InfirmierPatientDto>();

       this.validateInfirmierPatients();

       if (this.errorMessages.length === 0) {
            if (this.infirmierPatientsIndex == -1){
                this.item.infirmierPatients.push({... this.infirmierPatientsElement});
            }else {
                this.item.infirmierPatients[this.infirmierPatientsIndex] =this.infirmierPatientsElement;
            }
              this.infirmierPatientsElement = new InfirmierPatientDto();
              this.infirmierPatientsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteInfirmierPatients(p: InfirmierPatientDto, index: number) {
        this.item.infirmierPatients.splice(index, 1);
    }

    public editInfirmierPatients(p: InfirmierPatientDto, index: number) {
        this.infirmierPatientsElement = {... p};
        this.infirmierPatientsIndex = index;
        this.activeTab = 0;
    }
    public addWarningPatients() {
        if( this.item.warningPatients == null )
            this.item.warningPatients = new Array<WarningPatientDto>();

       this.validateWarningPatients();

       if (this.errorMessages.length === 0) {
            if (this.warningPatientsIndex == -1){
                this.item.warningPatients.push({... this.warningPatientsElement});
            }else {
                this.item.warningPatients[this.warningPatientsIndex] =this.warningPatientsElement;
            }
              this.warningPatientsElement = new WarningPatientDto();
              this.warningPatientsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteWarningPatients(p: WarningPatientDto, index: number) {
        this.item.warningPatients.splice(index, 1);
    }

    public editWarningPatients(p: WarningPatientDto, index: number) {
        this.warningPatientsElement = {... p};
        this.warningPatientsIndex = index;
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateGender(gender: string) {
    const isPermistted = await this.roleService.isPermitted('Gender', 'add');
    if(isPermistted) {
         this.gender = new GenderDto();
         this.createGenderDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createDoctorInChargeDialog(): boolean {
        return this.doctorService.createDialog;
    }
    set createDoctorInChargeDialog(value: boolean) {
        this.doctorService.createDialog= value;
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
    get createGenderDialog(): boolean {
        return this.genderService.createDialog;
    }
    set createGenderDialog(value: boolean) {
        this.genderService.createDialog= value;
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
    get createWarningTypeDialog(): boolean {
        return this.warningTypeService.createDialog;
    }
    set createWarningTypeDialog(value: boolean) {
        this.warningTypeService.createDialog= value;
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
    get createInfirmierDialog(): boolean {
        return this.infirmierService.createDialog;
    }
    set createInfirmierDialog(value: boolean) {
        this.infirmierService.createDialog= value;
    }





    get infirmierPatientsElement(): InfirmierPatientDto {
        if( this._infirmierPatientsElement == null )
            this._infirmierPatientsElement = new InfirmierPatientDto();
        return this._infirmierPatientsElement;
    }

    set infirmierPatientsElement(value: InfirmierPatientDto) {
        this._infirmierPatientsElement = value;
    }
    get warningPatientsElement(): WarningPatientDto {
        if( this._warningPatientsElement == null )
            this._warningPatientsElement = new WarningPatientDto();
        return this._warningPatientsElement;
    }

    set warningPatientsElement(value: WarningPatientDto) {
        this._warningPatientsElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): PatientCriteria {
        return this.service.criteria;
    }

    set criteria(value: PatientCriteria) {
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
