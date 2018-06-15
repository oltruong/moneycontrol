import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MenuComponent} from './menu/menu.component';
import {OperationsComponent} from './operations/operations.component';
import {OperationService} from "./operation.service";
import {HttpClientModule} from "@angular/common/http";
import {HomeComponent} from './home/home.component';
import {RouterModule} from '@angular/router';
import {ROUTES} from './app.routes';
import {RulesComponent} from './rules/rules.component';
import {RuleService} from "./rule.service";
import {CellEditComponent} from './cell-edit/cell-edit.component';
import {FormsModule} from "@angular/forms";
import { FileUploadComponent } from './file-upload/file-upload.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    OperationsComponent,
    HomeComponent,
    RulesComponent,
    CellEditComponent,
    FileUploadComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [OperationService, RuleService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
