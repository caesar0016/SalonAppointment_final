package com.example.salonappointment.Model;

public class register_service_model {

    private String photos, serviceName, description;
    private int duration;
    private double price;

    public register_service_model(String photos, String serviceName, String description, int duration, double price) {
        this.photos = photos;
        this.serviceName = serviceName;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
