package com.zuehlke.securesoftwaredevelopment.domain;

public class Post {
    private Integer id;
    private String picture;
    private String text;

    public Post(Integer id, String picture, String text) {
        this.id = id;
        this.picture = picture;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
