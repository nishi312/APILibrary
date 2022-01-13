package com.apilibrary.demo.base.mvp;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface BaseView extends MvpView {
    void clientError(String errorMessage);

    void hideLoading();

    void connectionError(String errorMessage);

    void onTimeout();

    void serverError(String errorMessage);

    void unauthenticated();

    void unexpectedError(String errorMessage);

    void unexpectedError(RuntimeException exception);

    void showLoading(String message);

    void gotoAppUnderMaintain();

    void sessionTokenExpired();

    void localValidationError(String title, String desc);

    void showApiFailureError(String message,String status,String useCase);

    void showApiSuccessMessage(String message,String status,String useCase);

}
