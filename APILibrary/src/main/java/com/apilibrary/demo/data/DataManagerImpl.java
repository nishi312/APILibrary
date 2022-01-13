package com.apilibrary.demo.data;

import com.apilibrary.demo.data.api.ApiHelper;
import com.apilibrary.demo.data.api.models.nationalities.NationalityResponse;
import com.apilibrary.demo.data.preference.PrefHelper;

import javax.inject.Inject;

import io.reactivex.Observable;

public final class DataManagerImpl implements DataManager {

    private PrefHelper mPreferenceHelper;
    private ApiHelper mApiHelper;

    @Inject
    public DataManagerImpl(PrefHelper mPreferenceHelper, ApiHelper mApiHelper) {
        this.mPreferenceHelper = mPreferenceHelper;
        this.mApiHelper = mApiHelper;
    }

    @Override
    public Observable<NationalityResponse> getAllNationalities() {
        return mApiHelper.getAllNationalities();
    }
}