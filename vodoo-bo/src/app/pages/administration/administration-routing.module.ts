import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AdministrationComponent } from './administration.component';
import { ProfileListComponent } from './gestion-profile/profile-list/profile-list.component';
import { CreateProfileComponent } from './gestion-profile/create-profile/create-profile.component';
import { UpdateProfileComponent } from './gestion-profile/update-profile/update-profile.component';
import { FormsModule } from '@angular/forms'; 

export const Adminroutes: Routes = [
  {
    path: '',
    component: AdministrationComponent,
  },
 
      { path: 'add', 
      component: CreateProfileComponent },
      { path: 'update', 
      component: UpdateProfileComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(Adminroutes),
    FormsModule],
  exports: [RouterModule]
})

export class AdministrationRoutingModule { }
