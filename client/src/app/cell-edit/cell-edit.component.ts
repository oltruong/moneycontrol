import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-cell-edit',
  templateUrl: './cell-edit.component.html',
  styleUrls: ['./cell-edit.component.sass']
})
export class CellEditComponent implements OnInit {

  @Input() editableValue: string;
  @Output() valueClicked = new EventEmitter<string>();

  editMode: Boolean;

  constructor() {
  }

  ngOnInit() {
    this.editMode = false;
  }

  edit() {
    this.editMode = true;
  }

  clear() {
    this.editMode = false;
    console.log("CLEEE " + this.editableValue);
    this.valueClicked.emit(this.editableValue);

  }

  changed() {
    console.log("EEEEMIT " + this.editableValue);
    this.valueClicked.emit(this.editableValue);
  }

}
