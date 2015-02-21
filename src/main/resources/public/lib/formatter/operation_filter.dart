import 'package:angular/angular.dart';

@Formatter(name: 'operationFilter')
class OperationFilter {
  List call(operationList, filterText) {
    if (operationList is Iterable && filterText is String && filterText.length >= 3) {
      return operationList.where((o) => (o.buildFormat().toLowerCase()).contains(filterText.toLowerCase())).toList();
    }

    return operationList;
  }
}

@Formatter(name: 'ruleFilter')
class RuleFilter {
  List call(ruleList, filterText) {
    if (ruleList is Iterable && filterText is String) {
      return ruleList.where((o) => (o.buildFormat().toLowerCase()).contains(filterText.toLowerCase())).toList();
    }

    return ruleList;
  }
}
