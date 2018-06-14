import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {RuleModel} from "./models/rule.model";
import {environment} from '../environments/environment';

@Injectable()
export class RuleService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.backendUrl + '/rules';
  }

  list(): Observable<Array<RuleModel>> {
    return this.http.get<Array<RuleModel>>(this.url);
  }

  update(rule: RuleModel) {
    this.http.put(this.url + "/" + rule.id, rule).subscribe((response: HttpResponse<Object>) => {
      console.log("retour " + response);
    });
  }

  create(rule: RuleModel) {
    this.http.post(this.url, rule).subscribe((response: HttpResponse<RuleModel>) => {
      console.log("retour " + response);
    });
  }

  delete(rule: RuleModel) {
    this.http.delete(this.url + "/" + rule.id).subscribe((response: HttpResponse<Object>) => {
      console.log("retour " + response);
    });
  }
}
