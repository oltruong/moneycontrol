import 'package:angular/angular.dart';
import 'package:moneycontrol/service/global_service.dart';


@Component(
    selector: 'navbar',
    templateUrl: '/lib/component/navbar_component.html',
    cssUrl: const ['/stylesheets/bootstrap.min.css', '/stylesheets/main.css'])
class NavBarComponent {

  int _currentMonth;
  int _currentYear;
  final GlobalService globalService;

  NavBarComponent(GlobalService this.globalService) {
    _currentMonth = new DateTime.now().month;
    _currentYear = new DateTime.now().year;
  }

  get currentMonth => _currentMonth;

  get currentYear => _currentYear;

  String getClass(String value) {

    bool isActive = value == globalService.pageUrl;
    if (!isActive) {
      isActive = globalService.pageUrl == "operations" && ((value == "operationsCurrentMonth" && globalService.month == _currentMonth.toString()) || value == globalService.month);
    }

    return isActive ? "active" : "";

  }
}