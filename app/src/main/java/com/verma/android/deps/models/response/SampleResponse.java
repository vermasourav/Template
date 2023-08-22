package com.verma.android.deps.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SampleResponse {

    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public SampleResponse setToken(String token) {
        this.token = token;
        return this;
    }


}