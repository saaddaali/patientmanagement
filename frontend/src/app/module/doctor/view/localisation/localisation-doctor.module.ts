import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { LocalisationCreateDoctorComponent } from './localisation/create/localisation-create-doctor.component';
import { LocalisationEditDoctorComponent } from './localisation/edit/localisation-edit-doctor.component';
import { LocalisationViewDoctorComponent } from './localisation/view/localisation-view-doctor.component';
import { LocalisationListDoctorComponent } from './localisation/list/localisation-list-doctor.component';
import { SafeZoneCreateDoctorComponent } from './safe-zone/create/safe-zone-create-doctor.component';
import { SafeZoneEditDoctorComponent } from './safe-zone/edit/safe-zone-edit-doctor.component';
import { SafeZoneViewDoctorComponent } from './safe-zone/view/safe-zone-view-doctor.component';
import { SafeZoneListDoctorComponent } from './safe-zone/list/safe-zone-list-doctor.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';
import {GoogleMapsModule} from "@angular/google-maps";



@NgModule({
  declarations: [

    LocalisationCreateDoctorComponent,
    LocalisationListDoctorComponent,
    LocalisationViewDoctorComponent,
    LocalisationEditDoctorComponent,
    SafeZoneCreateDoctorComponent,
    SafeZoneListDoctorComponent,
    SafeZoneViewDoctorComponent,
    SafeZoneEditDoctorComponent,
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
        PaginatorModule,
        TranslateModule,
        FileUploadModule,
        FullCalendarModule,
        CardModule,
        EditorModule,
        TagModule,
        GoogleMapsModule,


    ],
  exports: [
  LocalisationCreateDoctorComponent,
  LocalisationListDoctorComponent,
  LocalisationViewDoctorComponent,
  LocalisationEditDoctorComponent,
  SafeZoneCreateDoctorComponent,
  SafeZoneListDoctorComponent,
  SafeZoneViewDoctorComponent,
  SafeZoneEditDoctorComponent,
  ],
})
export class LocalisationDoctorModule { }
