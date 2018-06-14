import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OperationModel} from "./models/operation.model";
import {environment} from '../environments/environment';

@Injectable()
export class OperationService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.backendUrl + '/operations';
  }

  list(params): Observable<Array<OperationModel>> {
    return this.http.get<Array<OperationModel>>(this.url, {params});
  }

  update(operation: OperationModel) {
    this.http.put(this.url + "/" + operation.id, operation).subscribe((response: HttpResponse<Object>) => {
      console.log("retour " + response);
    });
  }

  refresh() {
    this.http.post(this.url + "/refresh", {}).subscribe((response: HttpResponse<Object>) => {
      console.log("retour " + response);
    });
  }
}
