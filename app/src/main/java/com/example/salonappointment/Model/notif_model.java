package com.example.salonappointment.Model;

public class notif_model {
    private String date, notif_message;
    public notif_model(String date, String notif_message) {
        this.date = date;
        this.notif_message = notif_message;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotif_message() {
        return notif_message;
    }

    public void setNotif_message(String notif_message) {
        this.notif_message = notif_message;
    }
}
