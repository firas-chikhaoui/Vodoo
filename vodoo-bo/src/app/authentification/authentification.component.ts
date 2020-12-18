import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'ngx-authentification',
  templateUrl: './authentification.component.html',
  styleUrls: ['./authentification.component.scss'],
})
export class AuthentificationComponent implements OnInit {

  constructor(public translate: TranslateService) {
  }
   enable: boolean= false;
  ngOnInit() {

  }

  changetoogle() {
    if (this.enable === true) {
      localStorage.setItem('lang', 'ar');
      this.translate.use('ar');
    }else {
      localStorage.setItem('lang', 'fr');
      this.translate.use('fr');
    }
  }

}

