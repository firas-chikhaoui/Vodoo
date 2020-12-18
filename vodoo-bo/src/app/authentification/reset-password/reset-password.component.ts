import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'ngx-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss'],
})
export class ResetPasswordComponent implements OnInit {

  resetPasswordform: FormGroup;
  isSubmitted: boolean = false;
  public barLabel: string = 'Fiabilité du mot de passe:';
  public myColors = ['#DD2C00', '#FF6D00', '#FFD600', '#AEEA00', '#00C853'];
  public baseColor = '#FFF';
  public strengthLabels = ['(Inutile)', '(Faible)', '(Normal)', '(Fort)', '(Génial!)'];
  public strength = 0;
  isShow: boolean = false;
  isChecked: boolean = false;
  userId: string = '';
  mdpError: boolean = false;
  lang: string;
  constructor(private _formBuilder: FormBuilder,
    private router: Router) { }

  ngOnInit() {
    this.userId = localStorage.getItem('UserId');
    this.resetPasswordform  = this._formBuilder.group({
        oldPassword: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
      },
    );
  }

  strengthChanged(strength: number) {
    this.strength = strength;
  }

  get formControls() { return this.resetPasswordform.controls; }

  onConfirm() {
    this.isSubmitted = true;
    if (this.resetPasswordform.invalid) {
      return;
    }else {
      // this._UserService.checkUserPassword(localStorage.getItem('UserId'),
      // this.resetPasswordform.value.oldPassword).subscribe(res => {
      //   if (res.userResponse == true){
      //     this.isChecked = false;
      //     console.log('password match');
      //     this._UserService.getUserById(this.userId).subscribe(res =>{
      //       if (res.result == 1){
      //         this.user = res.userResponse;
      //         this.user.password = this.resetPasswordform.value.password;
      //         this.user.provisionalpwd = 0;
      //         if (this.resetPasswordform.value.password == this.resetPasswordform.value.oldPassword){
      //           this.mdpError = true;
      //           console.log('Old password and new password match');
      //         }else{
      //           this.mdpError = false;
      //           // this.userDto.idUser = this.user.idUser;
      //           // this.userDto.password = this.user.password;
      //           // this.userDto.provisionalpwd = this.user.provisionalpwd;
      //           // this.userDto.typUserId = this.user.typUser.idTypUser;
      //           console.log(this.user);
      //           this._UserService.updateUser(this.user,this.userId).subscribe(res => {
      //             if (res.result == 1){
      //               localStorage.setItem("provisionalPwd",this.user.provisionalpwd.toString());
      //               console.log('password updated');
      //               if(this.user.typUser.codeTypUser === 'USER_EL_BACKOFFICE'){
      //                 return  this.router.navigateByUrl("/pages/dashboard");
      //               } else if(this.user.typUser.codeTypUser === 'USER_EL_PARTNER') {
      //                 this._PartnerEndPointService.getListPartnerByUser(this.user.idUser).subscribe(val=>{
      //                   this.partners=val.partnerResponse;
      //                   if (this.partners.length > 1){
      //                     console.log(val);
      //                     this.router.navigateByUrl("/auth/partnerUser");
      //                   } else{
      //                     localStorage.setItem('partnerid',this.partners[0].idPartner);
      //                     console.log(localStorage.getItem('partnerid'));
      //                     this.router.navigateByUrl("/pages/dashboard");
      //                   }
      //                 })
      //               }else{
      //                 this.router.navigateByUrl("/pages/dashboard");
      //               }
      //             }else{
      //               console.log("error");
      //               console.log(res.errorDescription);
      //             }
      //           })
      //         }
      //       }
      //     });
      //   }else if (res.userResponse == false){
      //     console.log('password doesnt match');
      //     this.isChecked = true;
      //   }
      // });
    }
  }
  onShowPS() {
    this.isShow = true;
  }

  onreset() {
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigateByUrl('/auth/login');
  }

}
