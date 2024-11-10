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

import { LocalisationCreateInfirmierComponent } from './localisation/create/localisation-create-infirmier.component';
import { LocalisationEditInfirmierComponent } from './localisation/edit/localisation-edit-infirmier.component';
import { LocalisationViewInfirmierComponent } from './localisation/view/localisation-view-infirmier.component';
import { LocalisationListInfirmierComponent } from './localisation/list/localisation-list-infirmier.component';
import { SafeZoneCreateInfirmierComponent } from './safe-zone/create/safe-zone-create-infirmier.component';
import { SafeZoneEditInfirmierComponent } from './safe-zone/edit/safe-zone-edit-infirmier.component';
import { SafeZoneViewInfirmierComponent } from './safe-zone/view/safe-zone-view-infirmier.component';
import { SafeZoneListInfirmierComponent } from './safe-zone/list/safe-zone-list-infirmier.component';

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

    LocalisationCreateInfirmierComponent,
    LocalisationListInfirmierComponent,
    LocalisationViewInfirmierComponent,
    LocalisationEditInfirmierComponent,
    SafeZoneCreateInfirmierComponent,
    SafeZoneListInfirmierComponent,
    SafeZoneViewInfirmierComponent,
    SafeZoneEditInfirmierComponent,
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
  LocalisationCreateInfirmierComponent,
  LocalisationListInfirmierComponent,
  LocalisationViewInfirmierComponent,
  LocalisationEditInfirmierComponent,
  SafeZoneCreateInfirmierComponent,
  SafeZoneListInfirmierComponent,
  SafeZoneViewInfirmierComponent,
  SafeZoneEditInfirmierComponent,
  ],
})
export class LocalisationInfirmierModule { }
