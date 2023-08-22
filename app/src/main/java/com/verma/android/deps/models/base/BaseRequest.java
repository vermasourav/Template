/*
 * *
 *  * Created by Sourav Kumar Verma on 23/2/20 8:45 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 23/2/20 8:44 PM
 *
 */

package com.verma.android.deps.models.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseRequest {
    @SerializedName("senderInfo")
    @Expose
    private SenderInfo senderInfo;

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public BaseRequest withSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
        return this;
    }


}



