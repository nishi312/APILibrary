package com.apilibrary.demo.di.modules.app;

import android.app.Application;
import android.content.Context;


import com.apilibrary.demo.di.qualifiers.AppContext;
import com.apilibrary.demo.di.scopes.AppScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppContextModule {

    @Binds
    @AppContext
    @AppScope
    public abstract Context bindContext(Application application);
}
