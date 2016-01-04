library moneycontrol.main;

import 'package:angular/angular.dart';
import 'package:angular/application_factory.dart';
import 'package:moneycontrol/moneycontrol_module.dart';

@Injectable()
class GlobalController {
  String _currentMonth;

  GlobalController() {
  }

  String get currentMonth => _currentMonth;

  set currentMonth(String value) => _currentMonth = value;


}

void main() {
  applicationFactory().rootContextType(GlobalController).addModule(new MoneyControlModule())
  .run();
}
