import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { ActivationAdminComponent } from './activation-admin/activation-admin.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { ForgetPasswordAdminComponent } from './forget-password-admin/forget-password-admin.component';
import { ChangePasswordAdminComponent } from './change-password-admin/change-password-admin.component';

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {
                            path: 'login',
                            children: [
                                {
                                    path: '',
                                    component: LoginAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'staff',
                            loadChildren: () => import('./view/staff/staff-admin-routing.module').then(x => x.StaffAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'patient',
                            loadChildren: () => import('./view/patient/patient-admin-routing.module').then(x => x.PatientAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'localisation',
                            loadChildren: () => import('./view/localisation/localisation-admin-routing.module').then(x => x.LocalisationAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'sensor',
                            loadChildren: () => import('./view/sensor/sensor-admin-routing.module').then(x => x.SensorAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'warning',
                            loadChildren: () => import('./view/warning/warning-admin-routing.module').then(x => x.WarningAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'healthcare',
                            loadChildren: () => import('./view/healthcare/healthcare-admin-routing.module').then(x => x.HealthcareAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        }
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class AdminRoutingModule { }
