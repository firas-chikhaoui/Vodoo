import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from "rxjs";
import { Profil } from '../../../../model/Profil';
import { Fonction } from '../../../../model/Fonction';

import { GestionProfileService } from '../gestion-profile.service';
import { GestionFonctionService } from '../gestion-fonction.service';
@Component({
  selector: 'ngx-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.scss']
})
export class CreateProfileComponent implements OnInit {

  
  profile: Profil = new Profil();
  fonctions: Observable<Fonction[]>;

  submitted = false;

  constructor(private profileService: GestionProfileService,
    private router: Router) {  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    debugger
    this.fonctions = this.profileService.getFonctionsList();
  }

  newProfile(): void {
    this.submitted = false;
    this.profile = new Profil();
  }

  save() {
    this.profileService.createProfile(this.profile)
      .subscribe(data => console.log(data), error => console.log(error));
    this.profile = new Profil();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/pages/profiles']);
  }

}
