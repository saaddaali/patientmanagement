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

import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { ChangePasswordAdminComponent } from './change-password-admin/change-password-admin.component';
import { ActivationAdminComponent } from './activation-admin/activation-admin.component';
import { ForgetPasswordAdminComponent } from './forget-password-admin/forget-password-admin.component';


import { StaffAdminModule } from './view/staff/staff-admin.module';
import { StaffAdminRoutingModule } from './view/staff/staff-admin-routing.module';
import { PatientAdminModule } from './view/patient/patient-admin.module';
import { PatientAdminRoutingModule } from './view/patient/patient-admin-routing.module';
import { LocalisationAdminModule } from './view/localisation/localisation-admin.module';
import { LocalisationAdminRoutingModule } from './view/localisation/localisation-admin-routing.module';
import { SensorAdminModule } from './view/sensor/sensor-admin.module';
import { SensorAdminRoutingModule } from './view/sensor/sensor-admin-routing.module';
import { WarningAdminModule } from './view/warning/warning-admin.module';
import { WarningAdminRoutingModule } from './view/warning/warning-admin-routing.module';
import { HealthcareAdminModule } from './view/healthcare/healthcare-admin.module';
import { HealthcareAdminRoutingModule } from './view/healthcare/healthcare-admin-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginAdminComponent,
   RegisterAdminComponent,
   ChangePasswordAdminComponent,
   ActivationAdminComponent,
   ForgetPasswordAdminComponent
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
  StaffAdminModule,
  StaffAdminRoutingModule,
  PatientAdminModule,
  PatientAdminRoutingModule,
  LocalisationAdminModule,
  LocalisationAdminRoutingModule,
  SensorAdminModule,
  SensorAdminRoutingModule,
  WarningAdminModule,
  WarningAdminRoutingModule,
  HealthcareAdminModule,
  HealthcareAdminRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [
    LoginAdminComponent,
    RegisterAdminComponent,
    ChangePasswordAdminComponent,
    ActivationAdminComponent,
    ForgetPasswordAdminComponent,

    StaffAdminModule,
    PatientAdminModule,
    LocalisationAdminModule,
    SensorAdminModule,
    WarningAdminModule,
    HealthcareAdminModule,
    SecurityModule
  ],

})
export class AdminModule { }
