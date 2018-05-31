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
  private category: string;

  private query: string;

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
    this.query = "";

    this.operationService.list().subscribe(operations => this.operations = operations);
  }

  getOperations(): Array<OperationModel> {
    return this.filterOperations(this.operations);
  }

  totalOperation() {
    return this.getOperations().length;
  }

  private filterOperations(operations: OperationModel[]) {
    if (operations && this.query && this.query.length > 2) {

      const query = this.query.toLowerCase();
      return operations.filter(operation => {
        let name = operation.name.toLowerCase();
        let category = operation.category ? operation.category.toLowerCase() : "";
        let subcategory = operation.subcategory ? operation.subcategory.toLowerCase() : "";
        let amount = operation.amount;

        return name.indexOf(query) >= 0 || category.indexOf(query) >= 0 || subcategory.indexOf(query) >= 0
          || amount.toString().indexOf(query) >= 0;
      });
    }
    return operations;
  }

  updateName(event: string, operation: OperationModel) {
    operation.name = event;
    console.log("UUUPDATE " + event + " " + operation.name);
  }

  // private sortoperations(operations: OperationModel[]) {
  //   if (operations && this.orderProp) {
  //     return operations
  //       .slice(0) // Make a copy
  //       .sort((a, b) => {
  //         if (a[this.orderProp] < b[this.orderProp]) {
  //           return -1;
  //         } else if ([b[this.orderProp] < a[this.orderProp]]) {
  //           return 1;
  //         } else {
  //           return 0;
  //         }
  //       });
  //   }
  //   return operations;
  // }
}
