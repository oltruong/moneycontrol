import 'package:angular/angular.dart';
import 'package:moneycontrol/model/model.dart';
import 'package:moneycontrol/service/query_service.dart';
import 'package:moneycontrol/service/global_service.dart';
import 'package:moneycontrol/formatter/operation_filter.dart';

@Component(
    selector: 'operations',
    templateUrl: '/lib/component/operations_component.html',
    cssUrl: const ['/stylesheets/bootstrap.min.css', '/stylesheets/main.css'])
class OperationsComponent {
  final QueryService queryService;
  final GlobalService globalService;
  final OperationFilter operationFilter;

  String _filterText;

  String _categoryOrder = "categoryName";

  get categoryOrder => _categoryOrder;

  void setCategoryOrder(String value) {
    if (_categoryOrder == value) {
      _categoryOrder = "-" + value;
    } else {
      _categoryOrder = value;
    }
  }


  List<Boolean> tabList = [true, false, false, false];

  Boolean _showInput = false;

  void setTab(int number) {

    for (int i = 0;i < tabList.length;i++) {

      tabList[i] = number == i;
    }
  }

  Boolean showTab(int number) {
    return tabList[number];
  }

  List<Operation> _operations = new List<Operation>();

  List<Category> _categories = new List<Category>();
  List<SubCategory> _subCategories = new List<SubCategory>();

  Boolean get showInput => _showInput;

  void set showInput(Boolean value) => _showInput = value;

  OperationsComponent(this.queryService, this.operationFilter, this.globalService, RouteProvider routeProvider) {
    globalService.pageUrl = routeProvider.route.name;

    String month = routeProvider.parameters['month'];
    String year = routeProvider.parameters['year'];

    globalService.year = year;

    _loadData(year, month);
  }


  void delete(Operation operation) {
    queryService.deleteOperation(operation.id);
    _operations.remove(operation);
    computeCategories();
  }

  void duplicate(Operation operation) {
    int id = operation.id;
    Float amount = operation.amount;
    operation.id = null;
    operation.amount = 0;
    queryService.create(operation).then((String uri) {
      if (uri != null) {
        queryService.getObject(uri).then((var data) {
          Operation newOperation = new Operation.fromJson(data);
          _operations.add(newOperation);
          computeCategories();
        });

      }
    });
    operation.id = id;
    operation.amount = amount;
  }


  computeCategories() {
    Map<String, Float> categoryMap = new Map();
    Map<String, Float> subCategoryMap = new Map();


    for (Operation operation in _operations) {
      String category = getValueOrUndefined(operation.category);
      String subCategory = getValueOrUndefined(operation.subcategory);


      if (categoryMap.containsKey(category)) {
        categoryMap[category] += operation.amount;
      } else {
        categoryMap[category] = operation.amount;
      }


      String subcategoryKey = subCategory + "*" + category;
      if (subCategoryMap.containsKey(subcategoryKey)) {
        subCategoryMap[subcategoryKey] += operation.amount;
      } else {
        subCategoryMap[subcategoryKey] = operation.amount;
      }
    }

    _categories = new List<Category>();
    for (String categoryName in categoryMap.keys) {

      Category category = new Category(categoryName);
      category.amount = categoryMap[categoryName];
      _categories.add(category);
    }

    _subCategories = new List<SubCategory>();
    for (String key in subCategoryMap.keys) {
      List<String> stringList = key.split("*");
      String category = stringList[1];
      String subCategory = stringList[0];
      SubCategory subcategory = new SubCategory(subCategory);
      subcategory.categoryName = category;
      subcategory.amount = subCategoryMap[key];
      _subCategories.add(subcategory);

    }
  }

  String getValueOrUndefined(String value) {
    if (isNotEmpty(value)) {
      return value;
    } else {
      return "Non défini";
    }
  }

  bool isNotEmpty(String value) {
    return value != null && !value.trim().isEmpty;
  }

  void _loadData(String year, String month) {

    Future<List<Operation>> operationsList;
    globalService.month = month ;

    if (month == null) {
      operationsList = queryService.getOperationsByYear(year);
    } else {
      operationsList = queryService.getOperationsByMonthAndYear(year, month);
    }

    operationsList.then((List operationList) {
      _operations = new List.from(operationList);

      computeCategories();
    }) .catchError((e) {
      print(e);
    });
  }

  get filterText => _filterText;

  void set filterText(String value) {
    _filterText = value;
  }

  get operations => _operations;

  int totalOperations() {
    return this.operationFilter.call(_operations, _filterText).length;
  }

  Float total() {
    Float value = 0;
    for (Operation operation in this.operationFilter.call(_operations, _filterText)) {
      value += operation.amount;
    }
    return value;
  }

  Float balance() {
    return sum(_categories);
  }

  Float sum(List<Category> categoryList) {
    Float value = 0;
    for (Category category in categoryList) {
      if (category.amount != null) {
        value += category.amount;
      }
    }
    return value;
  }


  get positive_categories => _categories.where((c) => c.amount > 0).toList();

  get negative_categories => _categories.where((c) => c.amount < 0).toList();

  get positive_subCategories => _subCategories.where((c) => c.amount > 0).toList();

  get negative_subCategories => _subCategories.where((c) => c.amount < 0).toList();

  get subCategories => _subCategories;


}