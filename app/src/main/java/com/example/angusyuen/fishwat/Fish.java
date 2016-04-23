package com.example.angusyuen.fishwat;

/**
 * Created by angusyuen on 23/04/16.
 */
public class Fish {
    private Integer id;
    private String nameOfFish;
    private String scientificNameOfFish;
    private String images;
    private String recommendation;
    private Integer inSeason;
    private String primaryImage;

    public Fish(String name, String scientificNameOfFish, String images, String recommendation, Integer inSeason, String primaryImage) {
        nameOfFish = name;
        this.scientificNameOfFish = scientificNameOfFish;
        this.images = images;
        this.recommendation = recommendation;
        this.inSeason = inSeason;
        this.primaryImage = primaryImage;
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
