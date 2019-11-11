package com.example.myapplicationtestapp.model;

import java.sql.Time;
import java.util.Date;

public class BloodPressure {
    private int systolicPressure;
    private int diastolicPressure;
    private int measuredArm;
    private Date date;
    private Time time;
    private String notes;
    private String tag;

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getMeasuredArm() {
        return measuredArm;
    }

    public void setMeasuredArm(int measuredArm) {
        this.measuredArm = measuredArm;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
