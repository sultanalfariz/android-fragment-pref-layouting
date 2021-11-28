package com.example.projectakhir.Model;

public class WisataModel {

    private String Title;
    private String Place;
    private String Description;
    private int Image;

    public WisataModel(String title, String place, String description, int image) {
        Title = title;
        Place = place;
        Description = description;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public String getPlace() {
        return Place;
    }

    public String getDescription() {
        return Description;
    }

    public int getImage() {
        return Image;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImage(int image) {
        Image = image;
    }
}
