package com.oltruong.moneycontrol.operation;


import org.bson.Document;

public class Statistic {

    private Double amount;
    private String category;
    private int month;
    private int year;

    public Statistic(Double amount, String category, int month, int year) {
        this.amount = amount;
        this.category = category;
        this.month = month;
        this.year = year;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static Statistic build(Document document) {
        final Document id = document.get("_id", Document.class);
        final Statistic statistic = new Statistic(document.getDouble("totalAmount"), id.getString("category"), id.getInteger("month"), id.getInteger("year"));
        System.out.println(document);
        return statistic;
    }
}
