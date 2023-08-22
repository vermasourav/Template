/*
 *
 * Created by VERMA Sourav on 30/1/21 7:49 PM.
 * Copyright © 2021. All rights reserved.
 * Last modified 23/1/21 9:33 PM
 *
 */

package com.verma.android.template;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SplashActivityTest extends BaseAction {
    @Rule
    public final GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(
            ACCESS_FINE_LOCATION,
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE,
            ACCESS_FINE_LOCATION
    );
    private final String TAG = getClass().getSimpleName();
    @Rule
    public ActivityTestRule<SplashActivity> activityRule = new ActivityTestRule<>(SplashActivity.class);

    @Override
    public String getTestName() {
        return getClass().getSimpleName();
    }

    @Before
    public void init() {
        super.init();
    }

    @Test
    public void splashTest() {
        takeScreenshot(getMethodName(0), 4);
    }
}
