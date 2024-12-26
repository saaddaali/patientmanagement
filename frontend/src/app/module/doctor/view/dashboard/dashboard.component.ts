import {Component, OnInit} from '@angular/core';
import gsap from 'gsap';
import {Subscription} from "rxjs";
import {MenuItem} from "primeng/api";
import {PatientDto} from "../../../../shared/model/patient/Patient.model";
import {PatientDoctorService} from "../../../../shared/service/doctor/patient/PatientDoctor.service";

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    protected cols: any[] = [];


    constructor(protected servicePatient: PatientDoctorService) {
    }


    ngOnInit() {
        this.loadItems();
        const send = document.getElementById('send');
        send.addEventListener("click", () => {
            gsap.to("#send-logo", {duration: 1.2, translateX: 65, width: 0})
        })

    }


    loadItems() {
        this.servicePatient.findAll().subscribe(data => {
            this.items = data;
        });

    }

    get items(): Array<PatientDto> {
        return this.servicePatient.items;
    }

    set items(value: Array<PatientDto>) {
        this.servicePatient.items = value;
    }
}
