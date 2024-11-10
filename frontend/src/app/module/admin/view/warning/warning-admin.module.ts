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

import { WarningTypeCreateAdminComponent } from './warning-type/create/warning-type-create-admin.component';
import { WarningTypeEditAdminComponent } from './warning-type/edit/warning-type-edit-admin.component';
import { WarningTypeViewAdminComponent } from './warning-type/view/warning-type-view-admin.component';
import { WarningTypeListAdminComponent } from './warning-type/list/warning-type-list-admin.component';
import { WarningPatientCreateAdminComponent } from './warning-patient/create/warning-patient-create-admin.component';
import { WarningPatientEditAdminComponent } from './warning-patient/edit/warning-patient-edit-admin.component';
import { WarningPatientViewAdminComponent } from './warning-patient/view/warning-patient-view-admin.component';
import { WarningPatientListAdminComponent } from './warning-patient/list/warning-patient-list-admin.component';

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



@NgModule({
  declarations: [

    WarningTypeCreateAdminComponent,
    WarningTypeListAdminComponent,
    WarningTypeViewAdminComponent,
    WarningTypeEditAdminComponent,
    WarningPatientCreateAdminComponent,
    WarningPatientListAdminComponent,
    WarningPatientViewAdminComponent,
    WarningPatientEditAdminComponent,
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


  ],
  exports: [
  WarningTypeCreateAdminComponent,
  WarningTypeListAdminComponent,
  WarningTypeViewAdminComponent,
  WarningTypeEditAdminComponent,
  WarningPatientCreateAdminComponent,
  WarningPatientListAdminComponent,
  WarningPatientViewAdminComponent,
  WarningPatientEditAdminComponent,
  ],
})
export class WarningAdminModule { }
