import {TestBed} from '@angular/core/testing';

import {AppComponent} from './app.component';
import {AppModule} from './app.module';
import {OperationsComponent} from "./operations/operations.component";
import {By} from "@angular/platform-browser";

describe('AppComponent', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [AppModule]
  }));

  it('should have a title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const element = fixture.nativeElement;
    expect(element.querySelector('h1').textContent).toContain("MoneyControl");
  });

  it('should use the operations component', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const element = fixture.debugElement;
    expect(element.query(By.directive(OperationsComponent)))
      .toBeDefined('You probably forgot to add OperationsComponent to the AppComponent template');
  });
});
