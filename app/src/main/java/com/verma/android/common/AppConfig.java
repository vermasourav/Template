package com.verma.android.common;

import android.content.Context;
import android.util.Log;

import com.verma.android.template.R;

public class AppConfig {

    private static final AppConfig ourInstance = new AppConfig();
    private static final String TAG = "AppConfig";
    public static boolean featureSplash;
    public static boolean featureOnboarding;
    public static boolean featureAds;
    public boolean featureRateUs;
    public boolean featureShareMenu;

    public boolean featureMenuOne;
    public boolean featureMenuTwo;
    public boolean featureMenuThree;
    public boolean featureMenuFour;
    public boolean featureMenuFive;
    public boolean featureMenuAboutUs;
    public boolean featureMenuSix;
    public boolean featureMenuSetting;
    public boolean featureMenuFaq;
    public boolean featureMenuPrivacy;
    public boolean featureMenuTermsOfService;
    public boolean featureMenuVersion;

    private AppConfig() {
        //Do Nothing
        Log.d(TAG, "AppConfig: ");
    }

    public static AppConfig getInstance() {
        return ourInstance;
    }

    public void init(Context pContext) {
        Utils.getInstance().printHashKey(pContext);
        featureAds = isFeaturesEnable(pContext, R.bool.feature_ads);
        featureSplash = isFeaturesEnable(pContext, R.bool.feature_splash);
        featureOnboarding = isFeaturesEnable(pContext, R.bool.feature_onboarding);
        featureRateUs = isFeaturesEnable(pContext, R.bool.feature_rate_us);
        featureShareMenu = isFeaturesEnable(pContext, R.bool.feature_share_menu);

        featureMenuOne = isFeaturesEnable(pContext, R.bool.feature_menu_one);
        featureMenuTwo = isFeaturesEnable(pContext, R.bool.feature_menu_two);
        featureMenuThree = isFeaturesEnable(pContext, R.bool.feature_menu_three);
        featureMenuFour = isFeaturesEnable(pContext, R.bool.feature_menu_four);
        featureMenuFive = isFeaturesEnable(pContext, R.bool.feature_menu_five);
        featureMenuSix = isFeaturesEnable(pContext, R.bool.feature_menu_six);
        featureMenuSetting = isFeaturesEnable(pContext, R.bool.feature_menu_setting);
        featureMenuAboutUs = isFeaturesEnable(pContext, R.bool.feature_menu_about_us);
        featureMenuFaq = isFeaturesEnable(pContext, R.bool.feature_menu_faq);
        featureMenuPrivacy = isFeaturesEnable(pContext, R.bool.feature_menu_privacy);
        featureMenuTermsOfService = isFeaturesEnable(pContext, R.bool.feature_menu_terms_of_service);
        featureMenuVersion = isFeaturesEnable(pContext, R.bool.feature_menu_version);

        if(Utils.IS_DEBUG){
            Log.d(TAG, "AppConfig: "+ this.toString());
        }

    }

    public boolean isFeaturesEnable(Context pContext, int features) {
        return pContext.getResources().getBoolean(features);
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "featureRateUs=" + featureRateUs +
                ", featureShareMenu=" + featureShareMenu +
                ", featureMenuOne=" + featureMenuOne +
                ", featureMenuTwo=" + featureMenuTwo +
                ", featureMenuThree=" + featureMenuThree +
                ", featureMenuFour=" + featureMenuFour +
                ", featureMenuFive=" + featureMenuFive +
                ", featureMenuAboutUs=" + featureMenuAboutUs +
                ", featureMenuSix=" + featureMenuSix +
                ", featureMenuSetting=" + featureMenuSetting +
                ", featureMenuFaq=" + featureMenuFaq +
                ", featureMenuPrivacy=" + featureMenuPrivacy +
                ", featureMenuTermsOfService=" + featureMenuTermsOfService +
                ", featureMenuVersion=" + featureMenuVersion +
                '}';
    }
}
