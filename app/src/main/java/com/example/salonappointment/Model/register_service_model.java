package com.example.salonappointment.Model;

public class register_service_model {

    private String serviceName, description;
    private double price;

    private String picture;
    private int duration;

    public register_service_model(String serviceName, String description, double price) {
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
    }

    public register_service_model(String serviceName, String description, double price, String picture) {
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public register_service_model(String serviceName, String description, double price, String picture, int duration) {
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.duration = duration;
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
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
