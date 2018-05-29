import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

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
import { CellEditComponent } from './cell-edit/cell-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    OperationsComponent,
    HomeComponent,
    RulesComponent,
    CellEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [OperationService, RuleService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
