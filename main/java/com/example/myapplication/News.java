package com.example.myapplication;

public class News {
    private String title;
    private String desc;
    private String publishTime;
    private String imageUrl;
    private String publishAccount;

    public News(String title,String desc,String publishTime,String imageUrl,String publishAccount){
        this.title = title;
        this.desc = desc;
        this.publishTime = publishTime;
        this.imageUrl = imageUrl;
        this.publishAccount = publishAccount;
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

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishAccount() {
        return publishAccount;
    }

    public void setPublishAccount(String publishAccount) {
        this.publishAccount = publishAccount;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishAccount='" + publishAccount + '\'' +
                '}';
    }
}
