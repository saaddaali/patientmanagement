import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { ActivationDoctorComponent } from './activation-doctor/activation-doctor.component';
import { LoginDoctorComponent } from './login-doctor/login-doctor.component';
import { RegisterDoctorComponent } from './register-doctor/register-doctor.component';
import { ForgetPasswordDoctorComponent } from './forget-password-doctor/forget-password-doctor.component';
import { ChangePasswordDoctorComponent } from './change-password-doctor/change-password-doctor.component';

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
                                    component: LoginDoctorComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterDoctorComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordDoctorComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordDoctorComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationDoctorComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'staff',
                            loadChildren: () => import('./view/staff/staff-doctor-routing.module').then(x => x.StaffDoctorRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'patient',
                            loadChildren: () => import('./view/patient/patient-doctor-routing.module').then(x => x.PatientDoctorRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'localisation',
                            loadChildren: () => import('./view/localisation/localisation-doctor-routing.module').then(x => x.LocalisationDoctorRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'warning',
                            loadChildren: () => import('./view/warning/warning-doctor-routing.module').then(x => x.WarningDoctorRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'healthcare',
                            loadChildren: () => import('./view/healthcare/healthcare-doctor-routing.module').then(x => x.HealthcareDoctorRoutingModule),
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
export class DoctorRoutingModule { }
