import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OperationModel} from "./models/operation.model";

@Injectable()
export class OperationService {

  constructor(private http: HttpClient) {
  }

  list(): Observable<Array<OperationModel>> {
    const params = {month: '5', year: '2017'};
//    params["robert"]="dd";
//     return this.http.get<Array<OperationModel>>('./rest/operations');
    return this.http.get<Array<OperationModel>>('http://localhost:8080/rest/operations', {params});
  }
}
