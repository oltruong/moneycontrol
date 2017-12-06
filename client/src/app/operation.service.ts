import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {OperationModel} from "./models/operation.model";

@Injectable()
export class OperationService {

  constructor(private http: HttpClient) {
  }

  list(): Observable<Array<OperationModel>> {
    const params = {month: '08', year: '2016'};
//    params["robert"]="dd";
    return this.http.get<Array<OperationModel>>('./rest/operations');
  }
}
