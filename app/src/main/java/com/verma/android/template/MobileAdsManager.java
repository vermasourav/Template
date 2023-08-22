/*
 *
 * Created by VERMA Sourav on 30/1/21 7:49 PM.
 * Copyright © 2021. All rights reserved.
 * Last modified 30/1/21 7:42 PM
 *
 */

package com.verma.android.template;

import android.content.Context;

import com.verma.android.common.AppConfig;

import java.util.Map;

import timber.log.Timber;


public class MobileAdsManager {
    private static final String TAG = "MobileAdsManager";
    private static MobileAdsManager instance = null;
   // private AdView adView;
   // private AdRequest adRequest;

    public static MobileAdsManager getInstance() {
        if (null == instance) {
            instance = new MobileAdsManager();
        }
        return instance;
    }

/*
    public void showAds(AdView pView) {
        if (null != pView && AppConfig.getInstance().featureAds) {
            if (null == adRequest) {
                initAdRequest();
            }
            adView.loadAd(adRequest);
        }
    }

    public void initAds(Context pContext, AdView pView) {
        if (!AppConfig.featureAds) {
            return;
        }

        MobileAds.initialize(pContext, initializationStatus -> {
            Timber.tag(TAG).d("initialize: ");
            Map<String, AdapterStatus> map = initializationStatus.getAdapterStatusMap();
            for (Map.Entry<String, AdapterStatus> entry : map.entrySet()) {
                AdapterStatus adapterStatus = entry.getValue();
                AdapterStatus.State state = adapterStatus.getInitializationState();
                Timber.tag(TAG).d("key = " + entry.getKey() + ", state = " + state.name() + ", desc = " + adapterStatus.getDescription());
            }
        });
        adView = pView;
        initAdRequest();
        adView.loadAd(adRequest);
    }

    private void initAdRequest() {
        adRequest = new AdRequest.Builder().build();
    }*/
}
