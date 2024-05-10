package com.example.salonappointment.Model;

public class staffSched_model {
    private String Staff_uid;
    private String startTime;
    private String startAmOrPm;
    private String endTime;
    private String endAmOrPm;
    private boolean isTaken;
    public static String FinalTime;

    public staffSched_model() {
    }

    public staffSched_model(String staff_uid, String startTime, String startAmOrPm, String endTime, String endAmOrPm, boolean isTaken) {
        Staff_uid = staff_uid;
        this.startTime = startTime;
        this.startAmOrPm = startAmOrPm;
        this.endTime = endTime;
        this.endAmOrPm = endAmOrPm;
        this.isTaken = isTaken;
    }

    public String getStaff_uid() {
        return Staff_uid;
    }

    public void setStaff_uid(String staff_uid) {
        Staff_uid = staff_uid;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartAmOrPm() {
        return startAmOrPm;
    }

    public void setStartAmOrPm(String startAmOrPm) {
        this.startAmOrPm = startAmOrPm;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndAmOrPm() {
        return endAmOrPm;
    }

    public void setEndAmOrPm(String endAmOrPm) {
        this.endAmOrPm = endAmOrPm;
    }
}
