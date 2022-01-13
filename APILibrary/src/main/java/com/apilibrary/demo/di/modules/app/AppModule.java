package com.apilibrary.demo.di.modules.app;

import android.app.Application;
import android.content.Context;


import com.apilibrary.demo.data.DataManager;
import com.apilibrary.demo.data.DataManagerImpl;
import com.apilibrary.demo.data.api.ApiHelper;
import com.apilibrary.demo.data.api.ApiHelperImpl;
import com.apilibrary.demo.data.preference.PrefHelper;
import com.apilibrary.demo.data.preference.PrefHelperImpl;
import com.apilibrary.demo.di.scopes.AppScope;

import dagger.Binds;
import dagger.Module;

@Module(includes = {AppContextModule.class})
abstract public class AppModule {

    @Binds
    @AppScope
    abstract Context provideContext(Application application);

    @Binds
    @AppScope
    public abstract DataManager dataManager(DataManagerImpl dataManagerImpl);

    @Binds
    @AppScope
    public abstract PrefHelper preferenceHelper(PrefHelperImpl prefHelperImpl);

    @Binds
    @AppScope
    public abstract ApiHelper apiHelper(ApiHelperImpl apiHelperImpl);
}
