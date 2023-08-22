package com.verma.android.template.ui.menu.aboutus.models;

import com.google.gson.annotations.SerializedName;

public class Member {
    @SerializedName("imageUrl")
    String imageUrl;
    @SerializedName("name")
    String name;
    @SerializedName("post")
    String post;
    @SerializedName("contactUrl")
    String contactUrl;

    public Member() {
        //Do Nothing
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

}