import {TestBed} from '@angular/core/testing';
import {of} from 'rxjs';
import {RouterTestingModule} from '@angular/router/testing';

import {AppModule} from '../app.module';
import {RulesComponent} from "./rules.component";
import {RuleService} from "../rule.service";


describe('RulesComponent', () => {
  const service = jasmine.createSpyObj('RuleService', ['list']);

  beforeEach(() => TestBed.configureTestingModule({
    imports: [AppModule, RouterTestingModule],
    providers: [{provide: RuleService, useValue: service}]
  }));

  it('should display every rule name in a title', () => {
    service.list.and.returnValue(of([
      {nameCondition: 'SuperMarket'},
      {nameCondition: 'Rent'},
      {nameCondition: 'Salary'},
      {nameCondition: 'Phone'},
      {nameCondition: 'Restaurant'}
    ]));

    const fixture = TestBed.createComponent(RulesComponent);
    fixture.detectChanges();

    expect(service.list).toHaveBeenCalled();

    expect(fixture.componentInstance.rules).not.toBeNull('You need to have a field `rules` initialized with 5 rules');
    expect(fixture.componentInstance.rules.length).toBe(5, 'You need to have a field `rules` initialized with 5 rules');
    expect(fixture.componentInstance.rules[0].nameCondition).toBe('SuperMarket');
    expect(fixture.componentInstance.rules[1].nameCondition).toBe('Rent');
    expect(fixture.componentInstance.rules[2].nameCondition).toBe('Salary');
    expect(fixture.componentInstance.rules[3].nameCondition).toBe('Phone');
    expect(fixture.componentInstance.rules[4].nameCondition).toBe('Restaurant');

  });
});
