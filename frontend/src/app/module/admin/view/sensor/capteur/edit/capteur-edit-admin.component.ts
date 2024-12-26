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




import {CapteurAdminService} from 'src/app/shared/service/admin/sensor/CapteurAdmin.service';
import {CapteurDto} from 'src/app/shared/model/sensor/Capteur.model';
import {CapteurCriteria} from 'src/app/shared/criteria/sensor/CapteurCriteria.model';


import {CapteurTypeDto} from 'src/app/shared/model/sensor/CapteurType.model';
import {CapteurTypeAdminService} from 'src/app/shared/service/admin/sensor/CapteurTypeAdmin.service';

@Component({
  selector: 'app-capteur-edit-admin',
  templateUrl: './capteur-edit-admin.component.html'
})
export class CapteurEditAdminComponent implements OnInit {

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







    constructor(private service: CapteurAdminService , private capteurTypeService: CapteurTypeAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.capteurTypeService.findAll().subscribe((data) => this.capteurTypes = data);
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
            this.item = new CapteurDto();
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




   public async openCreateCapteurType(capteurType: string) {
        const isPermistted = await this.roleService.isPermitted('CapteurType', 'edit');
        if (isPermistted) {
             this.capteurType = new CapteurTypeDto();
             this.createCapteurTypeDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }

    get capteurType(): CapteurTypeDto {
        return this.capteurTypeService.item;
    }
    set capteurType(value: CapteurTypeDto) {
        this.capteurTypeService.item = value;
    }
    get capteurTypes(): Array<CapteurTypeDto> {
        return this.capteurTypeService.items;
    }
    set capteurTypes(value: Array<CapteurTypeDto>) {
        this.capteurTypeService.items = value;
    }
    get createCapteurTypeDialog(): boolean {
        return this.capteurTypeService.createDialog;
    }
    set createCapteurTypeDialog(value: boolean) {
        this.capteurTypeService.createDialog= value;
    }




	get items(): Array<CapteurDto> {
        return this.service.items;
    }

    set items(value: Array<CapteurDto>) {
        this.service.items = value;
    }

    get item(): CapteurDto {
        return this.service.item;
    }

    set item(value: CapteurDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): CapteurCriteria {
        return this.service.criteria;
    }

    set criteria(value: CapteurCriteria) {
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
