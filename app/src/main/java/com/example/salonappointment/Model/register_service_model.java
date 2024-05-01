package com.example.salonappointment.Model;

public class register_service_model {

    private String serviceName, description;
    private double ReservationPrice;

    private String URLpicture;
    private int duration;

    public register_service_model() {
        //Default constructor
    }
    public register_service_model(String serviceName, String description, double price, String URLpicture) {
        this.serviceName = serviceName;
        this.description = description;
        this.ReservationPrice = ReservationPrice;
        this.URLpicture = URLpicture;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return ReservationPrice;
    }

    public void setPrice(double price) {
        this.ReservationPrice = price;
    }

    public String getPicture() {
        return URLpicture;
    }

    public void setPicture(String URLpicture) {
        this.URLpicture = URLpicture;
    }
}
