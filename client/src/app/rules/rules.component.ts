import {Component, OnInit} from '@angular/core';
import {RuleModel} from "../models/rule.model";
import {RuleService} from "../rule.service";
import {OperationService} from "../operation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.sass']
})
export class RulesComponent implements OnInit {

  rules: Array<RuleModel> = [];

  private orderProp: string = 'nameCondition';
   query: string;

  constructor(private ruleService: RuleService, private operationService:OperationService) {
  }

  ngOnInit() {
    this.ruleService.list().subscribe(rules => this.rules = rules);
  }

  getRules(): RuleModel[] {
    return this.sortRules(this.filterRules(this.rules));
  }

  private sortRules(rules: RuleModel[]) {
    if (rules && this.orderProp) {
      return rules
        .slice(0) // Make a copy
        .sort((a, b) => {
          if (a[this.orderProp] < b[this.orderProp]) {
            return -1;
          } else if ([b[this.orderProp] < a[this.orderProp]]) {
            return 1;
          } else {
            return 0;
          }
        });
    }
    return rules;
  }

  private filterRules(rules: RuleModel[]) {
    if (rules && this.query && this.query.length > 2) {
      const query = this.query.toLowerCase();
      return rules.filter(rule => {
        let name = rule.nameCondition.toLowerCase();
        let category = rule.category ? rule.category.toLowerCase() : "";
        let subcategory = rule.subcategory ? rule.subcategory.toLowerCase() : "";
        let amount = rule.amountCondition ? rule.amountCondition.toString() : "";

        return name.indexOf(query) >= 0 || category.indexOf(query) >= 0 || subcategory.indexOf(query) >= 0
          || amount.toString().indexOf(query) >= 0;
      });
    }
    return rules;
  }


  updateNameCondition(event: string, rule: RuleModel) {
    if (rule.nameCondition != event) {
      rule.nameCondition = event;
      this.ruleService.update(rule);
    }
  }

  updateAmount(event: string, rule: RuleModel) {
    if (rule.amountCondition.toString() != event) {
      rule.amountCondition = +event;
      this.ruleService.update(rule);
    }
  }

  updateCategory(event: string, rule: RuleModel) {
    if (rule.category != event) {
      rule.category = event;
      this.ruleService.update(rule);
    }
  }

  updateSubcategory(event: string, rule: RuleModel) {
    if (rule.subcategory != event) {
      rule.subcategory = event;
      this.ruleService.update(rule);
    }
  }

  updateRecipient(event: string, rule: RuleModel) {
    if (rule.recipient != event) {
      rule.recipient = event;
      this.ruleService.update(rule);
    }
  }

  updateComment(event: string, rule: RuleModel) {
    if (rule.comment != event) {
      rule.comment = event;
      this.ruleService.update(rule);
    }
  }

  duplicate(rule: RuleModel) {
    rule.id = undefined;
    this.ruleService.create(rule);
    this.ruleService.list().subscribe(rules => this.rules = rules);
  }

  delete(rule: RuleModel) {
    this.ruleService.delete(rule);
    this.rules = this.rules.filter(obj => obj !== rule);
  }

  refresh(){
    this.operationService.refresh();
  }

}
