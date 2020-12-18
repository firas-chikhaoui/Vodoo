import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {NbDialogService} from '@nebular/theme';
import {
  NbToastrService,
} from '@nebular/theme';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';


@Component({
  selector: 'ngx-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  displayDialog: boolean;
  index = 1;
  loginform: FormGroup;
  isSubmitted: Boolean = false;
  loginError: boolean = false;
  isExist: Boolean= true;
  lang: string;
  showloading: boolean= false;

  constructor(private _formBuilder: FormBuilder, private router: Router,
    private dialogService: NbDialogService,
    private toastrService: NbToastrService,
    public translate: TranslateService) {
  //   translate.onLangChange.subscribe((event: LangChangeEvent) => {
  // });
  }

  ngOnInit() {
    this.createform();
  }
  createform() {
    this.loginform = this._formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  get formControls() { return this.loginform.controls; }

  doLogin() {
    
    localStorage.clear();
    sessionStorage.clear();
  }

  onFirstLogin() {
    return this.router.navigateByUrl('/auth/resetPassword');
  }

  // private showToast(type:NbComponentStatus, title: string, body: string) {
  //   const config = {
  //     status: type,

  //   };
  //   const titleContent = title ? `${title}` : '';

  //   this.toastrService.show(
  //     body,
  //     `${titleContent}`,
  //     config);


  // }

  // makeToast(message) {
  //   this.showToast(this.status,this.translate.instant("Erreur"), this.translate.instant(message));
  // }

  Motpasseoublie() {
    return this.router.navigateByUrl('/auth/mailPassword');
  }

}
