import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { Observable } from "rxjs";
import { Profil } from '../../../../model/Profil';
import { Fonction } from '../../../../model/Fonction';

import { GestionProfileService } from '../gestion-profile.service';
import { GestionFonctionService } from '../gestion-fonction.service';

@Component({
  selector: 'ngx-profile-list',
  templateUrl: './profile-list.component.html',
  styleUrls: ['./profile-list.component.scss']
})
export class ProfileListComponent implements OnInit {

  profiles: Observable<Profil[]>;
  fonctions: Observable<Fonction[]>;


  constructor(private profileService: GestionProfileService,
    private router: Router) {}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.profiles = this.profileService.getProfilesList();
  }

  

  deleteProfile(id: number) {
    debugger
    this.profileService.deleteProfile(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
  profileDetails(id: number){
    debugger
    this.router.navigate(['/pages/details', id]);
  }

  updateProfile(id: number){
    debugger
    this.router.navigate(['ffff', id]);
  }

}
