package com.verma.android.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.verma.android.common.AppConfig;
import com.verma.android.deps.service.SharedKey;
import com.verma.android.onboarding.OnBoardingActivity;
import com.verma.android.template.databinding.ActivitySplashBinding;
import com.verma.android.template.ui.menu.MainActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity implements SharedKey {
    private int splashTimeOut = 1000;

    //Animations
    Animation topAnimantion;
    Animation bottomAnimation;
    Animation middleAnimation;

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initSplash();
        String verName = "Version. " + " - " + BuildConfig.VERSION_NAME;
        binding.appVersion.setText(verName);
        Glide.with(this).load(R.drawable.dot_loading).into(binding.process);

        if (AppConfig.featureSplash) {
            splashTimeOut = getResources().getInteger(R.integer.splash_time_out);
            ActionBar actionBar = getSupportActionBar();
            if (null != actionBar) {
                actionBar.hide();
            }

            ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
            Runnable runnable = this::moveNext;
            worker.schedule(runnable, splashTimeOut, TimeUnit.MILLISECONDS);
        } else {
            moveNext();
        }
    }

    private void initSplash() {
        //Animation Calls
        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_middle_animation);
        //-----------Setting Animations to the elements of SplashScreen-------- -
        binding.fifthLine.setAnimation(topAnimantion);
        binding.secondLine.setAnimation(topAnimantion);
        binding.thirdLine.setAnimation(topAnimantion);
        binding.fifthLine.setAnimation(topAnimantion);
        binding.fifthLine.setAnimation(topAnimantion);
        binding.sixthLine.setAnimation(topAnimantion);
        binding.sevenLine.setAnimation(topAnimantion);
        binding.appTitle.setAnimation(middleAnimation);
        binding.appVersion.setAnimation(bottomAnimation);

        binding.appSubtitle.setAnimation(topAnimantion);


    }

    private void moveNext(){
        if (AppConfig.featureOnboarding) {
            boolean isFirstLaunch = ((App) getApplication()).sharedPreferencesService.getBoolean(SharedKey.KEY_BOOLEAN_SETTING_KEEP_ME_LOGIN,true);
            if(isFirstLaunch){
                startOnboarding();
            }else{
                close();
            }
        }else{
            close();
        }
    }

    private void close() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void startOnboarding(){
        Intent intent = new Intent(this, OnBoardingActivity.class);
        onBoardingLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> onBoardingLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    ((App) getApplication()).sharedPreferencesService.setBoolean(SharedKey.KEY_BOOLEAN_SETTING_KEEP_ME_LOGIN,false);
                    //Do Task OnBoarding Done
                    Intent data = result.getData();
                    close();
                }
            });
}