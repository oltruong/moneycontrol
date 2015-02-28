import 'dart:convert';
import 'dart:mirrors';

abstract class Serializable {

  Map toJson() {
    Map map = new Map();
    InstanceMirror im = reflect(this);
    ClassMirror cm = im.type;
    var decls = cm.declarations.values.where((dm) => dm is VariableMirror);
    decls.forEach((dm) {
      var key = MirrorSystem.getName(dm.simpleName);
      var val = im.getField(dm.simpleName).reflectee.toString();
      if (key == 'creationDate') {
        val = im.getField(dm.simpleName).reflectee.millisecondsSinceEpoch;
      }
      if (val == 'null') {
        val = null;
      }
      map[key] = val;
    });

    return map;
  }

}

class Operation extends Object with Serializable {

  int id;
  String name;
  DateTime creationDate;
  int month;
  int year;
  String category;
  String subcategory;
  String recipient;
  String comment;

  Float amount;

  Operation(this.id, this.name, this.creationDate, this.month, this.year, this.category,
            this.subcategory, this.recipient, this.amount, this.comment);


  Operation.fromJson(Map<String, dynamic> json) : this(json['id'],
  json['name'], new DateTime.fromMillisecondsSinceEpoch(json['creationDate']), json['month'], json['year'], json['category'], json['subcategory'],
  json['recipient'], json['amount'], json['comment']);


  String buildFormat() {
    String result = "";
    if (name != null) {
      result += " " + name;
    }
    if (amount != null) {
      result += " " + amount.toStringAsFixed(2);
    }
    if (category != null) {
      result += " " + category;
    }
    if (subcategory != null) {
      result += " " + subcategory;
    }
    if (comment != null) {
      result += " " + comment;
    }
    if (recipient != null) {
      result += " " + recipient;
    }

    return result;


  }

}

class Category {
  String name;
  Float amount = 0;

  Category(this.name);

  String toString() {
    return "Name [" + name + "] - Amount [" + amount.toString() + "]";
  }
}

class SubCategory {
  String name;
  String categoryName;
  Float amount = 0;

  SubCategory(this.name);

  String toString() {
    return "Name [" + name + "] - Category [" + categoryName + "] - Amount [" + amount.toString() + "]";
  }

}


class Rule extends Object with Serializable {

  int id;
  String nameCondition;
  String category;
  String subcategory;
  String recipient;
  String comment;

  Float amountCondition;

  Rule(this.id, this.nameCondition, this.category, this.subcategory, this.recipient, this.comment, this.amountCondition);


  Rule.fromJson(Map<String, dynamic> json) : this(json['id'],
  json['nameCondition'], json['category'], json['subcategory'],
  json['recipient'], json['comment'], json['amountCondition']);

  String buildFormat() {
    String result = "";
    if (nameCondition != null) {
      result += " " + nameCondition;
    }
    if (amountCondition != null) {
      result += " " + amountCondition.toStringAsFixed(2);
    }
    if (category != null) {
      result += " " + category;
    }
    if (subcategory != null) {
      result += " " + subcategory;
    }
    if (comment != null) {
      result += " " + comment;
    }
    if (recipient != null) {
      result += " " + recipient;
    }

    return result;


  }

}
