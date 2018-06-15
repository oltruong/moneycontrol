import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CellEditComponent} from './cell-edit.component';
import {FormsModule} from "@angular/forms";

describe('CellEditComponent', () => {
  let component: CellEditComponent;
  let fixture: ComponentFixture<CellEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule],
      declarations: [CellEditComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CellEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
