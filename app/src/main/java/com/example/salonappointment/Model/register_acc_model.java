package com.example.salonappointment.Model;

public class register_acc_model {
    private String name, email, userType, password,profilePic;


    public register_acc_model(String name, String email, String userType) {
        this.name = name;
        this.email = email;
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public register_acc_model(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
