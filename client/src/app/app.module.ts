import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MenuComponent} from './menu/menu.component';
import {OperationsComponent} from './operations/operations.component';
import {OperationService} from "./operation.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    OperationsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [OperationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
