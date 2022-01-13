package com.apilibrary.demo.ui.presenter;


import com.apilibrary.demo.base.mvp.MvpBasePresenterImpl;
import com.apilibrary.demo.base.network.MyAppDisposableObserver;
import com.apilibrary.demo.data.DataManager;
import com.apilibrary.demo.data.api.models.nationalities.NationalityResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class MainPresenterImpl extends MvpBasePresenterImpl<MainContract.View>
        implements MainContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public MainPresenterImpl(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getAllNationalities() {
        ifViewAttached(view -> {
            view.showLoading(null);
            MyAppDisposableObserver<NationalityResponse> myAppDisposableObserver =
                    new MyAppDisposableObserver<NationalityResponse>(view) {
                        @Override
                        protected void onSuccess(Object response) {
                            view.hideLoading();
                            NationalityResponse nationalityResponse = (NationalityResponse) response;
                            if (nationalityResponse.isSuccess() && nationalityResponse.getData() != null) {
                                nationalityResponse.getData().getNationalities();
                                view.showResult(nationalityResponse.getData().getNationalities());
                            } else {
                                view.showApiFailureError(nationalityResponse.getMessage(), nationalityResponse.getStatus(), "");
                            }
                        }
                    };

            MyAppDisposableObserver<NationalityResponse> disposableObserver = dataManager.getAllNationalities()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(myAppDisposableObserver);
            compositeDisposable.add(disposableObserver);
        });
    }
}