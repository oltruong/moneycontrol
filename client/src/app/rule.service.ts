import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {RuleModel} from "./models/rule.model";

@Injectable()
export class RuleService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/rest/rules';
  }

  list(): Observable<Array<RuleModel>> {
    return this.http.get<Array<RuleModel>>(this.url);
  }

  update(rule: RuleModel) {
    this.http.put(this.url + "/" + rule.id, rule);
  }
}
