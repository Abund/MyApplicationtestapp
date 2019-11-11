package com.example.myapplicationtestapp.model;

import java.sql.Time;
import java.util.Date;

public class BloodSugar {

    private int concentrationSugar;
    private int measured;
    private Date date;
    private Time time;
    private String notes;
    private String tag;

    public int getConcentrationSugar() {
        return concentrationSugar;
    }

    public void setConcentrationSugar(int concentrationSugar) {
        this.concentrationSugar = concentrationSugar;
    }

    public int getMeasured() {
        return measured;
    }

    public void setMeasured(int measured) {
        this.measured = measured;
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
