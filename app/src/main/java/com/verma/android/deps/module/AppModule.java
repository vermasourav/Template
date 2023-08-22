package com.verma.android.deps.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.verma.android.deps.service.CommonService;
import com.verma.android.deps.service.NetworkService;
import com.verma.android.deps.service.SharedPreferencesService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public CommonService providesCommonService(NetworkService networkService, SharedPreferencesService sharedPreferencesService) {
        return new CommonService(networkService, sharedPreferencesService);
    }

    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    public SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
