import {TestBed} from '@angular/core/testing';
import {of} from 'rxjs';
import {RouterTestingModule} from '@angular/router/testing';

import {AppModule} from '../app.module';
import {OperationsComponent} from "./operations.component";
import {OperationService} from "../operation.service";


describe('OperationsComponent', () => {

  const service = jasmine.createSpyObj('OperationService', ['list']);

  beforeEach(() => TestBed.configureTestingModule({
    imports: [AppModule, RouterTestingModule],
    providers: [{provide: OperationService, useValue: service}]
  }));

  it('should display every operation name in a title', () => {
    service.list.and.returnValue(of([
      {name: 'SuperMarket'},
      {name: 'Rent'},
      {name: 'Salary'},
      {name: 'Phone'},
      {name: 'Restaurant'}
    ]));

    const fixture = TestBed.createComponent(OperationsComponent);
    fixture.detectChanges();

    expect(service.list).toHaveBeenCalled();

    expect(fixture.componentInstance.operations).not.toBeNull('You need to have a field `operations` initialized with 5 operations');
    expect(fixture.componentInstance.operations.length).toBe(5, 'You need to have a field `operations` initialized with 5 operations');
    expect(fixture.componentInstance.operations[0].name).toBe('SuperMarket');
    expect(fixture.componentInstance.operations[1].name).toBe('Rent');
    expect(fixture.componentInstance.operations[2].name).toBe('Salary');
    expect(fixture.componentInstance.operations[3].name).toBe('Phone');
    expect(fixture.componentInstance.operations[4].name).toBe('Restaurant');

  });
});
