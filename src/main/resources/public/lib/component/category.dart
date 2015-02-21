import 'package:angular/angular.dart';
import 'package:moneycontrol/model/model.dart';

@Component(
    selector: 'category',
    templateUrl: '/lib/component/category_component.html',
    cssUrl: const ['/stylesheets/bootstrap.min.css', '/stylesheets/main.css'])
class CategoryComponent {

  @NgOneWay('category')
  Category category;

}