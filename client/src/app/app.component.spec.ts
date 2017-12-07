import {TestBed} from '@angular/core/testing';

import {AppComponent} from './app.component';
import {AppModule} from './app.module';
import {OperationsComponent} from "./operations/operations.component";
import {By} from "@angular/platform-browser";
import {DebugElement} from "@angular/core";

describe('AppComponent', () => {
  let debugElement: DebugElement;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [AppModule]
    });
    const fixture = TestBed.createComponent(AppComponent);
    debugElement = fixture.debugElement;
  });

  it('should have a title', () => {
    const title = debugElement.query(By.css('h1')).nativeElement;
    expect(title.textContent).toContain("MoneyControl");
  });

  it('should use the operations component', () => {
    expect(debugElement.query(By.directive(OperationsComponent)))
      .toBeDefined('You probably forgot to add OperationsComponent to the AppComponent template');
  });
});
