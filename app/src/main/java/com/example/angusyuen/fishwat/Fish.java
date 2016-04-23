package com.example.angusyuen.fishwat;

/**
 * Created by angusyuen on 23/04/16.
 */
public class Fish {
    private String nameOfFish;
    private String scientificNameOfFish;

    public Fish(String name, String scientificNameOfFish) {
        nameOfFish = name;
        this.scientificNameOfFish = scientificNameOfFish;
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
