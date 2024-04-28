package com.example.salonappointment.Model;

public class appointment_model {
    private String staffID;
    private String customerID;
    //private String customerName;
    private String date;
    private String timeSlot;
    private double price;
    private String status;
    private String archiveFlag;

    public appointment_model(String staffID, String customerID, String date, String timeSlot, double price, String status, String archiveFlag) {
        this.staffID = staffID;
        this.customerID = customerID;
        this.date = date;
        this.timeSlot = timeSlot;
        this.price = price;
        this.status = status;
        this.archiveFlag = archiveFlag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArchiveFlag() {
        return archiveFlag;
    }

    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }


}
