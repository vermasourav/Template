package com.verma.android.template.ui.menu;

import static com.verma.android.template.App.BASEURL;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.verma.android.deps.DaggerDeps;
import com.verma.android.deps.module.AppModule;
import com.verma.android.deps.module.NetworkModule;
import com.verma.android.template.App;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public abstract class MenuBaseFragment extends Fragment {
    private static final String TAG = "MenuBaseFragment";
    public abstract String getScreenName();
    public abstract void initComponent();
    public abstract void setOptionMenu(boolean hasMenu );
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDeps.builder()
                .networkModule(new NetworkModule(BASEURL))
                .appModule(new AppModule(App.from(getContext())))
                .build().inject(this);
        setAnalytics();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponent();
    }

    public  void setOptionMenu(boolean hasMenu, MenuProvider menuProvider){
        if(hasMenu && null != menuProvider){
            MenuHost menuHost = requireActivity();
            menuHost.addMenuProvider(menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        }
    }


    private void setAnalytics() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, getScreenName());
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, super.getClass().getSimpleName());
        //TODO Enable it
        //FirebaseAnalytics.getInstance(requireContext()).logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
        Log.d(TAG, "setAnalytics: getScreenName - "+ getScreenName() + " Classname " +super.getClass().getSimpleName());

    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Timber.d("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onContextItemSelected(@NotNull MenuItem item) {
        Timber.d("onContextItemSelected");
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Timber.d("onOptionsItemSelected");
        return super.onOptionsItemSelected(item);
    }

    protected void setTitle(String pTitle) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(null != actionBar && !TextUtils.isEmpty(pTitle)){
            actionBar.setTitle(pTitle);
        }
    }

    protected void setSubtitle(String pSubtitle) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(null != actionBar && !TextUtils.isEmpty(pSubtitle)){
            actionBar.setSubtitle(pSubtitle);
        }
    }

}
