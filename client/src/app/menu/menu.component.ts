import {Component, OnInit} from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.sass']
})
export class MenuComponent implements OnInit {

  navbarCollapsed = true;

  year: string;
  month: number;

  constructor() {

  }

  ngOnInit() {
    this.year = moment().format('YYYY');
    this.month = +moment().format('MM');
  }

  toggleNavbar() {
    this.navbarCollapsed = !this.navbarCollapsed;
  }

}

