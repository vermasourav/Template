package com.verma.android.deps.service;


import com.verma.android.deps.models.response.BaseResponse;

public class ICallBack {

    public interface CallBack extends ErrorCallBack {
        void onSuccess(BaseResponse pResponse);
    }

    public interface ErrorCallBack {
        void onError(NetworkError networkError);

        void onFail(NetworkError networkError);
    }

    public interface SuccessCallBack {
        void onSuccess(boolean isSuccess, String pMessage);
    }

}
