import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 

import { PagesComponent } from './pages.component';
import { ECommerceComponent } from './e-commerce/e-commerce.component';
import { NotFoundComponent } from './miscellaneous/not-found/not-found.component';
import { ProfileListComponent } from './administration/gestion-profile/profile-list/profile-list.component';
import { CreateProfileComponent } from './administration/gestion-profile/create-profile/create-profile.component';
import { UpdateProfileComponent } from './administration/gestion-profile/update-profile/update-profile.component';


const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [
    {
      path: 'dashboard1',
      component: ECommerceComponent,
    },
    {
      path: 'profiles',
      component: ProfileListComponent,
    },
    {
      path: 'profiles/add',
      component: CreateProfileComponent,
    },
    { 
      path: 'profiles/update/:id', 
      component: UpdateProfileComponent },
    {
      path: 'miscellaneous',
      loadChildren: () => import('./miscellaneous/miscellaneous.module')
        .then(m => m.MiscellaneousModule),
    },
  
    {
      path: '**',
      component: NotFoundComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes),FormsModule ],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
