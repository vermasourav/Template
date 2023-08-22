package com.verma.android.deps.service;

import android.util.Log;

import com.verma.android.deps.models.request.SampleRequest;
import com.verma.android.deps.models.response.BaseResponse;
import com.verma.android.template.App;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CommonService implements INetworkCall {
    private static final String TAG = "CommonService";
    private final NetworkService networkService;
    private SharedPreferencesService sharedPreferencesService;

    public CommonService(NetworkService networkService, SharedPreferencesService sharedPreferencesService) {
        this.networkService = networkService;
        this.sharedPreferencesService = sharedPreferencesService;
    }

    public Subscription requestSample(final ICallBack.CallBack callback, SampleRequest sampleRequest) {
        Log.d(TAG, "requestAuthenticate: " + sampleRequest.toString());
        return networkService.requestSample(App.getInstance().API_PATH, sampleRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable::error)
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {
                        //DO Nothing
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(BaseResponse serverResponse) {
                        if (null == serverResponse.getStatus()) {
                            callback.onError(new NetworkError(new Throwable(serverResponse.getMessage())));
                        } else {
                            if (200 == serverResponse.getStatus()) {
                                callback.onSuccess(serverResponse);
                            } else {
                                callback.onError(new NetworkError(new Throwable(serverResponse.getError().getErrorMessage())));

                            }
                        }
                    }
                });
    }
}
