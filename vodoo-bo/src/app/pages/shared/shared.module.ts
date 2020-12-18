import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemeModule } from '../../@theme/theme.module';
import { NbMenuModule, NbCardModule, NbInputModule, NbButtonModule, NbRadioModule,
         NbCheckboxModule, NbSelectModule, NbToggleModule, NbTabsetModule,
         NbTooltipModule, NbSpinnerModule, NbBadgeModule, NbActionsModule, NbIconModule,
         NbAccordionModule,
         NbLayoutModule} from '@nebular/theme';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { CKEditorModule } from 'ng2-ckeditor';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ToastModule, DialogModule, TableModule, DataViewModule,
    DropdownModule, PanelModule, ButtonModule, StepsModule,
    ProgressBarModule } from 'primeng';
import { AuthentificationRoutingModule } from '../../authentification/authentification-routing.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ThemeModule,
    NbMenuModule,
    NbCardModule,
    FormsModule,
    NbInputModule,
    NbButtonModule,
    NbRadioModule,
    NbCheckboxModule,
    ReactiveFormsModule,
    NbSelectModule,
    NbToggleModule,
    // CKEditorModule,
    NbTabsetModule,
    NbTooltipModule,
    NbSpinnerModule,
    NbBadgeModule,
    NbActionsModule,
    NbIconModule,
    NbAccordionModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
    ToastModule,
    AuthentificationRoutingModule,
    NbLayoutModule,
    DialogModule,
    TableModule,
    DataViewModule,
    DropdownModule,
    PanelModule,
    ButtonModule,
  ],
  exports: [
    CommonModule,
    ThemeModule,
    NbMenuModule,
    NbCardModule,
    FormsModule,
    NbInputModule,
    NbButtonModule,
    NbRadioModule,
    NbCheckboxModule,
    ReactiveFormsModule,
    NbSelectModule,
    NbToggleModule,
    // CKEditorModule,
    NbTabsetModule,
    NbTooltipModule,
    NbSpinnerModule,
    NbBadgeModule,
    NbActionsModule,
    NbIconModule,
    NbAccordionModule,
    HttpClientModule,
    TranslateModule,
    NbLayoutModule,
    DialogModule,
    TableModule,
    DataViewModule,
    DropdownModule,
    PanelModule,
    ButtonModule,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
})
export class SharedModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
