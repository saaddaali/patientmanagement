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

import { InfirmierCreateAdminComponent } from './infirmier/create/infirmier-create-admin.component';
import { InfirmierEditAdminComponent } from './infirmier/edit/infirmier-edit-admin.component';
import { InfirmierViewAdminComponent } from './infirmier/view/infirmier-view-admin.component';
import { InfirmierListAdminComponent } from './infirmier/list/infirmier-list-admin.component';
import { SpecializationCreateAdminComponent } from './specialization/create/specialization-create-admin.component';
import { SpecializationEditAdminComponent } from './specialization/edit/specialization-edit-admin.component';
import { SpecializationViewAdminComponent } from './specialization/view/specialization-view-admin.component';
import { SpecializationListAdminComponent } from './specialization/list/specialization-list-admin.component';
import { DoctorCreateAdminComponent } from './doctor/create/doctor-create-admin.component';
import { DoctorEditAdminComponent } from './doctor/edit/doctor-edit-admin.component';
import { DoctorViewAdminComponent } from './doctor/view/doctor-view-admin.component';
import { DoctorListAdminComponent } from './doctor/list/doctor-list-admin.component';

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

    InfirmierCreateAdminComponent,
    InfirmierListAdminComponent,
    InfirmierViewAdminComponent,
    InfirmierEditAdminComponent,
    SpecializationCreateAdminComponent,
    SpecializationListAdminComponent,
    SpecializationViewAdminComponent,
    SpecializationEditAdminComponent,
    DoctorCreateAdminComponent,
    DoctorListAdminComponent,
    DoctorViewAdminComponent,
    DoctorEditAdminComponent,
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
  InfirmierCreateAdminComponent,
  InfirmierListAdminComponent,
  InfirmierViewAdminComponent,
  InfirmierEditAdminComponent,
  SpecializationCreateAdminComponent,
  SpecializationListAdminComponent,
  SpecializationViewAdminComponent,
  SpecializationEditAdminComponent,
  DoctorCreateAdminComponent,
  DoctorListAdminComponent,
  DoctorViewAdminComponent,
  DoctorEditAdminComponent,
  ],
})
export class StaffAdminModule { }
