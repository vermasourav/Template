package com.verma.android.deps;

import com.verma.android.deps.module.AppModule;
import com.verma.android.deps.module.NetworkModule;
import com.verma.android.template.App;
import com.verma.android.template.ui.menu.MainActivity;
import com.verma.android.template.ui.menu.MenuBaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})

public interface Deps {
    void inject(App application);

    void inject(MainActivity mainActivity);

    void inject(MenuBaseFragment baseFragment);
}
