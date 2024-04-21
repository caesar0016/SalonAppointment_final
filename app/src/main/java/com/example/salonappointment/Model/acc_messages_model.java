package com.example.salonappointment.Model;

public class acc_messages_model {
    private String name, message, time, profilePicture;
    public acc_messages_model(){
        //default constructor
    }
    public acc_messages_model(String name, String message, String time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public acc_messages_model(String name, String message, String time, String profilePicture) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
