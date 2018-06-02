import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {OperationService} from './operation.service';
import {OperationModel} from './models/operation.model';

describe('OperationService', () => {

  let operationService: OperationService;
  let http: HttpTestingController;

  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [OperationService]
  }));

  beforeEach(() => {
    operationService = TestBed.get(OperationService);
    http = TestBed.get(HttpTestingController);
  });

  it('should return an Observable of 3 operations', () => {
    // fake response
    const hardcodedOperations = [{name: 'Paris'}, {name: 'Tokyo'}, {name: 'Lyon'}];

    let actualOperations = [];
    operationService.list(null).subscribe((operations: Array<OperationModel>) => actualOperations = operations);

    http.expectOne('http://localhost:8080/rest/operations', null)
      .flush(hardcodedOperations);

    expect(actualOperations).toEqual(hardcodedOperations, 'The `list` method should return an array of OperationModel wrapped in an Observable');
  });

});
