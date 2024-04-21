package com.example.salonappointment.Model;

public class category_registration_model {

    private String categoryName, description, img_category;
    public category_registration_model(){
        //default constructor
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_category() {
        return img_category;
    }

    public void setImg_category(String img_category) {
        this.img_category = img_category;
    }

    public category_registration_model(String categoryName, String description, String img_category) {
        this.categoryName = categoryName;
        this.description = description;
        this.img_category = img_category;
    }
}
