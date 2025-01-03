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




import {SafeZoneDoctorService} from 'src/app/shared/service/doctor/localisation/SafeZoneDoctor.service';
import {SafeZoneDto} from 'src/app/shared/model/localisation/SafeZone.model';
import {SafeZoneCriteria} from 'src/app/shared/criteria/localisation/SafeZoneCriteria.model';


import {PatientDto} from 'src/app/shared/model/patient/Patient.model';
import {PatientDoctorService} from 'src/app/shared/service/doctor/patient/PatientDoctor.service';

@Component({
  selector: 'app-safe-zone-edit-doctor',
  templateUrl: './safe-zone-edit-doctor.component.html',
    styleUrls: ['./safe-zone-edit-doctor.component.scss']
})
export class SafeZoneEditDoctorComponent implements OnInit {

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

    map: any;
    circle: any;
    marker: any;







    constructor(private service: SafeZoneDoctorService , private patientService: PatientDoctorService, @Inject(PLATFORM_ID) private platformId?) {
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



    ngAfterViewInit(): void {
        this.initMap();
    }

    initMap() {
        const mapOptions = {
            center: { lat: this.item.centreLatitude || 33.5897, lng: this.item.centreLongitude || -7.6039 },
            zoom: 10,
        };
        const map = new google.maps.Map(document.getElementById("map") as HTMLElement, mapOptions);

        const marker = new google.maps.Marker({
            position: { lat: this.item.centreLatitude || 33.5897, lng: this.item.centreLongitude || -7.6039 },
            map: map,
            draggable: true,
        });

        google.maps.event.addListener(marker, 'dragend', (event: any) => {
            this.item.centreLatitude = event.latLng.lat();
            this.item.centreLongitude = event.latLng.lng();
        });

        google.maps.event.addListener(map, 'click', (event: any) => {
            this.item.centreLatitude = event.latLng.lat();
            this.item.centreLongitude = event.latLng.lng();
            marker.setPosition(event.latLng);
        });
    }


    updateMap() {
        // If the circle already exists, remove it
        if (this.circle) {
            this.circle.setMap(null);
        }

        // Update the map center and marker position
        const center = new google.maps.LatLng(this.item.centreLatitude, this.item.centreLongitude);
        this.map.setCenter(center);
        this.marker.setPosition(center);

        // Add a new circle based on the radius
        if (this.item.rayon > 0) {
            this.circle = new google.maps.Circle({
                map: this.map,
                radius: this.item.rayon, // radius in meters
                center: center,
                strokeColor: '#FF0000',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#FF0000',
                fillOpacity: 0.35
            });
        }
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
