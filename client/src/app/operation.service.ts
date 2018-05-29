import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OperationModel} from "./models/operation.model";

@Injectable()
export class OperationService {

  constructor(private http: HttpClient) {
  }

  list(): Observable<Array<OperationModel>> {
    const params = {year: '2017'};
    // const params = {month: '5', year: '2017'};
    const url = './rest/operations';
    // const url = 'http://localhost:8080/rest/operations';
    // return this.http.get<Array<OperationModel>>(url, {params});
    return this.http.get<Array<OperationModel>>(url);
  }
}
