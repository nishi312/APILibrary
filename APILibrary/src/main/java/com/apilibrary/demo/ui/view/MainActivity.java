package com.apilibrary.demo.ui.view;

import static com.apilibrary.demo.utils.ToastUtil.toast;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.apilibrary.demo.CallAPI;
import com.apilibrary.demo.R;
import com.apilibrary.demo.base.mvp.BaseActivity;
import com.apilibrary.demo.data.api.models.nationalities.NationalitiesItem;
import com.apilibrary.demo.ui.presenter.MainContract;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter>
        implements MainContract.View {

    @Inject
    public MainContract.Presenter mPresenter;
    CallAPI callAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void callNationalityAPI() {
        mPresenter.getAllNationalities();
    }

    @NonNull
    @Override
    public MainContract.Presenter createPresenter() {
        return mPresenter;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void showResult(List<NationalitiesItem> nationalities) {
        toast(this, nationalities.toString());
    }
}