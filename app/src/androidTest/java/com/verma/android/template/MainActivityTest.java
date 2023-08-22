/*
 *
 * Created by VERMA Sourav on 30/1/21 7:49 PM.
 * Copyright © 2021. All rights reserved.
 * Last modified 23/1/21 9:33 PM
 *
 */

package com.verma.android.template;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.contrib.DrawerMatchers.isOpen;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.Gravity;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.verma.android.template.ui.menu.MainActivity;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest extends BaseAction {
  /*  @Rule
    public final GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(
            ACCESS_FINE_LOCATION,
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE,
            ACCESS_FINE_LOCATION
    );*/
    private final String TAG = getClass().getSimpleName();
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Override
    public String getTestName() {
        return getClass().getSimpleName();
    }

    @Before
    public void init() {
        super.init();
    }

    @Test
    public void homeTest() {
        takeScreenshot(getMethodName(0), 2);
    }

    @Test
    public void navigationTest() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(DrawerActions.open());
        takeScreenshot(getMethodName(0) + "_0", 1);
        onView(withId(R.id.drawer_layout)).check(matches(isOpen(Gravity.LEFT))).perform(DrawerActions.close());


        // Start the screen of your activity.
        openTab(R.id.nav_home, context.getString(R.string.menu_nav_home));
        if (context.getResources().getBoolean(R.bool.feature_menu_one)) {
            openTab(R.id.nav_one, context.getString(R.string.menu_nav_one));
        }
        if (context.getResources().getBoolean(R.bool.feature_menu_two)) {
            openTab(R.id.nav_two, context.getString(R.string.menu_nav_two));
        }
        if (context.getResources().getBoolean(R.bool.feature_menu_three)) {
            openTab(R.id.nav_three, context.getString(R.string.menu_nav_three));
        }

        if (context.getResources().getBoolean(R.bool.feature_menu_four)) {
            openTab(R.id.nav_four, context.getString(R.string.menu_nav_four));
        }

        if (context.getResources().getBoolean(R.bool.feature_menu_five)) {
            openTab(R.id.nav_five, context.getString(R.string.menu_nav_five));
        }

        if (context.getResources().getBoolean(R.bool.feature_menu_six)) {
            openTab(R.id.nav_six, context.getString(R.string.menu_nav_six));
        }
        if (context.getResources().getBoolean(R.bool.feature_menu_setting)) {
            openTab(R.id.nav_setting, context.getString(R.string.menu_nav_setting));
        }

        if (context.getResources().getBoolean(R.bool.feature_menu_faq)) {
            openTab(R.id.nav_faq, context.getString(R.string.menu_nav_faq),true);
        }

        if (context.getResources().getBoolean(R.bool.feature_menu_privacy)) {
            openTab(R.id.nav_privacy_policy, context.getString(R.string.menu_nav_privacy_policy),true);
        }

        if (context.getResources().getBoolean(R.bool.feature_menu_terms_of_service)) {
            openTab(R.id.nav_terms_of_service, context.getString(R.string.menu_nav_terms_of_service),true);
        }

        if (context.getResources().getBoolean(R.bool.feature_menu_about_us)) {
            openTab(R.id.nav_about_us, context.getString(R.string.menu_nav_about_us), true);
        }

    }


    private void openTab(int nav_id, String pName) {
        openTab(nav_id,pName,false);
    }
    private void openTab(int nav_id, String pName, boolean scroll) {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(DrawerActions.open());
        if(scroll){
            onView(withId(R.id.drawer_layout)).perform(ViewActions.swipeUp());
        }
        onView(withId(nav_id)).perform(click());
        takeScreenshot(getMethodName(3) + "_" + pName, 2);
    }
}
