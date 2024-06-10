package com.example.salonappointment.Model;

public class writeReviewsModel {
    private String staffUID;
    private String customerID;
    private String title;
    private String description;
    private int scoreRating;
    private String date;

    public writeReviewsModel(String staffUID, String customerID, String title, String description, int scoreRating, String date) {
        this.staffUID = staffUID;
        this.customerID = customerID;
        this.title = title;
        this.description = description;
        this.scoreRating = scoreRating;
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getStaffUID() {
        return staffUID;
    }

    public void setStaffUID(String staffUID) {
        this.staffUID = staffUID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScoreRating() {
        return scoreRating;
    }

    public void setScoreRating(int scoreRating) {
        this.scoreRating = scoreRating;
    }
}
