import { Component, OnInit, AfterViewInit, Inject, PLATFORM_ID } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { SafeZoneDoctorService } from 'src/app/shared/service/doctor/localisation/SafeZoneDoctor.service';
import { SafeZoneDto } from 'src/app/shared/model/localisation/SafeZone.model';
import { SafeZoneCriteria } from 'src/app/shared/criteria/localisation/SafeZoneCriteria.model';
import { PatientDto } from 'src/app/shared/model/patient/Patient.model';
import { PatientDoctorService } from 'src/app/shared/service/doctor/patient/PatientDoctor.service';
import { environment } from 'src/environments/environment';
import { ServiceLocator } from 'src/app/zynerator/service/ServiceLocator';
import {RoleService} from "../../../../../../zynerator/security/shared/service/Role.service";
import {StringUtilService} from "../../../../../../zynerator/util/StringUtil.service";

declare var google: any;  // Declare the Google Maps API global variable

@Component({
    selector: 'app-safe-zone-create-doctor',
    templateUrl: './safe-zone-create-doctor.component.html',
    styleUrls: ['./safe-zone-create-doctor.component.scss']
})
export class SafeZoneCreateDoctorComponent implements OnInit, AfterViewInit {

    protected _submitted = false;
    protected _errorMessages = new Array<string>();
    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;

    // Map-related properties
    map: any;
    marker: any;

    constructor(
        private service: SafeZoneDoctorService,
        private patientService: PatientDoctorService,
        @Inject(PLATFORM_ID) private platformId?
    ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        // Load linked patients
        this.patientService.findAll().subscribe((data) => this.linkedPatients = data);
    }

    ngAfterViewInit() {
        // Initialize the Google Map once the view is fully initialized
        this.initMap();
    }

    initMap() {
        const mapOptions = {
            center: { lat: 33.986, lng: -6.866 }, // Default center of the map (Marrakech)
            zoom: 12,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        this.map = new google.maps.Map(document.getElementById("map"), mapOptions);

        // Add a marker and set up a click event to get latitude and longitude
        this.marker = new google.maps.Marker({
            map: this.map,
            position: mapOptions.center,
            draggable: true
        });

        // When the map is clicked, set the marker position
        google.maps.event.addListener(this.map, 'click', (event) => {
            this.setMarkerPosition(event.latLng);
        });

        // When the marker is dragged, update the latitude and longitude
        google.maps.event.addListener(this.marker, 'dragend', () => {
            this.updateCoordinates(this.marker.getPosition());
        });
    }

    setMarkerPosition(latLng: any) {
        this.marker.setPosition(latLng);
        this.updateCoordinates(latLng);
    }

    updateCoordinates(latLng: any) {
        this.item.centreLatitude = latLng.lat();
        this.item.centreLongitude = latLng.lng();
    }

    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({ severity: 'error', summary: 'Erreurs', detail: 'Merci de corriger les erreurs sur le formulaire' });
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({ ...item });
                this.createDialog = false;
                this.submitted = false;
                this.item = new SafeZoneDto();
            } else {
                this.messageService.add({ severity: 'error', summary: 'Erreurs', detail: 'Element existant' });
            }
        }, error => {
            console.log(error);
        });
    }

    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }

    public setValidation(value: boolean) { }

    public validateForm(): void {
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
        this.patientService.createDialog = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
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
