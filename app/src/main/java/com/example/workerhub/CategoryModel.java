package com.example.workerhub;

public class CategoryModel {
    private String cat_name;
    private int img_id;

    public CategoryModel(String cat_name, int img_id) {
        this.cat_name = cat_name;
        this.img_id = img_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public int getImg_id() {
        return img_id;
    }
}
