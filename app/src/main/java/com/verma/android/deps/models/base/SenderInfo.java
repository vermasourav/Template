package com.verma.android.deps.models.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderInfo {

    @SerializedName("appVersion")
    @Expose
    private String appVersion;

    @SerializedName("sender")
    @Expose
    private String sender;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public SenderInfo withAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public SenderInfo withSender(String sender) {
        this.sender = sender;
        return this;
    }

}