import 'package:angular/angular.dart';
import 'package:moneycontrol/service/query_service.dart';


@Component(
    selector: 'celledit',
    templateUrl: '/lib/component/celledit_component.html',
    cssUrl: const ['/stylesheets/bootstrap.min.css', '/stylesheets/main.css'])
class CellEditComponent {


  @NgTwoWay('value')
  String value;

  @NgTwoWay('object')
  Object object;

  final QueryService queryService;

  bool _editMode = false;

  CellEditComponent(this.queryService) {
  }

  get editMode => _editMode;

  void set editMode(bool edit) {
    _editMode = edit;
  }

  void edit() {
    _editMode = false;
    queryService.edit(object);
  }


  void activateEdit() {
    _editMode = true;
  }


}
