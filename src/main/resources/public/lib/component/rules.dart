import 'package:angular/angular.dart';
import 'package:moneycontrol/model/model.dart';
import 'package:moneycontrol/service/query_service.dart';
import 'package:moneycontrol/service/global_service.dart';
import 'package:moneycontrol/formatter/operation_filter.dart';

@Component(
    selector: 'rules',
    templateUrl: '/lib/component/rules_component.html',
    cssUrl: const ['/stylesheets/bootstrap.min.css', '/stylesheets/main.css'])
class RuleComponent {
  final QueryService queryService;
  final GlobalService globalService;

  String _filterText;

  Boolean _showOperations = true;

  Boolean _showInput = false;

  List<Rule> _rules = new List<Rule>();

  Router _router;

  Boolean get showInput => _showInput;


  void set showInput(Boolean value) => _showInput = value;

  RuleComponent(this.queryService, this.globalService, RouteProvider routeProvider, Router router) {
    globalService.pageUrl = routeProvider.route.name;
    _router = router;
    _loadRules();
  }

  void _loadRules() {

    queryService.getRules().then((List ruleList) {
      _rules = new List.from(ruleList);
    }) .catchError((e) {
      print(e);
    });
  }

  get filterText => _filterText;

  void set filterText(String value) {
    _filterText = value;
  }

  void delete(Rule rule) {
    queryService.deleteRule(rule.id);
    _rules.remove(rule);
  }

  void duplicate(Rule rule) {
    int id = rule.id;
    rule.id = null;
    queryService.create(rule);

    queryService.create(rule).then((String uri) {
      if (uri != null) {
        queryService.getObject(uri).then((var data) {
          Rule newRule = new Rule.fromJson(data);
          _rules.add(newRule);
        });

      }
    });
    rule.id = id;
  }

  redirectUnclassified() {
    _router.go("operationsByYear", {
        'year': 'unclassified'
    });
  }

  void refresh() {
    queryService.refreshClassification();
    redirectUnclassified();
  }

  get rules => _rules;


}