package com.verma.android.deps.service;


import com.verma.android.deps.models.request.SampleRequest;
import com.verma.android.deps.models.response.BaseResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface NetworkService {

    //final String PATH = BuildConfig.API_PATH;
    public String PATH = "demo/api";
    final String API = "/V1";
    final String SLASH = "/";

    @GET(PATH + "/appconfigs")
    Observable<Object> appconfigs();

    @POST("{api_path}" + API + "/authenticate")
    Observable<BaseResponse> requestSample(@Path("api_path") String api_path, @Body SampleRequest body);


}
