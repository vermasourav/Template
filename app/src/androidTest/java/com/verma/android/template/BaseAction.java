/*
 * Created by V3RMA Sourav on 24/05/23, 11:31 pm
 * file=${fileName}.java
 * Copyright © 2023 All rights reserved
 * Last modified 24/05/23, 11:31 pm
 * Location Bangalore
 */

package com.verma.android.template;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;

abstract class BaseAction {
    private static final String TAG = "BaseAction";
    public Context context;
    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            Log.e(TAG, "takeScreenshot: " + e);
            takeScreenshot(description.getMethodName() + "_failed");
        }

        @Override
        protected void succeeded(Description description) {
            super.succeeded(description);
        }
    };

    abstract String getTestName();

    public void init() {
        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());
        context = getInstrumentation().getTargetContext().getApplicationContext();
        //MockitoAnnotations.initMocks(this);
    }

    public void takeScreenshot(String pName, int pDelay) {
        waitForSeconds(pDelay);
        takeScreenshot(pName);
    }

    public void takeScreenshot(String pName) {
        try {

            if (!TextUtils.isEmpty(pName)) {
                String fullName = getTestName() + "_" + pName;
                fullName = fullName.replaceAll("[^\\w]", "");
                Log.d(TAG, "takeScreenshot: " + fullName);
                Screengrab.screenshot(fullName);
            }
        } catch (Exception e) {
            Log.e(TAG, "takeScreenshot: " + e);
        }
    }

    protected void waitForSeconds(int pDelay) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(pDelay));
        } catch (Exception e) {
            //Do Nothing
        }
    }

    public String getMethodName(int at) {
        if (at == 0) {
            at = 2;
        }
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[at]
                .getMethodName();
        Log.d(TAG, "getMethodName: "+ nameOfCurrMethod);
        return nameOfCurrMethod;
    }

}
