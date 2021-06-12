package com.oltruong.moneycontrol.operation;

public class ClassificationChange {

    private String category;
    private String subcategory;
    private String newCategory;
    private String newSubcategory;
    private int year;

    public ClassificationChange() {
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setNewCategory(String newCategory) {
        this.newCategory = newCategory;
    }

    public void setNewSubcategory(String newSubcategory) {
        this.newSubcategory = newSubcategory;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getNewCategory() {
        return newCategory;
    }

    public String getNewSubcategory() {
        return newSubcategory;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "ClassificationChange{" +
                "category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", newCategory='" + newCategory + '\'' +
                ", newSubcategory='" + newSubcategory + '\'' +
                ", year=" + year +
                '}';
    }
}
