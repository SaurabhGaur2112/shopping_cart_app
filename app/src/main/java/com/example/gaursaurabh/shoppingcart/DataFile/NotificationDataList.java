package com.example.gaursaurabh.shoppingcart.DataFile;

public class NotificationDataList {

    private String icon, title, desc, image;

    public NotificationDataList(String icon, String title, String desc, String image){
        this.icon = icon;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
