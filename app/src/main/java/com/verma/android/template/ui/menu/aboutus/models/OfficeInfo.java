package com.verma.android.template.ui.menu.aboutus.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OfficeInfo {

    @SerializedName("officeLogoUrl")
    String officeLogoUrl;
    @SerializedName("googlePlayUrl")
    String googlePlayUrl;
    @SerializedName("facebookPageUrl")
    String facebookPageUrl;
    @SerializedName("facebookPageID")
    String facebookPageID;
    @SerializedName("groupUrl")
    String groupUrl;
    @SerializedName("youtubeUrl")
    String youtubeUrl;
    @SerializedName("githubUrl")
    String githubUrl;
    @SerializedName("webUrl")
    String webUrl;
    @SerializedName("members")
    ArrayList<Member> members;

    public OfficeInfo() {
        //Do Nothing
    }

    public String getOfficeLogoUrl() {
        return officeLogoUrl;
    }

    public void setOfficeLogoUrl(String officeLogoUrl) {
        this.officeLogoUrl = officeLogoUrl;
    }

    public String getGooglePlayUrl() {
        return googlePlayUrl;
    }

    public void setGooglePlayUrl(String googlePlayUrl) {
        this.googlePlayUrl = googlePlayUrl;
    }

    public String getFacebookPageUrl() {
        return facebookPageUrl;
    }

    public void setFacebookPageUrl(String facebookPageUrl) {
        this.facebookPageUrl = facebookPageUrl;
    }

    public String getFacebookPageID() {
        return facebookPageID;
    }

    public void setFacebookPageID(String facebookPageID) {
        this.facebookPageID = facebookPageID;
    }

    public String getGroupUrl() {
        return groupUrl;
    }

    public void setGroupUrl(String groupUrl) {
        this.groupUrl = groupUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}