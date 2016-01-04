import 'package:angular/angular.dart';

void moneycontrolRouteInitializer(Router router, RouteViewFactory views) {
  views.configure({
      'operationsByMonth': ngRoute(
          path: '/operations/:year/:month',
          view: '/view/operations.html'),
      'operationsByYear': ngRoute(
          path: '/operations/:year',
          view: '/view/operations.html'),
      'fileupload': ngRoute(
          path: '/fileupload',
          view: '/view/fileupload.html'),
      'rules': ngRoute(
          path: '/rules',
          view: '/view/rules.html'),
      'home': ngRoute(
          path: '/home',
          view: '/view/home.html'),
      'default': ngRoute(
          defaultRoute: true,
          view: '/view/home.html')

  });
}