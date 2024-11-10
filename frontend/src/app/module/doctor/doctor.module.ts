import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';

import { LoginDoctorComponent } from './login-doctor/login-doctor.component';
import { RegisterDoctorComponent } from './register-doctor/register-doctor.component';
import { ChangePasswordDoctorComponent } from './change-password-doctor/change-password-doctor.component';
import { ActivationDoctorComponent } from './activation-doctor/activation-doctor.component';
import { ForgetPasswordDoctorComponent } from './forget-password-doctor/forget-password-doctor.component';


import { StaffDoctorModule } from './view/staff/staff-doctor.module';
import { StaffDoctorRoutingModule } from './view/staff/staff-doctor-routing.module';
import { PatientDoctorModule } from './view/patient/patient-doctor.module';
import { PatientDoctorRoutingModule } from './view/patient/patient-doctor-routing.module';
import { LocalisationDoctorModule } from './view/localisation/localisation-doctor.module';
import { LocalisationDoctorRoutingModule } from './view/localisation/localisation-doctor-routing.module';
import { WarningDoctorModule } from './view/warning/warning-doctor.module';
import { WarningDoctorRoutingModule } from './view/warning/warning-doctor-routing.module';
import { HealthcareDoctorModule } from './view/healthcare/healthcare-doctor.module';
import { HealthcareDoctorRoutingModule } from './view/healthcare/healthcare-doctor-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginDoctorComponent,
   RegisterDoctorComponent,
   ChangePasswordDoctorComponent,
   ActivationDoctorComponent,
   ForgetPasswordDoctorComponent
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
  StaffDoctorModule,
  StaffDoctorRoutingModule,
  PatientDoctorModule,
  PatientDoctorRoutingModule,
  LocalisationDoctorModule,
  LocalisationDoctorRoutingModule,
  WarningDoctorModule,
  WarningDoctorRoutingModule,
  HealthcareDoctorModule,
  HealthcareDoctorRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [
    LoginDoctorComponent,
    RegisterDoctorComponent,
    ChangePasswordDoctorComponent,
    ActivationDoctorComponent,
    ForgetPasswordDoctorComponent,

    StaffDoctorModule,
    PatientDoctorModule,
    LocalisationDoctorModule,
    WarningDoctorModule,
    HealthcareDoctorModule,
    SecurityModule
  ],

})
export class DoctorModule { }
