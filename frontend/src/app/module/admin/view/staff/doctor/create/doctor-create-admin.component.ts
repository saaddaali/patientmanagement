import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {DoctorAdminService} from 'src/app/shared/service/admin/staff/DoctorAdmin.service';
import {DoctorDto} from 'src/app/shared/model/staff/Doctor.model';
import {DoctorCriteria} from 'src/app/shared/criteria/staff/DoctorCriteria.model';
import {SpecializationDto} from 'src/app/shared/model/staff/Specialization.model';
import {SpecializationAdminService} from 'src/app/shared/service/admin/staff/SpecializationAdmin.service';
@Component({
  selector: 'app-doctor-create-admin',
  templateUrl: './doctor-create-admin.component.html'
})
export class DoctorCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;




	constructor(private service: DoctorAdminService , private specializationService: SpecializationAdminService, @Inject(PLATFORM_ID) private platformId? ) {
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
                this.item = new DoctorDto();
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





    public  setValidation(value: boolean){
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateSpecialization(specialization: string) {
    const isPermistted = await this.roleService.isPermitted('Specialization', 'add');
    if(isPermistted) {
         this.specialization = new SpecializationDto();
         this.createSpecializationDialog = true;
    }else{
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): DoctorCriteria {
        return this.service.criteria;
    }

    set criteria(value: DoctorCriteria) {
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
