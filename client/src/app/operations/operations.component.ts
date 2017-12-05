import {Component, OnInit} from '@angular/core';
import {OperationModel} from "../models/operation.model";
import {OperationService} from "../operation.service";

@Component({
  selector: 'app-operations',
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.sass']
})
export class OperationsComponent implements OnInit {

  operations: Array<OperationModel> = [];

  constructor(private operationService: OperationService) {}

  ngOnInit() {
    this.operationService.list().subscribe(operations => this.operations = operations);

  }
}
