package com.kas.healthyfoodscanner.ui.search;

public class SearchItem {

    private String barcode;
    private String imgUrl;
    private String productName;
    private String whites;
    private String fats;
    private String carbohydrates;
    private String calories;
    private String additives;
    private String detailUrl;

    public SearchItem() {
    }

    public SearchItem(String barcode, String imgUrl, String productName, String whites, String fats, String carbohydrates, String calories, String additives, String detailUrl) {
        this.barcode = barcode;
        this.imgUrl = imgUrl;
        this.productName = productName;
        this.whites = whites;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.additives = additives;
        this.detailUrl = detailUrl;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWhites() {
        return whites;
    }

    public void setWhites(String whites) {
        this.whites = whites;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getAdditives() {
        return additives;
    }

    public void setAdditives(String additives) {
        this.additives = additives;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
