package com.example.projectakhir.Model;

public class FavoriteModel {

    private String Title;
    private String Place;
    private int Image;

    public FavoriteModel(String title, String place, int image) {
        Title = title;
        Place = place;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPlace() {
        return Place;
    }
    public void setPlace(String place) {
        Place = place;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
