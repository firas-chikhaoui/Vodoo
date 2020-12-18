import { Component, OnInit } from '@angular/core';
import { Profil } from '../../../../model/Profil';
import { ActivatedRoute, Router } from '@angular/router';
import { GestionProfileService } from '../gestion-profile.service';
@Component({
  selector: 'ngx-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.scss']
})
export class UpdateProfileComponent implements OnInit {

  id: string;
  profile: Profil;

  constructor(private route: ActivatedRoute,private router: Router,
    private profileService: GestionProfileService) { }

  ngOnInit() {
    this.profile = new Profil();

    this.id = this.route.snapshot.params['id'];
    
    this.profileService.getProfile(this.id)
      .subscribe(data => {
        console.log(data)
        this.profile = data;
      }, error => console.log(error));
  }

  updateProfile() {
    this.profileService.updateProfile(this.id, this.profile)
      .subscribe(data => console.log(data), error => console.log(error));
    this.profile = new Profil();
    this.gotoList();
  }

  onSubmit() {
    this.updateProfile();    
  }

  gotoList() {
    this.router.navigate(['/pages/profiles']);
  }

}
