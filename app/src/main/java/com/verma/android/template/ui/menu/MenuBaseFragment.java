package com.verma.android.template.ui.menu;

import static com.verma.android.template.App.BASEURL;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.verma.android.deps.DaggerDeps;
import com.verma.android.deps.module.AppModule;
import com.verma.android.deps.module.NetworkModule;
import com.verma.android.template.App;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class MenuBaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDeps.builder()
                .networkModule(new NetworkModule(BASEURL))
                .appModule(new AppModule(App.from(getContext())))
                .build().inject(this);
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
