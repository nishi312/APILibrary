package com.apilibrary.demo.di.modules.contributor;

import com.apilibrary.demo.di.modules.MainActivityModule;
import com.apilibrary.demo.di.scopes.ActivityScope;
import com.apilibrary.demo.ui.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract public class ActivityContributorModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    @ActivityScope
    public abstract MainActivity mainActivity();
}
