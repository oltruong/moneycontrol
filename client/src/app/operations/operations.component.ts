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
  categories: Map<string, number> = new Map();
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
        this.operationService.list(paramsSent).subscribe(operations => {
            this.operations = operations;
            this.loadCategories();
          }
        );

      });
    this.query = "";

  }

  yearMinusMonth() {
    if (this.month > 1) {
      return this.year;
    } else {
      return this.year - 1;
    }
  }

  previousMonth() {
    if (this.month > 1) {
      return this.month - 1;
    } else {
      return 12;
    }
  }

  yearPlusMonth() {
    if (this.month < 12) {
      return this.year;
    } else {
      return this.year + 1;
    }
  }

  nextMonth() {
    if (this.month < 12) {
      return this.month + 1;
    } else {
      return 1;
    }
  }

  private loadCategories() {
    this.categories = new Map<string, number>();

    for (let operation of this.operations) {
      let category = operation.category != undefined ? operation.category : "undefined";

      let value = this.categories.get(category);
      if (value == undefined) {
        value = 0;
      }
      value += operation.amount;
      this.categories.set(category, value);
    }
  }

  getPositiveCategories() {
    return Array.from(this.categories.entries()).filter(category =>
      category[1] > 0
    ).sort((a, b) => {
      if (a[1] < b[1]) {
        return 1;
      } else if (a[1] == b[1]) {
        return 0;
      } else {
        return -1;
      }
    });
  }

  getNegativeCategories() {
    return Array.from(this.categories.entries()).filter(category =>
      category[1] <= 0
    ).sort((a, b) => {
      if (a[1] < b[1]) {
        return -1;
      } else if (a[1] == b[1]) {
        return 0;
      } else {
        return 1;
      }
    });
  }

  getOperations(): Array<OperationModel> {
    return this.filterOperations(this.operations).sort((a, b) => {
      if (a.creationDate > b.creationDate) {
        return -1;
      } else if (a.creationDate == b.creationDate) {
        return 0;
      } else {
        return 1;
      }
    });
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
