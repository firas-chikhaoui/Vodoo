import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '../../../../node_modules/@angular/forms';
import { Router } from '../../../../node_modules/@angular/router';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
@Component({
  selector: 'ngx-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.scss'],
})
export class ForgotpasswordComponent implements OnInit {

  resetPasswordform: FormGroup;
  isSubmitted: boolean = false;
  public barLabel: string = 'Fiabilité du mot de passe:';
  public myColors = ['#DD2C00', '#FF6D00', '#FFD600', '#AEEA00', '#00C853'];
  public baseColor = '#FFF';
  public strength = 0;
  isShow: boolean = false;
  isChecked: boolean = false;
  userId: string = '';
  isPassword: boolean = false;

  constructor(private _formBuilder: FormBuilder,
    private router: Router, public translate: TranslateService) { }

  ngOnInit() {
    this.userId = localStorage.getItem('UserId');
    this.resetPasswordform  = this._formBuilder.group({
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
      },
    );
  }

  strengthChanged(strength: number) {
    this.strength = strength;
  }

  get formControls() { return this.resetPasswordform.controls; }



  onUpdate() {
    this.isSubmitted = true;
    if (this.resetPasswordform.invalid) {
      return;
    }else {
      // this._UserService.editPassword().subscribe(res => {
      //     if (res.result == 1){
      //       this.router.navigateByUrl("/auth/login");
      //     } else { this.notifStatus = 'warning';
      //     this._NotificationService.showToast(this.notifStatus,this.translate.instant('Alerte!'),
      //     this.translate.instant('Token'))}
      //   });
    }
  }

  onShowPS() {
    this.isShow = true;
  }

}

