package com.verma.android.template;

import android.content.Context;

import androidx.annotation.NonNull;

import com.verma.android.common.AppConfig;
import com.verma.android.deps.DaggerDeps;
import com.verma.android.deps.module.AppModule;
import com.verma.android.deps.module.NetworkModule;
import com.verma.android.deps.service.SharedKey;
import com.verma.android.deps.service.SharedPreferencesService;

import javax.inject.Inject;

import timber.log.Timber;

public class App extends android.app.Application {

    @Inject
    public SharedPreferencesService sharedPreferencesService;
    public static String API_PATH = BuildConfig.API_PATH;
    public static String BASEURL = BuildConfig.BASEURL;
    private static App instance = null;


    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public static App from(@NonNull Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerDeps.builder()
                .networkModule(new NetworkModule(BASEURL))
                .appModule(new AppModule(App.from(this)))
                .build().inject(this);
        Timber.plant(new Timber.DebugTree());
        AppConfig.getInstance().init(this);


        setBaseURL();

        API_PATH = sharedPreferencesService.getString(SharedKey.KEY_PREF_API_PATH, BuildConfig.API_PATH);
        sharedPreferencesService.setString(SharedKey.KEY_PREF_API_PATH, API_PATH);

    }

    public void setBaseURL() {
        BASEURL = sharedPreferencesService.getString(SharedKey.KEY_PREF_IP_ADDRESS, BuildConfig.BASEURL);
        sharedPreferencesService.setString(SharedKey.KEY_PREF_IP_ADDRESS, BASEURL);
    }

    public Context getContext() {
        return this;
    }

    public boolean isFeaturesEnable(Context pContext, int features) {
        return pContext.getResources().getBoolean(features);
    }
}
