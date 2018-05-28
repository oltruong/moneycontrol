import {Component, OnInit} from '@angular/core';
import {OperationModel} from "../models/operation.model";
import {OperationService} from "../operation.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-operations',
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.sass']
})
export class OperationsComponent implements OnInit {

  operations: Array<OperationModel> = [];
  private year: Number;
  private month: Number;
  private category: String;

  constructor(private operationService: OperationService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route
      .queryParams
      .subscribe(params => {
        this.year = +params['year'];
        this.month = +params['month'];
        this.category = params['category'];
      });

    this.operationService.list().subscribe(operations => this.operations = operations);
  }
}
