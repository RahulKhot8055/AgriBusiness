package com.tanuja.agribuisness;

public class ProductModels {

    String image;
    String Name;
    String Price;

    public ProductModels(String image, String name, String price) {
        this.image = image;
        Name = name;
        Price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public ProductModels() {
    }


}
