package com.verma.android.deps.service;

import com.verma.android.deps.models.request.SampleRequest;

import rx.Subscription;

public interface INetworkCall {
    Subscription requestSample(final ICallBack.CallBack callBack, SampleRequest pRequest);

}