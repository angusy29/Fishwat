package com.example.angusyuen.fishwat;

/**
 * Created by angusyuen on 23/04/16.
 */
public class Fish {
    private String nameOfFish;
    private String scientificNameOfFish;
    private String description;
    private boolean isConsumable;
    private boolean isSeasonable;

    public Fish(String name, String scientificNameOfFish, String description, boolean consumptionStatus, boolean seasonStatus) {
        nameOfFish = name;
        this.scientificNameOfFish = scientificNameOfFish;
        this.description = description;
        isConsumable = consumptionStatus;
        isSeasonable = seasonStatus;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsConsumable() {
        return isConsumable;
    }

    public boolean getIsSeasonable() {
        return isSeasonable;
    }

    public void setName(String name) {
        nameOfFish = name;
    }

    public void setScientificNameOfFish(String name) {
        scientificNameOfFish = name;
    }

    public String getName() {
        return nameOfFish;
    }

    public String getScientificNameOfFish() {
        return scientificNameOfFish;
    }
}
