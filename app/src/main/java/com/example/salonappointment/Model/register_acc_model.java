package com.example.salonappointment.Model;

public class register_acc_model {
    private String name, email, userType, uid, profileURl;

    public register_acc_model() {
        // Default constructor
    }


    public register_acc_model(String name, String email, String userType, String uid, String profileURl) {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.uid = uid;
        this.profileURl = profileURl;
    }

    public register_acc_model(String name, String email, String userType, String uid) {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfileURl() {
        return profileURl;
    }

    public void setProfileURl(String profileURl) {
        this.profileURl = profileURl;
    }
}
