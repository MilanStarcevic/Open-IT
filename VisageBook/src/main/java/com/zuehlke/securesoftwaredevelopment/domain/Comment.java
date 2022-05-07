package com.zuehlke.securesoftwaredevelopment.domain;

public class Comment {
    private int postId;
    private Integer userId;
    private String comment;

    public Comment() {
    }

    public Comment(int carId, Integer userId, String comment) {
        this.postId = carId;
        this.userId = userId;
        this.comment = comment;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
