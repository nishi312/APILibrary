package com.apilibrary.demo.di.components;

import android.app.Application;
import android.content.Context;

import com.apilibrary.demo.App;
import com.apilibrary.demo.data.DataManager;
import com.apilibrary.demo.di.modules.app.AppModule;
import com.apilibrary.demo.di.modules.app.NetworkModule;
import com.apilibrary.demo.di.modules.contributor.ActivityContributorModule;
import com.apilibrary.demo.di.modules.contributor.FragmentContributorModule;
import com.apilibrary.demo.di.qualifiers.AppContext;
import com.apilibrary.demo.di.scopes.AppScope;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@AppScope
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, NetworkModule.class,
        ActivityContributorModule.class,
        FragmentContributorModule.class})
public interface AppComponent extends AndroidInjector<App> {
    @AppContext
    Context appContext();

    DataManager getDataManager();

    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
