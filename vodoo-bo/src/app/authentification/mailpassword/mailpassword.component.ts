import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router } from '../../../../node_modules/@angular/router';
import {
  NbComponentStatus,

  NbToastrService,
} from '@nebular/theme';
import { TranslateService } from '@ngx-translate/core';



@Component({
  selector: 'ngx-mailpassword',
  templateUrl: './mailpassword.component.html',
  styleUrls: ['./mailpassword.component.scss'],



})
export class MailpasswordComponent implements OnInit {


  sendmailform: FormGroup;
  isSubmitted: boolean = false;
  isShow: boolean = false;
  isExist: boolean = false;
  isChecked: boolean = false;
  userId: string = '';
  title = 'Envoie!';
  content = `Prière de rénitialiser votre mot de passe !`;

  titledang = 'Echoué!';

  constructor(private _formBuilder: FormBuilder,
    private router: Router, private toastrService: NbToastrService,
    public translate: TranslateService,
    ) { }

  ngOnInit() {
    this.userId = localStorage.getItem('UserId');
    this.sendmailform  = this._formBuilder.group({
      email: ['', [Validators.required,
        Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+.[a-zA-Z]+.[a-zA-Z]{2,3}$')]],

      },
    );
  }

  get formControls() { return this.sendmailform.controls; }
  onConfirm() {
    this.isSubmitted = true;
    if (this.sendmailform.invalid) {
      return;
    }else {
      // this._UserService.sendmail(this.sendmailform.value.email).subscribe(res => {
      //     if (res.result == 1){
      //       this.makeToast();
      //       this.router.navigateByUrl("/auth/login");
      //     }
      //       else if (res.result == -1){
      //         this._NotificationService.showToast("danger",this.translate.instant("Erreur"),
      //     this.translate.instant("Veuillez choisir un autre mail inexistant"));
      //     }else if (res.result == -9){
      //       this.showToast(this.statusdang,
      //  this.translate.instant(this.titledang), this.translate.instant(res.errorDescription));
      //     }

      //   });

  }
}

showSuccess() {
  // this.msgs = [];
  // this.msgs.push({severity:'success', summary:'Success Message', detail:'Mot de passe'});
}

}





