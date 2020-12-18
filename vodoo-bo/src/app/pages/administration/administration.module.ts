import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdministrationComponent } from './administration.component';
import { AuthentificationRoutingModule } from '../../authentification/authentification-routing.module';

import { CreateProfileComponent } from './gestion-profile/create-profile/create-profile.component';
import { UpdateProfileComponent } from './gestion-profile/update-profile/update-profile.component';

import { ProfileListComponent } from './gestion-profile/profile-list/profile-list.component';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from '../shared/shared.module';
import { GestionFonctionComponent } from './gestion-fonction/gestion-fonction.component';
import { FonctionListComponent } from './gestion-fonction/fonction-list/fonction-list.component';



@NgModule({
  declarations: [
    AdministrationComponent, 
    CreateProfileComponent, 
    UpdateProfileComponent, 
    ProfileListComponent,
    GestionFonctionComponent, 
    FonctionListComponent],
    
  imports: [
    CommonModule,
    SharedModule,
    AuthentificationRoutingModule,
    HttpClientModule
  ],
  
})
export class AdministrationModule { }
