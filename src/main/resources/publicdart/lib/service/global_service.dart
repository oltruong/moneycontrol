import 'package:angular/angular.dart';

@Injectable()
class GlobalService {

  String _pageUrl;
  String _month;

  String _year;

  GlobalService() {
    _year = new DateTime.now().year.toString();
  }

  get pageUrl => _pageUrl;

  void set pageUrl(String value) {
    _pageUrl = value;
  }

  get month => _month;

  void set month(String value) {
    _month = value;
  }

  String get year => _year;

  set year(String value) => _year = value;


}
