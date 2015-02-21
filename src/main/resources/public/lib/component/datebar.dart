import 'package:angular/angular.dart';
import 'package:intl/intl.dart';
import 'package:moneycontrol/service/global_service.dart';


@Component(
    selector: 'datebar',
    templateUrl: '/lib/component/datebar_component.html',
    cssUrl: const ['/stylesheets/bootstrap.min.css', '/stylesheets/main.css'])
class DateBarComponent {

  List<DateTime> _monthList ;

  final GlobalService globalService;

  DateFormat _dateFormat;

  String _year;

  DateBarComponent(GlobalService this.globalService, RouteProvider routeProvider) {
    Intl.defaultLocale = 'fr';
    _dateFormat = new DateFormat('MMM');
    _monthList = new List();

    String year = routeProvider.parameters['year'];
    globalService.year = year;


    if (globalService.year != 'all' && globalService.year != 'unclassified') {
      _year = globalService.year;
    }
    else {
      _year = new DateTime.now().year.toString();
    }
    for (var i = 1; i < 13; i++) {
      _monthList.add(new DateTime(int.parse(_year), i, 1));
    }


  }


  String checkClass(DateTime month) {
    if (month.month.toString() == globalService.month) {
      return "active";
    }
    return "";
  }

  String checkAllYear() {
    if (null == globalService.month && globalService.year != 'all' && globalService.year != 'unclassified') {
      return "active";
    }
    return "";
  }

  String checkYear(String value) {
    if (value == globalService.year) {
      return "active";
    }
    return "";
  }

  get monthList => _monthList;

  get year => _year;

  get month => globalService.month;

  get dateFormat => _dateFormat;

}