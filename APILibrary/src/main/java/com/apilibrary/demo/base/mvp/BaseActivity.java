package com.apilibrary.demo.base.mvp;


import static com.apilibrary.demo.utils.ToastUtil.longToast;
import static com.apilibrary.demo.utils.ToastUtil.toast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatDelegate;

import com.apilibrary.demo.R;
import com.apilibrary.demo.base.network.ConnectivityUtils;
import com.apilibrary.demo.utils.Utilities;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import dagger.android.AndroidInjection;
import timber.log.Timber;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends MvpActivity<V, P>
        implements BaseView {

    private Dialog progressDialog;
    private Handler handler = new Handler();

    @Override
    protected void attachBaseContext(Context newBase) {
    }


    public void showLoading(String message) {
        progressDialog = DialogUtils.showLoading(this, message);
    }

    public void hideLoading() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) progressDialog.cancel();
            progressDialog = null;
        }
    }

    public void clientError(String errorMessage) {
        if (errorMessage != null)
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }


    @SuppressLint("NewApi")
    public void connectionError(String errorMessage) {
        Timber.e(errorMessage);
        if (!this.isNetworkConnected()) {
            showApiFailureError(getString(R.string.networkError), "", "");
        } else {
            showApiFailureError(getString(R.string.connectionError), "", "");
        }

    }

    public void gotoAppUnderMaintain() {
        // moveActivity(this, MaintenanceActivity.class, true, true);
    }


    public boolean isNetworkConnected() {
        return ConnectivityUtils.isConnectedToInternet(this);
    }

    public void onTimeout() {
        longToast(this, R.string.timeoutError);
    }

    public void serverError(String errorMessage) {
        Timber.e(errorMessage);
        longToast(this, R.string.serverError);
    }

    public void unauthenticated() {
        longToast(this, R.string.unauthenticatedError);
    }

    public void unexpectedError(String errorMessage) {
        toast(this, errorMessage);
    }

    public void unexpectedError(RuntimeException exception) {
        if (exception.getMessage() != null) {
            toast(this, exception.getMessage());
        }
    }

    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        adjustFontScale(getResources().getConfiguration());
        setStatusBarColor();
        setScreenDirection();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        if (this.getLayoutRes() != 0) {
            this.setContentView(this.getLayoutRes());
        }
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            Utilities.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19 && getWindow() != null && getWindow().getDecorView() != null) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Utilities.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            if (getWindow() != null) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }

    public void setScreenDirection() {
        if (getWindow() != null) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.initToolbar();
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void initToolbar();

    public void moveActivity(Context context, Class<?> c, boolean finish, boolean clearStack, Bundle bundle) {
        Intent intent = new Intent(context, c);
        if (bundle != null) intent.putExtras(bundle);
        if (clearStack)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (finish) ((Activity) context).finish();
    }

    public void moveActivity(Context context, Class<?> c, int flags, Bundle bundle) {
        Intent intent = new Intent(context, c);
        if (bundle != null)
            intent.putExtras(bundle);
        intent.setFlags(flags);
        context.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void moveActivity(Context context, Class<?> c, boolean finish, int clearStack, Bundle bundle) {
        Intent intent = new Intent(context, c);
        if (bundle != null) intent.putExtras(bundle);
        intent.setFlags(clearStack);
        context.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (finish) ((Activity) context).finish();
    }

    public void moveActivity(Context context, Class<?> c, boolean finish, boolean clearStack, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, c);
        if (bundle != null) intent.putExtras(bundle);
        if (clearStack)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (finish) ((Activity) context).finish();
    }

    public void moveActivity(Context context, Class<?> c, boolean finish, boolean clearStack) {
        Intent intent = new Intent(context, c);
        if (clearStack)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (finish) ((Activity) context).finish();
    }

    public void moveActivity(Context context, Class<?> c, boolean finish, boolean clearStack, Intent intent) {
        if (clearStack)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (finish) ((Activity) context).finish();
    }

    public void showInternetNotAvailableDialog() {
        toast(this, getString(R.string.networkError));
    }

    @Override
    public void sessionTokenExpired() {
        longToast(this, getString(R.string.session_expired));
    }

    @Override
    public void localValidationError(String title, String desc) {
        longToast(this, getString(R.string.txt_ok));
    }


    public void showApiFailureError(String message, String status, String useCase) {
        showErrorDialog(message, status);
    }

    @Override
    public void showApiSuccessMessage(String message, String status, String useCase) {
        toast(this, message);
    }


    public void showErrorDialog(String message, String status) {
        toast(this, message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void adjustFontScale(Configuration configuration) {
        configuration.fontScale = (float) 1.0;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }

}
