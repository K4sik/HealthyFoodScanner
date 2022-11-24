package com.kas.healthyfoodscanner.ui.database;

public class Product {

    private String id;
    private String companyName;
    private String productName;
    private String ingredients;
    private int kcal;
    private int sugar;
    private int salt;

    public Product() {
    }

    public Product(String id, String companyName, String productName, String ingredients, int kcal, int sugar, int salt) {
        this.id = id;
        this.companyName = companyName;
        this.productName = productName;
        this.ingredients = ingredients;
        this.kcal = kcal;
        this.sugar = sugar;
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }
}
