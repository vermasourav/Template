/*
 * *
 *  * Created by Sourav Kumar Verma on 23/2/20 8:56 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 23/2/20 8:56 PM
 *
 */

package com.verma.android.deps.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("errorNo")
    @Expose
    private String errorNo;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public Error withErrorNo(String errorNo) {
        this.errorNo = errorNo;
        return this;
    }

}