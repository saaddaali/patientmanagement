import {Component, OnInit} from '@angular/core';
import {PatientInfirmierService} from 'src/app/shared/service/infirmier/patient/PatientInfirmier.service';
import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientCriteria} from 'src/app/shared/criteria/patient/PatientCriteria.model';


import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/config/dto/FileTempDto.model';
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

import {AuthService} from 'src/app/config/security/shared/service/Auth.service';
import {ExportService} from 'src/app/config/util/Export.service';


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
  selector: 'app-patient-list-infirmier',
  templateUrl: './patient-list-infirmier.component.html'
})
export class PatientListInfirmierComponent implements OnInit {

    protected fileName = 'Patient';

    protected findByCriteriaShow = false;
    protected cols: any[] = [];
    protected excelPdfButons: MenuItem[];
    protected exportData: any[] = [];
    protected criteriaData: any[] = [];
    protected _totalRecords = 0;
    private _pdfName: string;


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    protected authService: AuthService;
    protected exportService: ExportService;
    protected excelFile: File | undefined;
    protected enableSecurity = false;


     yesOrNoPasswordChanged: any[] = [];
     yesOrNoAccountNonLocked: any[] = [];
     yesOrNoEnabled: any[] = [];
     yesOrNoCredentialsNonExpired: any[] = [];
     yesOrNoAccountNonExpired: any[] = [];
    genders: Array<GenderDto>;
    doctorInCharges: Array<DoctorDto>;


    constructor( private service: PatientInfirmierService  , private infirmierService: InfirmierInfirmierService, private infirmierPatientService: InfirmierPatientInfirmierService, private genderService: GenderInfirmierService, private doctorService: DoctorInfirmierService, private warningPatientService: WarningPatientInfirmierService, private warningTypeService: WarningTypeInfirmierService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.authService = ServiceLocator.injector.get(AuthService);
        this.exportService = ServiceLocator.injector.get(ExportService);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
        this.loadGender();
        this.loadDoctorInCharge();
        this.yesOrNoPasswordChanged =  [{label: 'PasswordChanged', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];
        this.yesOrNoAccountNonLocked =  [{label: 'AccountNonLocked', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];
        this.yesOrNoEnabled =  [{label: 'Enabled', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];
        this.yesOrNoCredentialsNonExpired =  [{label: 'CredentialsNonExpired', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];
        this.yesOrNoAccountNonExpired =  [{label: 'AccountNonExpired', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];

    }




    public onExcelFileSelected(event: any): void {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files.length > 0) {
            this.excelFile = input.files[0];
        }
    }

    public importExcel(): void {
        if (this.excelFile) {
            this.service.importExcel(this.excelFile).subscribe(
                response => {
                    this.items = response;
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'File uploaded successfully!',
                        life: 3000
                    });
                },
                error => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'File uploaded with Error!',
                        life: 3000
                    });
                }
            );
        }
    }

    public findPaginatedByCriteria() {
        this.service.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.totalRecords = paginatedItems.dataSize;
            this.selections = new Array<PatientDto>();
        }, error => console.log(error));
    }

    public onPage(event: any) {
        this.criteria.page = event.page;
        this.criteria.maxResults = event.rows;
        this.findPaginatedByCriteria();
    }

    public async edit(dto: PatientDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            console.log(res);
            this.editDialog = true;
        });

    }

    public async view(dto: PatientDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            this.viewDialog = true;
        });
    }

    public async openCreate() {
        this.item = new PatientDto();
        this.createDialog = true;
    }

    public async deleteMultiple() {
        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer ces éléments ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.deleteMultiple().subscribe(() => {
                    for (let selection of this.selections) {
                        let index = this.items.findIndex(element => element.id === selection.id);
                        this.items.splice(index,1);
                    }
                    this.selections = new Array<PatientDto>();
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Les éléments sélectionnés ont été supprimés',
                        life: 3000
                    });

                }, error => console.log(error));
            }
        });
    }


    public isSelectionDisabled(): boolean {
        return this.selections == null || this.selections.length == 0;
    }


    public async delete(dto: PatientDto) {

        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer cet élément ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.delete(dto).subscribe(status => {
                    if (status > 0) {
                        const position = this.items.indexOf(dto);
                        position > -1 ? this.items.splice(position, 1) : false;
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Succès',
                            detail: 'Element Supprimé',
                            life: 3000
                        });
                    }

                }, error => console.log(error));
            }
        });

    }

    public async duplicate(dto: PatientDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(
            res => {
                this.initDuplicate(res);
                this.item = res;
                this.item.id = null;
                this.createDialog = true;
            });
    }

    // TODO : check if correct
    public initExport(): void {
        this.excelPdfButons = [
            {
                label: 'CSV', icon: 'pi pi-file', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterCSV(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'XLS', icon: 'pi pi-file-excel', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterExcel(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'PDF', icon: 'pi pi-file-pdf', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterPdf(this.criteriaData, this.exportData, this.fileName);
                }
            }
        ];
    }

    public exportPdf(dto: PatientDto): void {
        this.service.exportPdf(dto).subscribe((data: ArrayBuffer) => {
            const blob = new Blob([data], {type: 'application/pdf'});
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.download = this.pdfName;
            link.setAttribute('target', '_blank'); // open link in new tab
            link.click();
            window.URL.revokeObjectURL(url);
        }, (error) => {
            console.error(error); // handle any errors that occur
        });
    }

    public showSearch(): void {
        this.findByCriteriaShow = !this.findByCriteriaShow;
    }


    update() {
        this.service.edit().subscribe(data => {
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = data;
            this.editDialog = false;
            this.item = new PatientDto();
        } , error => {
            console.log(error);
        });
    }

    public save() {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;


                this.item = new PatientDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }
        }, error => {
            console.log(error);
        });
    }

// add


    public initCol() {
        this.cols = [
            {field: 'dateOfBirth', header: 'Date of birth'},
            {field: 'gender?.libelle', header: 'Gender'},
            {field: 'emergencyContact', header: 'Emergency contact'},
            {field: 'doctorInCharge?.email', header: 'Doctor in charge'},
            {field: 'passwordChanged', header: 'Password changed'},
            {field: 'accountNonLocked', header: 'Account non locked'},
            {field: 'password', header: 'Password'},
            {field: 'email', header: 'Email'},
            {field: 'enabled', header: 'Enabled'},
            {field: 'credentialsNonExpired', header: 'Credentials non expired'},
            {field: 'accountNonExpired', header: 'Account non expired'},
            {field: 'username', header: 'Username'},
        ];
    }


    public async loadGender(){
        this.genderService.findAllOptimized().subscribe(genders => this.genders = genders, error => console.log(error))
    }
    public async loadDoctorInCharge(){
        this.doctorService.findAllOptimized().subscribe(doctorInCharges => this.doctorInCharges = doctorInCharges, error => console.log(error))
    }


	public initDuplicate(res: PatientDto) {
        if (res.infirmierPatients != null) {
             res.infirmierPatients.forEach(d => { d.patient = null; d.id = null; });
        }
        if (res.warningPatients != null) {
             res.warningPatients.forEach(d => { d.patient = null; d.id = null; });
        }
	}


    public prepareColumnExport(): void {
        this.service.findByCriteria(this.criteria).subscribe(
            (allItems) =>{
                this.exportData = allItems.map(e => {
					return {
						'Date of birth': this.datePipe.transform(e.dateOfBirth , 'dd/MM/yyyy hh:mm'),
						'Gender': e.gender?.libelle ,
						'Address': e.address ,
						'Emergency contact': e.emergencyContact ,
						'Doctor in charge': e.doctorInCharge?.email ,
						'Password changed': e.passwordChanged? 'Vrai' : 'Faux' ,
						'Account non locked': e.accountNonLocked? 'Vrai' : 'Faux' ,
						'Password': e.password ,
						'Email': e.email ,
						'Enabled': e.enabled? 'Vrai' : 'Faux' ,
						'Credentials non expired': e.credentialsNonExpired? 'Vrai' : 'Faux' ,
						'Account non expired': e.accountNonExpired? 'Vrai' : 'Faux' ,
						'Username': e.username ,
					}
				});

            this.criteriaData = [{
                'Date of birth Min': this.criteria.dateOfBirthFrom ? this.datePipe.transform(this.criteria.dateOfBirthFrom , this.dateFormat) : environment.emptyForExport ,
                'Date of birth Max': this.criteria.dateOfBirthTo ? this.datePipe.transform(this.criteria.dateOfBirthTo , this.dateFormat) : environment.emptyForExport ,
            //'Gender': this.criteria.gender?.libelle ? this.criteria.gender?.libelle : environment.emptyForExport ,
                'Address': this.criteria.address ? this.criteria.address : environment.emptyForExport ,
                'Emergency contact': this.criteria.emergencyContact ? this.criteria.emergencyContact : environment.emptyForExport ,
            //'Doctor in charge': this.criteria.doctorInCharge?.email ? this.criteria.doctorInCharge?.email : environment.emptyForExport ,
                'Password changed': this.criteria.passwordChanged ? (this.criteria.passwordChanged ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
                'Account non locked': this.criteria.accountNonLocked ? (this.criteria.accountNonLocked ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
                'Password': this.criteria.password ? this.criteria.password : environment.emptyForExport ,
                'Email': this.criteria.email ? this.criteria.email : environment.emptyForExport ,
                'Enabled': this.criteria.enabled ? (this.criteria.enabled ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
                'Credentials non expired': this.criteria.credentialsNonExpired ? (this.criteria.credentialsNonExpired ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
                'Account non expired': this.criteria.accountNonExpired ? (this.criteria.accountNonExpired ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
                'Username': this.criteria.username ? this.criteria.username : environment.emptyForExport ,
            }];
			}

        )
    }


    get items(): Array<PatientDto> {
        return this.service.items;
    }

    set items(value: Array<PatientDto>) {
        this.service.items = value;
    }

    get selections(): Array<PatientDto> {
        return this.service.selections;
    }

    set selections(value: Array<PatientDto>) {
        this.service.selections = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
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

    get dateFormat() {
        return environment.dateFormatList;
    }


    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    get pdfName(): string {
        return this._pdfName;
    }

    set pdfName(value: string) {
        this._pdfName = value;
    }

    get createActionIsValid(): boolean {
        return this.service.createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this.service.createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this.service.editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this.service.editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this.service.listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this.service.listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this.service.deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this.service.deleteActionIsValid = value;
    }


    get viewActionIsValid(): boolean {
        return this.service.viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this.service.viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this.service.duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this.service.duplicateActionIsValid = value;
    }

    get createAction(): string {
        return this.service.createAction;
    }

    set createAction(value: string) {
        this.service.createAction = value;
    }

    get listAction(): string {
        return this.service.listAction;
    }

    set listAction(value: string) {
        this.service.listAction = value;
    }

    get editAction(): string {
        return this.service.editAction;
    }

    set editAction(value: string) {
        this.service.editAction = value;
    }

    get deleteAction(): string {
        return this.service.deleteAction;
    }

    set deleteAction(value: string) {
        this.service.deleteAction = value;
    }

    get viewAction(): string {
        return this.service.viewAction;
    }

    set viewAction(value: string) {
        this.service.viewAction = value;
    }

    get duplicateAction(): string {
        return this.service.duplicateAction;
    }

    set duplicateAction(value: string) {
        this.service.duplicateAction = value;
    }

    get entityName(): string {
        return this.service.entityName;
    }

    set entityName(value: string) {
        this.service.entityName = value;
    }
}
