package com.tanuja.agribuisness;

public class ImageDataSlider {

    // image url is used to
    // store the url of image
    private String imgUrl;

    // Constructor method.
    public ImageDataSlider(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    // Getter method
    public String getImgUrl() {
        return imgUrl;
    }

    // Setter method
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
