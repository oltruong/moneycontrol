import 'package:angular/angular.dart';
import 'package:moneycontrol/service/query_service.dart';
import 'dart:html';
import 'dart:async';


@Component(
    selector: 'fileupload',
    templateUrl: '/lib/component/fileupload_component.html',
    cssUrl: const ['/stylesheets/bootstrap.min.css', '/stylesheets/main.css'])
class FileUploadComponent {

  final Http _http;
  Router _router;


  FileUploadComponent(this._http, Router router) {
    _router = router;

  }

  get files => _files;

  void sendFile() {
    FileList files = document.querySelector('input').files;
    File file = files.item(0);
    final reader = new FileReader();

    reader.readAsText(files[0]);
    reader.onLoadEnd.listen((e) {

      HttpRequest.request("/rest/bankfileupload", method: "POST", sendData: reader.result).then((req) {
        redirectUnclassified();
      }).catchError((e) {
        print('error on post');
        print(e);
      });
    });


  }

  redirectUnclassified() {
    _router.go("operationsByYear", {
        'year': 'unclassified'
    });


  }
}
