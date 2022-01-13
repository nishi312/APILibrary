package com.apilibrary.demo.ui.presenter;

import com.apilibrary.demo.base.mvp.BasePresenter;
import com.apilibrary.demo.base.mvp.BaseView;
import com.apilibrary.demo.data.api.models.nationalities.NationalitiesItem;

import java.util.List;

public interface MainContract {

    interface View extends BaseView {
        void showResult(List<NationalitiesItem> nationalities);
    }

    interface Presenter extends BasePresenter<View> {
        void getAllNationalities();
    }
}