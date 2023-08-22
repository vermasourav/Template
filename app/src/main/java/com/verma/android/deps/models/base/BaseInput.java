package com.verma.android.deps.models.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.verma.android.deps.Constant;

public class BaseInput {
    @SerializedName("id")
    @Expose
    private String id = Constant.instance().getTransactionId();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseInput withId(String id) {
        this.id = id;
        return this;
    }


}
