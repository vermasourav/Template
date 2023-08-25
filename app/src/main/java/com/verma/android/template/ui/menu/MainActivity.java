package com.verma.android.template.ui.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.verma.android.common.AppConfig;
import com.verma.android.template.App;
import com.verma.android.template.BuildConfig;
import com.verma.android.template.MobileAdsManager;
import com.verma.android.template.R;
import com.verma.android.template.ui.rate.AppRate;
import com.verma.android.template.ui.rate.StoreType;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private ActionBarDrawerToggle aToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        try {
            setSupportActionBar(toolbar);
            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show());
           // fab.setVisibility(View.GONE);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
            //navigationView.getHeaderView(0).setVisibility(View.GONE);

            aToggle = new ActionBarDrawerToggle(this, drawer, toolbar, android.R.string.ok, android.R.string.ok);
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home,
                    R.id.nav_one,
                    R.id.nav_two,
                    R.id.nav_three,
                    R.id.nav_four,
                    R.id.nav_five,
                    R.id.nav_six,
                    R.id.nav_setting,
                    R.id.nav_faq,
                    R.id.nav_privacy_policy,
                    R.id.nav_terms_of_service,
                    R.id.nav_item_version,
                    R.id.nav_about_us
            )
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);

            initAds();
           // setupBackPress();
        } catch (Exception e) {
            //DO Nothing
            Timber.d("onCreate: ");
        }

    }

    private void shareMe() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //Do Nothing
        }
    }

    private void initAds() {
       // MobileAdsManager.getInstance().initAds(this, findViewById(R.id.adView));
        //MobileAdsManager.getInstance().showAds(findViewById(R.id.adView));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        menu.findItem(R.id.action_rate_us).setVisible(AppConfig.getInstance().featureRateUs);
        menu.findItem(R.id.action_share_me).setVisible(AppConfig.getInstance().featureShareMenu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (aToggle.onOptionsItemSelected(item)) {
            if (item.getItemId() == R.id.nav_four) {
                shareMe();
                return false;
            }
            return true;
        } else {
            if(item.getItemId() == R.id.action_rate_us){
                addRateMe();
                return true;
            }else if(item.getItemId() == R.id.action_share_me){
                shareMe();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayVersion();
        setupMenu();
    }

    private void displayVersion() {
        String version = getVersion();
        SpannableString spanString = new SpannableString(version);

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, android.R.color.holo_red_light));
        spanString.setSpan(foregroundColorSpan, 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_item_version).setTitle(spanString);

    }

    public void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception ignored) {
            //DO Nothing
        }
    }

    private String getVersion() {
        try {
            String verName = BuildConfig.VERSION_NAME;
            return getString(R.string.app_name) + " Version " + " - " + verName;
        } catch (Exception e) {
            return "";
        }
    }

    public void setupMenu() {
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_one).setVisible(AppConfig.getInstance().featureMenuOne);
        menu.findItem(R.id.nav_two).setVisible(AppConfig.getInstance().featureMenuTwo);
        menu.findItem(R.id.nav_three).setVisible(AppConfig.getInstance().featureMenuThree);
        menu.findItem(R.id.nav_four).setVisible(AppConfig.getInstance().featureMenuFour);
        menu.findItem(R.id.nav_five).setVisible(AppConfig.getInstance().featureMenuFive);
        menu.findItem(R.id.nav_six).setVisible(AppConfig.getInstance().featureMenuSix);
        menu.findItem(R.id.nav_setting).setVisible(AppConfig.getInstance().featureMenuSetting);
        menu.findItem(R.id.nav_about_us).setVisible(AppConfig.getInstance().featureMenuAboutUs);
        menu.findItem(R.id.nav_faq).setVisible(AppConfig.getInstance().featureMenuFaq);
        menu.findItem(R.id.nav_privacy_policy).setVisible(AppConfig.getInstance().featureMenuPrivacy);
        menu.findItem(R.id.nav_terms_of_service).setVisible(AppConfig.getInstance().featureMenuTermsOfService);
        menu.findItem(R.id.nav_item_version).setVisible(AppConfig.getInstance().featureMenuVersion);

    }

    private void addRateMe() {
        AppRate.with(this)
                .setStoreType(StoreType.GOOGLEPLAY) //default is Google, other option is Amazon
                .setInstallDays(2) // default 10, 0 means install day.
                .setLaunchTimes(10) // default 10 times.
                .setRemindInterval(2) // default 1 day.
                .setShowLaterButton(true) // default true.
                .setDebug(true) // default false.
                .setCancelable(false) // default false.
                .setOnClickButtonListener(which -> Timber.d("addRateMe: " + which))
                .setTitle(R.string.new_rate_dialog_title)
                .setTextLater(R.string.new_rate_dialog_later)
                .setTextNever(R.string.new_rate_dialog_never)
                .setTextRateNow(R.string.new_rate_dialog_ok)
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
    }

    public void displayMessage(String pMessage) {
        Snackbar.make(
                findViewById(R.id.fab),
                pMessage,
                Snackbar.LENGTH_SHORT
        ).show();

    }


    public void setupBackPress(){
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Back is pressed... Finishing the activity
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.text_exit_request);
                builder.setCancelable(false)
                        .setPositiveButton(getString(android.R.string.ok), (dialog, which) -> {
                                    finish();
                                }
                        )
                        .setNegativeButton(getString(android.R.string.no), null);
                builder.show();
            }
        });
    }
    public Context getContext(){
        return this;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.text_exit_request);
        builder.setCancelable(false)
                .setPositiveButton(getString(android.R.string.ok),
                        (dialog, which) -> {
                            finish();
                        }
                )
                .setNegativeButton(getString(android.R.string.no), null);
        //builder.show();
        super.onBackPressed();
    }
}
