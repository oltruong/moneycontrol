import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-cell-edit',
  templateUrl: './cell-edit.component.html',
  styleUrls: ['./cell-edit.component.sass']
})
export class CellEditComponent implements OnInit {

  @Input() value: String;

  private editMode: Boolean;

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
  }

  update() {
    console.log("UPDATE");
  }

}
