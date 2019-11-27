package com.example.myapplicationtestapp.model;

public class Calorie {
    private String time;
    private String foodType;
    private String calorieUnits;
    private String date;
    private String foodName;
    private String foodContent;

    public String getFoodContent() {
        return foodContent;
    }

    public void setFoodContent(String foodContent) {
        this.foodContent = foodContent;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String timeType) {
        this.foodType = timeType;
    }

    public String getCalorieUnits() {
        return calorieUnits;
    }

    public void setCalorieUnits(String calorieUnits) {
        this.calorieUnits = calorieUnits;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
