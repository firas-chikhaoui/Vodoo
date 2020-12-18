import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from "rxjs";
import { Fonction } from '../../../../model/Fonction';
import { GestionFonctionService } from '../../gestion-profile/gestion-fonction.service';

@Component({
  selector: 'ngx-fonction-list',
  templateUrl: './fonction-list.component.html',
  styleUrls: ['./fonction-list.component.scss']
})
export class FonctionListComponent implements OnInit {

  profiles: Observable<Fonction[]>;

  constructor(private profileService: GestionFonctionService,
    private router: Router) {}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.profiles = this.profileService.getFonctionsList();
  }

  

}
