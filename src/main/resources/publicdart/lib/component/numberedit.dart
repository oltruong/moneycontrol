import 'package:angular/angular.dart';
import 'package:moneycontrol/service/query_service.dart';


@Component(
    selector: 'numberedit',
    templateUrl: '/lib/component/numberedit_component.html',
    cssUrl: const ['/stylesheets/bootstrap.min.css', '/stylesheets/main.css'])
class NumberEditComponent {


  @NgTwoWay('value')
  Float value;

  @NgTwoWay('object')
  Object object;

  final QueryService queryService;

  bool _editMode = false;

  NumberEditComponent(this.queryService) {
  }

  get editMode => _editMode;

  void set editMode(bool edit) {
    _editMode = edit;
  }

  void edit() {
    _editMode = false;
    queryService.edit(object);
  }


}
