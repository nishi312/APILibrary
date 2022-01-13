package com.apilibrary.demo.di.modules;


import com.apilibrary.demo.di.modules.app.CompositeDisposableModule;
import com.apilibrary.demo.di.scopes.ActivityScope;
import com.apilibrary.demo.ui.presenter.MainContract;
import com.apilibrary.demo.ui.presenter.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module(includes = {CompositeDisposableModule.class})
public final class MainActivityModule {

    @Provides
    @ActivityScope
    public MainContract.Presenter verifyAmountPresenter(MainPresenterImpl verifyAmountPresenter) {
        return verifyAmountPresenter;
    }
}