package com.example.salonappointment.Model;

public class writeReviewsModel {
    private String staffUID;
    private String customerID;
    private String title;
    private String description;
    private int scoreRating;

    public writeReviewsModel(String staffUID, String customerID, String title, String description, int scoreRating) {
        this.staffUID = staffUID;
        this.customerID = customerID;
        this.title = title;
        this.description = description;
        this.scoreRating = scoreRating;
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
