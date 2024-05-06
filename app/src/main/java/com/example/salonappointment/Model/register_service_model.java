package com.example.salonappointment.Model;

public class register_service_model {

    private String serviceName, description;
    private double ReservationPrice;
    private String URLpicture;

    public register_service_model() {
        //Default constructor
    }

    public register_service_model(String serviceName, String description, double reservationPrice, String URLpicture) {
        this.serviceName = serviceName;
        this.description = description;
        ReservationPrice = reservationPrice;
        this.URLpicture = URLpicture;
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

    public double getReservationPrice() {
        return ReservationPrice;
    }

    public void setReservationPrice(double reservationPrice) {
        ReservationPrice = reservationPrice;
    }

    public String getURLpicture() {
        return URLpicture;
    }

    public void setURLpicture(String URLpicture) {
        this.URLpicture = URLpicture;
    }
}
