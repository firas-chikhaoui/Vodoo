import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthentificationComponent } from './authentification.component';
import { LoginComponent } from './login/login.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { MailpasswordComponent } from './mailpassword/mailpassword.component';
import { SharedModule } from '../pages/shared/shared.module';
import { Ng9PasswordStrengthBarModule } from 'ng9-password-strength-bar';
import { AuthentificationRoutingModule } from './authentification-routing.module';


@NgModule({
  declarations: [
    AuthentificationComponent,
    LoginComponent,
    ForgotpasswordComponent,
    ResetPasswordComponent,
    MailpasswordComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    Ng9PasswordStrengthBarModule,
    AuthentificationRoutingModule,
  ],
})
export class AuthentificationModule { }
