package com.apilibrary.demo.base.network;

import static com.apilibrary.demo.utils.Utilities.getStringFromErrorBody;

import android.text.TextUtils;

import com.apilibrary.demo.App;
import com.apilibrary.demo.base.mvp.BaseView;
import com.apilibrary.demo.data.api.models.MessageBase;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import timber.log.Timber;


public abstract class MyAppDisposableObserver<T> extends DisposableObserver {

    private final BaseView view;
    private String userCase;
    private MyAppWrappedError wrappedError;

    public MyAppDisposableObserver(BaseView view) {
        this.view = view;
    }

    public MyAppDisposableObserver(BaseView view, String userCase) {
        this.view = view;
        this.userCase = userCase;
    }

    public void onError(Throwable throwable) {
        if (view != null) {
            view.hideLoading();
        }
        Timber.e("onError: %s", throwable.toString());
        if (throwable instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            String errorString = null;
            try {
                errorString = getStringFromErrorBody(responseBody.bytes()).toString();
                wrappedError = (new Gson()).fromJson(errorString, MyAppWrappedError.class);
                wrappedError.setError(errorString);
                int code = ((HttpException) throwable).code();
                if (code == 401) {
                    assert view != null;
                    view.unauthenticated();
                }  else {
                     if (code >= 400 && code <= 499) {
                        assert view != null;
                        String messageToDisplay = "";
                        String messageData = new Gson().toJson(wrappedError.getMessage());
                        String messageString = null;
                        try {
                            messageString = new Gson().fromJson(messageData, String.class);
                        } catch (Exception e) {
                            Timber.d("Exception : " + e.toString());
                        }
                        if (messageString != null) {
                            messageToDisplay = messageString;
                        } else {
                            MessageBase messageBase = new Gson().fromJson(messageData, MessageBase.class);
                            messageToDisplay = messageBase.getMessage();
                        }
                        view.showApiFailureError(messageToDisplay, "", "");
                        return;
                    }
                    if (code == 500) {
                        assert view != null;
                        String messageToDisplay = "";
                        String messageData = new Gson().toJson(wrappedError.getMessage());
                        String status = new Gson().toJson(wrappedError.getStatus());
                        String messageString = null;
                        try {
                            messageString = new Gson().fromJson(messageData, String.class);
                        } catch (Exception e) {
                            Timber.d("Exception : " + e.toString());
                        }
                        if (messageString != null) {
                            messageToDisplay = messageString;
                        } else {
                            MessageBase messageBase = new Gson().fromJson(messageData, MessageBase.class);
                            messageToDisplay = messageBase.getMessage();
                        }
                        view.showApiFailureError(messageToDisplay, status, userCase);
                        return;
                    }
                    assert view != null;
                    view.showApiFailureError(throwable.toString(), "", "");
                }
            } catch (Exception e) {
                Timber.d("Exception : " + e.toString());
            }

        } else if (throwable instanceof ConnectException) {
            assert view != null;
            view.showApiFailureError(throwable.toString(), "", "");
        } else if (throwable instanceof SocketTimeoutException) {
            assert view != null;
            view.onTimeout();
        } else if (throwable instanceof SSLHandshakeException) {
            assert view != null;
            Timber.d("throwable code== " + throwable.getMessage());
            try {
                view.connectionError(throwable.getMessage());
            } catch (Exception e) {
                Timber.d("Exception : " + e.toString());
            }
        } else if (throwable instanceof IOException) {
            assert view != null;
            Timber.d("throwable code== " + throwable.getMessage());
            try {
                if (throwable.getMessage().contains("Read error")) {
                    view.gotoAppUnderMaintain();
                } else {
                    view.showApiFailureError(throwable.toString(), "", "");
                }
            } catch (Exception e) {
                Timber.d("Exception : " + e.toString());
            }

        } else {
            assert view != null;
            view.showApiFailureError(throwable.toString(), "", "");
        }

    }

    public void onComplete() {
        Timber.d("onComplete:");
    }

    public void onNext(Object response) {
        Timber.d("onNext:");
        this.onSuccess(response);
    }

    protected abstract void onSuccess(Object response);
}
