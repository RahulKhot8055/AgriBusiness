package com.tanuja.agribuisness;

public class VegModel {

    String Firstname;
    String Lastname;
    String image;

    public VegModel() {
    }

    public VegModel(String firstname, String lastname, String image) {
        Firstname = firstname;
        Lastname = lastname;
        this.image = image;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
