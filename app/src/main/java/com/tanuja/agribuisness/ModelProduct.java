package com.tanuja.agribuisness;



public class ModelProduct
{
    private String productId,productTitle,productDescription,productcategory, producQuantity,productIcon,
            originalPrice,discountNote,discountPrice,discountAvailabel,timetamp,uid;

    public ModelProduct(String productId,String productTitle,String productDescription,String productcategory,
                        String producQuantity,String productIcon,String originalPrice,String discountNote,
                        String discountPrice,String discountAvailabel,String timetamp,String uid
    ){
        this.productId= productId;
        this.productTitle=productTitle;
        this.productDescription=productDescription;
        this.productcategory=productcategory;
        this.producQuantity=producQuantity;
        this.productIcon=productIcon;
        this.originalPrice=originalPrice;
        this.discountNote=discountNote;
        this.discountPrice=discountPrice;
        this.discountAvailabel=discountAvailabel;
        this.timetamp=timetamp;
        this.uid=uid;



    }

    public String getProductId() {

        return productId;
    }

    public void setProductId(String productId) {

        this.productId = productId;
    }

    public String getProductTitle() {

        return productTitle;
    }

    public void setProductTitle(String productTitle) {

        this.productTitle = productTitle;
    }

    public String getProductDescription() {

        return productDescription;
    }

    public void setProductDescription(String productDescription) {

        this.productDescription = productDescription;
    }

    public String getProductcategory() {

        return productcategory;
    }

    public void setProductcategory(String productcategory) {

        this.productcategory = productcategory;
    }

    public String getProducQuantity() {
        return producQuantity;
    }

    public void setProducQuantity(String producQuantity) {
        this.producQuantity = producQuantity;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDiscountNote() {
        return discountNote;
    }

    public void setDiscountNote(String discountNote) {
        this.discountNote = discountNote;
    }

    public String getDiscountAvailabel() {
        return discountAvailabel;
    }

    public void setDiscountAvailabel(String discountAvailabel) {
        this.discountAvailabel = discountAvailabel;
    }

    public String getTimetamp() {
        return timetamp;
    }

    public void setTimetamp(String timetamp) {
        this.timetamp = timetamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDiscountPrice() {
        return null;
    }
}
