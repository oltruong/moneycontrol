import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {RuleModel} from "./models/rule.model";

@Injectable()
export class RuleService {

  constructor(private http: HttpClient) {
  }

  list(): Observable<Array<RuleModel>> {


    const url = './rest/rules';
    // const url = 'http://localhost:8080/rest/rules';
    return this.http.get<Array<RuleModel>>(url);
  }
}
