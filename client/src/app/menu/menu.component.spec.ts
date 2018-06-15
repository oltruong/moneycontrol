import {TestBed} from '@angular/core/testing';

import {AppModule} from '../app.module';
import {MenuComponent} from './menu.component';

describe('MenuComponent', () => {

  beforeEach(() => TestBed.configureTestingModule({
    imports: [AppModule]
  }));

  it('should have a `navbarCollapsed` field', () => {
    const menu: MenuComponent = new MenuComponent();
    menu.ngOnInit();
    expect(menu.navbarCollapsed)
      .toBe(true, 'Check that `navbarCollapsed` is initialized with `true`.' +
        'Maybe you forgot to declare `navbarCollapsed` in your component.');
  });

  it('should have a `toggleNavbar` method', () => {
    const menu: MenuComponent = new MenuComponent();
    expect(menu.toggleNavbar)
      .not.toBeNull('Maybe you forgot to declare a `toggleNavbar()` method');

    menu.toggleNavbar();

    expect(menu.navbarCollapsed)
      .toBe(false, '`toggleNavbar()` should change `navbarCollapsed` from true to false`');

    menu.toggleNavbar();

    expect(menu.navbarCollapsed)
      .toBe(true, '`toggleNavbar()` should change `navbarCollapsed` from false to true`');
  });
});
