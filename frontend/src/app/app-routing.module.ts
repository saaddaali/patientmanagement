import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';
import {AppLayoutComponent} from 'src/app/layout/app.layout.component';

import {HomePublicComponent} from 'src/app/public/home/home-public.component';

import {LoginAdminComponent} from 'src/app/module/admin/login-admin/login-admin.component';
import {RegisterAdminComponent} from 'src/app/module/admin/register-admin/register-admin.component';
import {ChangePasswordAdminComponent} from 'src/app/module/admin/change-password-admin/change-password-admin.component';
import {LoginDoctorComponent} from 'src/app/module/doctor/login-doctor/login-doctor.component';
import {RegisterDoctorComponent} from 'src/app/module/doctor/register-doctor/register-doctor.component';
import {ChangePasswordDoctorComponent} from 'src/app/module/doctor/change-password-doctor/change-password-doctor.component';

@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                {path: '', component: HomePublicComponent},
                {path: 'admin/login', component: LoginAdminComponent },
                {path: 'admin/register', component: RegisterAdminComponent },
                {path: 'admin/changePassword', component: ChangePasswordAdminComponent },
                {path: 'doctor/login', component: LoginDoctorComponent },
                {path: 'doctor/register', component: RegisterDoctorComponent },
                {path: 'doctor/changePassword', component: ChangePasswordDoctorComponent },
                {
                    path: 'app',
                    component: AppLayoutComponent,
                    children: [
                        {
                            path: 'admin',
                            loadChildren: () => import( './module/admin/admin-routing.module').then(x => x.AdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'doctor',
                            loadChildren: () => import( './module/doctor/doctor-routing.module').then(x => x.DoctorRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'infirmier',
                            loadChildren: () => import( './module/infirmier/infirmier-routing.module').then(x => x.InfirmierRoutingModule),
                            canActivate: [AuthGuard],
                        },
                    ],
                    canActivate: [AuthGuard]
                },
            ],
                { scrollPositionRestoration: 'enabled' }
            ),
        ],
    exports: [RouterModule],
    })
export class AppRoutingModule { }
