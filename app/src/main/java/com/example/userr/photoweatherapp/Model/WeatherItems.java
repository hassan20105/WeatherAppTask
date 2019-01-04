package com.example.userr.photoweatherapp.Model;

public class WeatherItems {
    int img;
    int fav_img;

    String placeName, temperature, description, windSpeed ,imageLink;
    boolean isLiked=false;


    public WeatherItems(int img, int fav_img, String placeName, String temperature, String description, String windSpeed) {
        this.img = img;
        this.fav_img = fav_img;
        this.placeName = placeName;
        this.temperature = temperature;
        this.description = description;
        this.windSpeed = windSpeed;
    }
    public WeatherItems(int img,  String placeName, String temperature, String description, String windSpeed,String imageLink) {
        this.img = img;
        this.placeName = placeName;
        this.temperature = temperature;
        this.description = description;
        this.windSpeed = windSpeed;
        this.imageLink = imageLink;

    }
    public boolean isLiked() {
        return isLiked;
    }
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getFav_img() {
        return fav_img;
    }

    public void setFav_img(int fav_img) {
        this.fav_img = fav_img;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
