package com.example.angusyuen.fishwat;

/**
 * Created by angusyuen on 23/04/16.
 */
public class Fish {
    private String nameOfFish;
    private String scientificNameOfFish;
    private String description;
    private String isConsumable;
    private int isSeasonable;
    private String images;
    private String primaryImage;

    public Fish(String name, String scientificNameOfFish, String description, String consumptionStatus,
                int seasonStatus, String images, String primaryImage) {
        nameOfFish = name;
        this.scientificNameOfFish = scientificNameOfFish;
        this.description = description;
        isConsumable = consumptionStatus;
        isSeasonable = seasonStatus;
        this.images = images;
        this.primaryImage = primaryImage;
    }

    public String getDescription() {
        return description;
    }

    public String getIsConsumable() {
        return isConsumable;
    }

    public int getIsSeasonable() {
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
