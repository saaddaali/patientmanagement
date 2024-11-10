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




import {InfirmierDoctorService} from 'src/app/shared/service/doctor/staff/InfirmierDoctor.service';
import {InfirmierDto} from 'src/app/shared/model/staff/Infirmier.model';
import {InfirmierCriteria} from 'src/app/shared/criteria/staff/InfirmierCriteria.model';


import {SpecializationDto} from 'src/app/shared/model/staff/Specialization.model';
import {SpecializationDoctorService} from 'src/app/shared/service/doctor/staff/SpecializationDoctor.service';

@Component({
  selector: 'app-infirmier-edit-doctor',
  templateUrl: './infirmier-edit-doctor.component.html'
})
export class InfirmierEditDoctorComponent implements OnInit {

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







    constructor(private service: InfirmierDoctorService , private specializationService: SpecializationDoctorService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.specializationService.findAll().subscribe((data) => this.specializations = data);
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
            this.item = new InfirmierDto();
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




   public async openCreateSpecialization(specialization: string) {
        const isPermistted = await this.roleService.isPermitted('Specialization', 'edit');
        if (isPermistted) {
             this.specialization = new SpecializationDto();
             this.createSpecializationDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
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
    get createSpecializationDialog(): boolean {
        return this.specializationService.createDialog;
    }
    set createSpecializationDialog(value: boolean) {
        this.specializationService.createDialog= value;
    }




	get items(): Array<InfirmierDto> {
        return this.service.items;
    }

    set items(value: Array<InfirmierDto>) {
        this.service.items = value;
    }

    get item(): InfirmierDto {
        return this.service.item;
    }

    set item(value: InfirmierDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): InfirmierCriteria {
        return this.service.criteria;
    }

    set criteria(value: InfirmierCriteria) {
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
