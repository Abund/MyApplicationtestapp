package com.example.myapplicationtestapp.model;

import java.util.Date;

public class Medication {

    private String medicationName;
    private Date starteDate;
    private String instructions;
    private String Units;
    private Date endDate;
    private String numberOfDays;
    private String notes;
    private String repeats;

    public Medication() {
    }

    public Medication(String medicationName, Date starteDate, String instructions, String units, Date endDate, String numberOfDays, String notes, String repeats) {
        this.medicationName = medicationName;
        this.starteDate = starteDate;
        this.instructions = instructions;
        this.Units = units;
        this.endDate = endDate;
        this.numberOfDays = numberOfDays;
        this.notes = notes;
        this.repeats = repeats;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Date getStarteDate() {
        return starteDate;
    }

    public void setStarteDate(Date starteDate) {
        this.starteDate = starteDate;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRepeats() {
        return repeats;
    }

    public void setRepeats(String repeats) {
        this.repeats = repeats;
    }
}
