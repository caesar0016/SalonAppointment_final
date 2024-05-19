package com.example.salonappointment.Model;

public class staffSched_model {
    private String Staff_uid;
    private String time;
    private boolean isTaken;
    private String date;
    public static String FinalTime;

    public staffSched_model() {

    }

    public staffSched_model(String staff_uid, String time, boolean isTaken, String date) {
        Staff_uid = staff_uid;
        this.time = time;
        this.isTaken = isTaken;
        this.date = date;
    }

    public staffSched_model(String staff_uid, String time, boolean isTaken) {
        Staff_uid = staff_uid;
        this.time = time;
        this.isTaken = isTaken;
    }

    public String getStaff_uid() {
        return Staff_uid;
    }

    public void setStaff_uid(String staff_uid) {
        Staff_uid = staff_uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
