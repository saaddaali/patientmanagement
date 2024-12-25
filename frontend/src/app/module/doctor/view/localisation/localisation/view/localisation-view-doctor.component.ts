import { Component, OnInit, OnDestroy } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { RoleService } from 'src/app/zynerator/security/shared/service/Role.service';
import { StringUtilService } from 'src/app/zynerator/util/StringUtil.service';
import { ServiceLocator } from 'src/app/zynerator/service/ServiceLocator';
import { ConfirmationService, MessageService } from 'primeng/api';
import { LocalisationDoctorService } from 'src/app/shared/service/doctor/localisation/LocalisationDoctor.service';
import { LocalisationDto } from 'src/app/shared/model/localisation/Localisation.model';
import { PatientDto } from 'src/app/shared/model/patient/Patient.model';
import { CapteurDto } from 'src/app/shared/model/sensor/Capteur.model';
import { CapteurDoctorService } from 'src/app/shared/service/doctor/sensor/CapteurDoctor.service';
import { PatientDoctorService } from 'src/app/shared/service/doctor/patient/PatientDoctor.service';
import { LocalisationCriteria } from "../../../../../../shared/criteria/localisation/LocalisationCriteria.model";
import {map} from "rxjs";

@Component({
    selector: 'app-localisation-view-doctor',
    templateUrl: './localisation-view-doctor.component.html',
    styleUrls: ['./localisation-view-doctor.component.scss']
})
export class LocalisationViewDoctorComponent implements OnInit, OnDestroy {
    protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;

    zoom: number = 15; // Initial zoom level
    center: google.maps.LatLngLiteral ;
    markerPosition: google.maps.LatLngLiteral; // Separate variable for marker position
    marker: google.maps.Marker; // Marker object to manage position updates
    intervalId: any; // Variable to store the interval ID for clearing it

    mapOptions: google.maps.MapOptions = {
        mapTypeControl: true,
        streetViewControl: true,
        fullscreenControl: true,
        zoomControl: true,
        styles: [
            {
                featureType: 'poi',
                elementType: 'labels',
                stylers: [{ visibility: 'off' }]
            }
        ]
    };

    markerOptions: google.maps.MarkerOptions = {
        animation: google.maps.Animation.DROP,
    };

    constructor(
        private service: LocalisationDoctorService,
        private capteurService: CapteurDoctorService,
        private patientService: PatientDoctorService
    ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);

    }

    ngOnInit(): void {
        this.center = { lat: this.item.latitude, lng: this.item.longitude };
        this.markerPosition = { lat: this.center.lat, lng: this.center.lng }; // Set initial marker position
        this.intervalId = setInterval(() => {
            this.refreshMarker();
        }, 5000); // 5 seconds interval
    }

    ngOnDestroy(): void {
        if (this.intervalId) {
            clearInterval(this.intervalId);
        }
    }

    refreshMarker(): void {
        // Fetch the latest localisation data from the service
        this.service.findById(this.item).subscribe((data: LocalisationDto) => {
            this.item = data;  // Update the item with the latest data
            const newPosition: google.maps.LatLngLiteral = { lat: this.item.latitude, lng: this.item.longitude };
            this.markerPosition = newPosition; // Update the marker position
        });
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

    get dateFormat() {
        return environment.dateFormatView;
    }

    get dateFormatColumn() {
        return environment.dateFormatList;
    }


    protected readonly map = map;
}
