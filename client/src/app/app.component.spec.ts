import {TestBed} from '@angular/core/testing';
import {RouterTestingModule} from '@angular/router/testing';

import {AppComponent} from './app.component';
import {AppModule} from './app.module';
import {OperationsComponent} from "./operations/operations.component";
import {By} from "@angular/platform-browser";
import {DebugElement} from "@angular/core";

describe('AppComponent', () => {
  let debugElement: DebugElement;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [AppModule, RouterTestingModule]
    });
    const fixture = TestBed.createComponent(AppComponent);
    debugElement = fixture.debugElement;
  });


  it('should use the operations component', () => {
    expect(debugElement.query(By.directive(OperationsComponent)))
      .toBeDefined('You probably forgot to add OperationsComponent to the AppComponent template');
  });
});
