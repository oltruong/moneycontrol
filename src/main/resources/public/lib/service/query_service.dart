import 'dart:async';
import 'dart:convert';
import 'package:moneycontrol/model/model.dart';
import 'package:angular/angular.dart';

@Injectable()
class QueryService {

  String _operationsUrl = '/rest/operation/period/';
  final Http _http;

  QueryService(Http this._http) {
  }

  Future<List<Operation>> getOperationsByYear(String year) {
    if (year == 'all') {
      return getOperations('/rest/operation');
    } else {
      return getOperations('/rest/operation/period/' + year);
    }
  }


  Future<List<Operation>> getOperationsByMonthAndYear(String year, String month) {
    return getOperations('/rest/operation/period/' + year + '/' + month);

  }

  Future<List<Operation>> getOperations(String url) {
    return _http.get(url)
    .then((HttpResponse response) {
      List<Operation> operationList = new List();
      for (Map operationJson in response.data) {
        Operation operation = new Operation.fromJson(operationJson);
        operationList.add(operation);
      }
      return operationList;
    });
  }


  Future<List<Rule>> getRules() {
    return _http.get("/rest/rule")
    .then((HttpResponse response) {
      List<Rule> ruleList = new List();
      for (Map ruleJson in response.data) {
        ruleList.add(new Rule.fromJson(ruleJson));
      }
      return ruleList;
    });
  }

  void deleteRule(int id) {
    _http.delete("/rest/rule/" + id.toString());
  }

  void deleteOperation(int id) {
    _http.delete("/rest/operation/" + id.toString());
  }

  Future<String> create(Object object) {
    String url = getUrl(object);
    return _http.post('/rest/' + url, JSON.encode(object)) .then((HttpResponse response) {
      return response.headers("location");
    }).catchError((e) {
      print('error on create rule');
      print(e);
      return null;
    });
  }

  Future getObject(String location) {
    var result;
    return _http.get(location).then((HttpResponse response) {
      return response.data;
    }).catchError((e) {
      print('error on getObject');
      print(e);
      return null;
    });
  }


  void refreshClassification() {
    _http.post('/rest/operation/refresh', null).catchError((e) {
      print('error on refresh classification');
      print(e);
    });

  }

  String getUrl(Object object) {
    String url = "";
    if (object is Operation) {
      url = 'operation';
    } else if (object is Rule) {
      url = 'rule';
    }
    return url;
  }

  void edit(Object object) {
    String url = getUrl(object);

    _http.put('/rest/' + url + '/' + object.id.toString(), JSON.encode(object)).catchError((e) {
      print('error on edit');
      print(e);
    });
  }


  String buildUrl(String month) {
    if (month != null) {
      return _operationsUrl + month;
    } else {
      return "/rest/operation";
    }

  }

}
