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

import { LocalisationCreateAdminComponent } from './localisation/create/localisation-create-admin.component';
import { LocalisationEditAdminComponent } from './localisation/edit/localisation-edit-admin.component';
import { LocalisationViewAdminComponent } from './localisation/view/localisation-view-admin.component';
import { LocalisationListAdminComponent } from './localisation/list/localisation-list-admin.component';
import { SafeZoneCreateAdminComponent } from './safe-zone/create/safe-zone-create-admin.component';
import { SafeZoneEditAdminComponent } from './safe-zone/edit/safe-zone-edit-admin.component';
import { SafeZoneViewAdminComponent } from './safe-zone/view/safe-zone-view-admin.component';
import { SafeZoneListAdminComponent } from './safe-zone/list/safe-zone-list-admin.component';

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

    LocalisationCreateAdminComponent,
    LocalisationListAdminComponent,
    LocalisationViewAdminComponent,
    LocalisationEditAdminComponent,
    SafeZoneCreateAdminComponent,
    SafeZoneListAdminComponent,
    SafeZoneViewAdminComponent,
    SafeZoneEditAdminComponent,
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
  LocalisationCreateAdminComponent,
  LocalisationListAdminComponent,
  LocalisationViewAdminComponent,
  LocalisationEditAdminComponent,
  SafeZoneCreateAdminComponent,
  SafeZoneListAdminComponent,
  SafeZoneViewAdminComponent,
  SafeZoneEditAdminComponent,
  ],
})
export class LocalisationAdminModule { }
