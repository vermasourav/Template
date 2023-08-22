/*
 * *
 *  * Created by Sourav Kumar Verma on 23/2/20 8:57 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 23/2/20 8:57 PM
 *
 */

package com.verma.android.deps.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;


    @SerializedName("data")
    @Expose
    private String data;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public BaseResponse withError(Error error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BaseResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getData() {
        return data;
    }

    public BaseResponse setData(String data) {
        this.data = data;
        return this;
    }
}