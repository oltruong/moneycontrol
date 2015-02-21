library moneymag.moneymag_module;

import 'package:angular/angular.dart';
import 'package:moneycontrol/component/navbar.dart';
import 'package:moneycontrol/component/fileupload.dart';
import 'package:moneycontrol/component/celledit.dart';
import 'package:moneycontrol/component/numberedit.dart';
import 'package:moneycontrol/component/datebar.dart';
import 'package:moneycontrol/component/operations.dart';
import 'package:moneycontrol/component/rules.dart';
import 'package:moneycontrol/service/query_service.dart';
import 'package:moneycontrol/service/global_service.dart';
import 'package:moneycontrol/routing/moneycontrol_router.dart';
import 'package:moneycontrol/formatter/operation_filter.dart';

class MoneyMagModule extends Module {
  MoneyMagModule() {
    bind(QueryService);
    bind(GlobalService);
    bind(OperationFilter);
    bind(RuleFilter);
    bind(OperationsComponent);
    bind(RuleComponent);
    bind(CellEditComponent);
    bind(NumberEditComponent);
    bind(NavBarComponent);
    bind(DateBarComponent);
    bind(FileUploadComponent);
    bind(RouteInitializerFn, toValue: moneycontrolRouteInitializer);
  }
}
