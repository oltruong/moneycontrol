package com.oltruong.moneycontrol.operation;

import org.bson.Document;

public class Classification {

    private String category;
    private String subcategory;

    public Classification(String category, String subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public static Classification build(Document document) {
        final Document id = document.get("_id", Document.class);
        return new Classification(id.getString("category"), id.getString("subcategory"));
    }
}
