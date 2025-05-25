package com.sufiyan.socialscape.model;

public class Post {
    private int id;
    private int userId;
    private String title;
    private String platform;
    private String caption;
    private String date;
    private String time;
    private String mediaPath;
    private String mediaType;
    private String status;

    //Default constructor
    public Post() {}

    //Parameterized constructor
    public Post(int userId, String title, String platform, String caption, String date, String time, String mediaPath, String mediaType, String status) {
        this.userId = userId;
        this.title = title;
        this.platform = platform;
        this.caption = caption;
        this.date = date;
        this.time = time;
        this.mediaPath = mediaPath;
        this.mediaType = mediaType;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getMediaPath() {
        return mediaPath;
    }
    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
