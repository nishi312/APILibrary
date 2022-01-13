package com.apilibrary.demo.base.mvp;

import com.apilibrary.demo.data.DataManager;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MvpBasePresenterImpl<T extends BaseView> extends MvpBasePresenter<T> {

    @Inject
    public CompositeDisposable compositeDisposable;

    @Inject
    public DataManager dataManager;

    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }
}