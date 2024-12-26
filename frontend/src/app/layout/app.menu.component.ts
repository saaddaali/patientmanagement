import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
  modelAdmin: any[];
  modelDoctor: any[];
  modelInfirmier: any[];
constructor(public layoutService: LayoutService, public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Localisation Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste localisation',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/localisation/localisation/list']
								  },
						]
					  },
					  {
						label: 'Doctor Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste specialization',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/staff/specialization/list']
								  },
								  {
									label: 'Liste doctor',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/staff/doctor/list']
								  },
						]
					  },
					  {
						label: 'Sensor Type Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste capteur type',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/sensor/capteur-type/list']
								  },
						]
					  },
					  {
						label: 'Sensor Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste capteur',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/sensor/capteur/list']
								  },
						]
					  },
					  {
						label: 'Infirmier-Patient Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste infirmier patient',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/healthcare/infirmier-patient/list']
								  },
						]
					  },
					  {
						label: 'Warning Type Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste warning type',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/warning/warning-type/list']
								  },
						]
					  },
					  {
						label: 'Patient Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste patient',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/patient/patient/list']
								  },
						]
					  },
					  {
						label: 'Infirmier Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste infirmier',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/staff/infirmier/list']
								  },
						]
					  },
					  {
						label: 'Safety Zone Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste safe zone',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/localisation/safe-zone/list']
								  },
						]
					  },
					  {
						label: 'Gender Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste gender',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/patient/gender/list']
								  },
						]
					  },
					  {
						label: 'Warning Patient Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste warning patient',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/warning/warning-patient/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];
      this.modelDoctor = [
          {
              label: 'DASHBOARD',
              icon: 'pi pi-home',
              styleClass: 'dashboard-title',
              expanded: true,
          },
          {
              label: 'Patient Care',
              icon: 'pi pi-heart',
              items: [
                  {
                      label: 'Patients',
                      icon: 'pi pi-users',
                      routerLink: ['/app/doctor/patient/patient/list']
                  },
                  {
                      label: 'Nurse Assignments',
                      icon: 'pi pi-user-plus',
                      routerLink: ['/app/doctor/healthcare/infirmier-patient/list']
                  },
                  {
                      label: 'Warning Cases',
                      icon: 'pi pi-exclamation-triangle',
                      routerLink: ['/app/doctor/warning/warning-patient/list']
                  }
              ]
          },
          {
              label: 'Staff',
              icon: 'pi pi-briefcase',
              items: [
                  {
                      label: 'Nurses',
                      icon: 'pi pi-user',
                      routerLink: ['/app/doctor/staff/infirmier/list']
                  }
              ]
          },
          {
              label: 'Facility',
              icon: 'pi pi-building',
              items: [
                  {
                      label: 'Locations',
                      icon: 'pi pi-map-marker',
                      routerLink: ['/app/doctor/localisation/localisation/list']
                  },
                  {
                      label: 'Safety Zones',
                      icon: 'pi pi-shield',
                      routerLink: ['/app/doctor/localisation/safe-zone/list']
                  }
              ]
          },
      ];
    this.modelInfirmier =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Localisation Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste localisation',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/infirmier/localisation/localisation/list']
								  },
						]
					  },
					  {
						label: 'Patient Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste patient',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/infirmier/patient/patient/list']
								  },
						]
					  },
					  {
						label: 'Safety Zone Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste safe zone',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/infirmier/localisation/safe-zone/list']
								  },
						]
					  },
					  {
						label: 'Warning Patient Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste warning patient',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/infirmier/warning/warning-patient/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }
}
