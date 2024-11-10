import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { ActivationInfirmierComponent } from './activation-infirmier/activation-infirmier.component';
import { LoginInfirmierComponent } from './login-infirmier/login-infirmier.component';
import { RegisterInfirmierComponent } from './register-infirmier/register-infirmier.component';
import { ForgetPasswordInfirmierComponent } from './forget-password-infirmier/forget-password-infirmier.component';
import { ChangePasswordInfirmierComponent } from './change-password-infirmier/change-password-infirmier.component';

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
                                    component: LoginInfirmierComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterInfirmierComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordInfirmierComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordInfirmierComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationInfirmierComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'patient',
                            loadChildren: () => import('./view/patient/patient-infirmier-routing.module').then(x => x.PatientInfirmierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'localisation',
                            loadChildren: () => import('./view/localisation/localisation-infirmier-routing.module').then(x => x.LocalisationInfirmierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'warning',
                            loadChildren: () => import('./view/warning/warning-infirmier-routing.module').then(x => x.WarningInfirmierRoutingModule),
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
export class InfirmierRoutingModule { }
