import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/config/security/guards/auth.guard';


@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
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
