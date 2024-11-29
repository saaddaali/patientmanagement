import {Component, OnInit} from '@angular/core';
import {LayoutService} from "../../layout/service/app.layout.service";
import {AppComponent} from "../../app.component";
import gsap from "gsap";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MenuItem} from "primeng/api";


@Component({
    selector: 'app-home',
    templateUrl: './search-public.component.html',
    styleUrls: ['./search-public.component.scss']
})
export class SearchPublicComponent implements OnInit {

    searchForm: FormGroup;
    date: Date = new Date();
    passengers: number = 1;
    items: MenuItem[];

    features = [
        {
            icon: 'pi pi-bolt',
            title: 'Réservez en un clic !',
            description: 'Réserver un trajet n\'a jamais été aussi facile ! Avec notre application intuitive et puissante, trouvez un trajet à proximité en quelques minutes.'
        },
        {
            icon: 'pi pi-shield',
            title: 'Voyagez l\'esprit tranquille',
            description: 'Nous prenons le temps de connaître chacun de nos membres et partenaires de transport. Consultez les avis, vérifiez les profils et réservez votre trajet en toute sécurité sur notre plateforme fiable.'
        },
        {
            icon: 'pi pi-euro',
            title: 'Votre choix de trajets à bas prix',
            description: 'Peu importe où vous allez, que ce soit en bus ou en covoiturage, trouvez le trajet parfait parmi une large gamme de destinations à des prix abordables.'
        }
    ];

    cities = [
        //maroc
        { name: 'Casablanca', code: 'CAS' },
        { name: 'Rabat', code: 'RAB' },
        { name: 'Fes', code: 'FES' },
        { name: 'Marrakech', code: 'MAR' },
        { name: 'Agadir', code: 'AGA' },
        { name: 'Tanger', code: 'TAN' },
        { name: 'Oujda', code: 'OUJ' },
        { name: 'Tetouan', code: 'TET' },
        { name: 'Meknes', code: 'MEK' },
        { name: 'Kenitra', code: 'KEN' },
        { name: 'Safi', code: 'SAF' },
        { name: 'El Jadida', code: 'ELJ' },
        { name: 'Nador', code: 'NAD' },
        { name: 'Beni Mellal', code: 'BEM' },
        { name: 'Khouribga', code: 'KHO' },
        { name: 'Taza', code: 'TAZ' },
        { name: 'Settat', code: 'SET' },
        { name: 'Berrechid', code: 'BER' },
        { name: 'Khemisset', code: 'KHE' },
        { name: 'Taourirt', code: 'TAO' },
        { name: 'Ouarzazate', code: 'OUA' },
        { name: 'Berkane', code: 'BER' },
        { name: 'Sidi Kacem', code: 'SIK' },
        { name: 'Taroudant', code: 'TAR' },
        { name: 'Oued Zem', code: 'OUZ' },
        { name: 'Sefrou', code: 'SEF' },
        { name: 'Essaouira', code: 'ESS' },
        { name: 'Figuig', code: 'FIG' },
        { name: 'Dakhla', code: 'DAK' },
        { name: 'Tiznit', code: 'TIZ' },
        { name: 'Guelmim', code: 'GUE' },
        { name: 'Larache', code: 'LAR' },
        { name: 'Tan-Tan', code: 'TAN' },
        { name: 'Tata', code: 'TAT' },
        { name: 'Tiznit', code: 'TIZ' },
        { name: 'Zagora', code: 'ZAG' },
    ];


    onSearch() {
        if (this.searchForm.valid) {
            console.log(this.searchForm.value);
        }
    }


    constructor(public layoutService: LayoutService, public app: AppComponent,private fb: FormBuilder ) {
        this.searchForm = this.fb.group({
            departure: [''],
            destination: [''],
            date: [new Date()],
            passengers: [1]
        });
    }

    onSubmit() {
        if (this.searchForm.valid) {
            console.log(this.searchForm.value);
        }
    }


    loading = true;
    bool: boolean = true;

    ngOnInit(): void {
        gsap.to(".spring", { rotation: 360, duration: 8, repeat: -1,ease: "linear" });
        gsap.fromTo(".spring", {x: 500 , y: 10}, {x: 0 , y:0, duration: 5, ease: "circ"});
        gsap.to(".dotnet", { rotation: 360, duration: 8, repeat: -1,ease: "linear" });
        gsap.fromTo(".dotnet", {x: -500 , y: 10}, {x: 0 , y:0, duration: 5, ease: "circ"});
        gsap.to(".react", { rotation: 360, duration: 8, repeat: -1,ease: "linear" });
        gsap.fromTo(".react", {x: 500 , y: 10}, {x: 0 , y:0, duration: 5, ease: "circ"});
        gsap.to(".angular", { rotation: 360, duration: 8, repeat: -1,ease: "linear" });
        gsap.fromTo(".angular", {x: -500 , y: 10}, {x: 0 , y:0, duration: 5, ease: "circ"});
        gsap.to(".title", { translate: 1, y: 50, duration: 3,ease: "linear" });


        const tl = gsap.timeline()
        if(this.bool){
            tl.fromTo(".body1", {opacity: 0}, {opacity: 1, duration: 3})
            this.bool = false
        }

        this.items = [
            {
                label: 'Bus',
                icon: 'pi pi-truck'
            },
            {
                label: 'Train',
                icon: 'pi pi-subway'
            },
            {
                label: 'Covoiturage',
                icon: 'pi pi-car'
            }
        ];


    }

}
