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




import { PatientInfirmierModule } from './view/patient/patient-infirmier.module';
import { PatientInfirmierRoutingModule } from './view/patient/patient-infirmier-routing.module';
import { LocalisationInfirmierModule } from './view/localisation/localisation-infirmier.module';
import { LocalisationInfirmierRoutingModule } from './view/localisation/localisation-infirmier-routing.module';
import { WarningInfirmierModule } from './view/warning/warning-infirmier.module';
import { WarningInfirmierRoutingModule } from './view/warning/warning-infirmier-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
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
  PatientInfirmierModule,
  PatientInfirmierRoutingModule,
  LocalisationInfirmierModule,
  LocalisationInfirmierRoutingModule,
  WarningInfirmierModule,
  WarningInfirmierRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [

    PatientInfirmierModule,
    LocalisationInfirmierModule,
    WarningInfirmierModule,
    SecurityModule
  ],

})
export class InfirmierModule { }
