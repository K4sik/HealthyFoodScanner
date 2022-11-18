package com.kas.healthyfoodscanner.ui.home;

public class Page {

    private int imgId;
    private String name;
    private int imgId2;

    public Page() {    }

    public Page(int imgId, String name, int imgid2) {
        this.imgId = imgId;
        this.name = name;
        this.imgId2 = imgid2;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId2() {
        return imgId2;
    }

    public void setImgId2(int imgid2) {
        this.imgId2 = imgid2;
    }
}
