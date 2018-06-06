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
  private year: number;
  private month: number;
  private category: string;

  private query: string;

  constructor(private operationService: OperationService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route
      .queryParams
      .subscribe(params => {

        const paramsSent = {};
        console.log("UUUU " + params);
        console.log("UUUU " + params['year']);
        if (params['year'] != undefined) {
          paramsSent['year'] = params['year']
        }
        if (params['month'] != undefined) {
          paramsSent['month'] = params['month']
        }

        if (params['category'] != undefined) {
          paramsSent['category'] = params['category']
        }

        this.year = +params['year'];
        this.month = +params['month'];
        this.category = params['category'];
        this.operationService.list(paramsSent).subscribe(operations => this.operations = operations);

      });
    this.query = "";

  }

  getOperations(): Array<OperationModel> {
    return this.filterOperations(this.operations);
  }

  totalOperation(): number {
    return this.getOperations().length;
  }

  sumTotal(): number {
    let total = 0;
    this.operations.forEach(operation => total += operation.amount);
    return total;
  }

  sumFiltered(): number {
    let total = 0;
    this.getOperations().forEach(operation => total += operation.amount);
    return total;
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
    if (operation.name != event) {
      operation.name = event;
      this.operationService.update(operation);
    }
  }

  updateCategory(event: string, operation: OperationModel) {
    if (operation.category != event) {
      operation.category = event;
      this.operationService.update(operation);
    }
  }

  updateSubcategory(event: string, operation: OperationModel) {
    if (operation.subcategory != event) {
      operation.subcategory = event;
      this.operationService.update(operation);
    }
  }

  updateRecipient(event: string, operation: OperationModel) {
    if (operation.recipient != event) {
      operation.recipient = event;
      this.operationService.update(operation);
    }
  }

  updateComment(event: string, operation: OperationModel) {
    if (operation.comment != event) {

      operation.comment = event;
      this.operationService.update(operation);
    }
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
