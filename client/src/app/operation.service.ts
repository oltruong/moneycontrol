import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OperationModel} from "./models/operation.model";

@Injectable()
export class OperationService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/rest/operations';
    // this.url = './rest/operations';
  }


  list(params): Observable<Array<OperationModel>> {
    // const params = {};
    //
    // console.log("AAA " + year);
    // if (year != NaN) {
    //   params['year'] = year.toString();
    // }
    // if (month != NaN) {
    //   params['month'] = month.toString();
    // }
    // // const url = './rest/operations';


    // return of(this.json);
    return this.http.get<Array<OperationModel>>(this.url, {params});
    // return this.http.get<Array<OperationModel>>(url);
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
