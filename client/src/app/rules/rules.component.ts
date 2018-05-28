import {Component, OnInit} from '@angular/core';
import {RuleModel} from "../models/rule.model";
import {RuleService} from "../rule.service";

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.sass']
})
export class RulesComponent implements OnInit {

  rules: Array<RuleModel> = [];

  constructor(private ruleService: RuleService) {
  }

  ngOnInit() {
    this.ruleService.list().subscribe(rules => this.rules = rules);
  }
}
