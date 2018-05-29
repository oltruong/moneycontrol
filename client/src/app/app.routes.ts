import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {OperationsComponent} from "./operations/operations.component";
import {RulesComponent} from "./rules/rules.component";

export const ROUTES: Routes = [
  {path: '', component: HomeComponent},
  {path: 'operations', component: OperationsComponent},
  {path: 'rules', component: RulesComponent}
];