import {TestBed} from '@angular/core/testing';
import {RuleService} from "./rule.service";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {RuleModel} from "./models/rule.model";

describe('RuleService', () => {

  let ruleService: RuleService;
  let http: HttpTestingController;

  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [RuleService]
  }));


  beforeEach(() => {
    ruleService = TestBed.get(RuleService);
    http = TestBed.get(HttpTestingController);
  });

  it('should return an Observable of 3 rules', () => {
    const hardcodedRules = [{name: 'Paris'}, {name: 'Tokyo'}, {name: 'Lyon'}];

    let actualRules = [];
    ruleService.list().subscribe((operations: Array<RuleModel>) => actualRules = operations);

    http.expectOne('./rest/rules')
      .flush(hardcodedRules);

    expect(actualRules).toEqual(hardcodedRules, 'The `list` method should return an array of RuleModel wrapped in an Observable');
  });
});
