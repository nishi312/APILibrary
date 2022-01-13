package com.apilibrary.demo.data.api;


import com.apilibrary.demo.data.api.models.nationalities.NationalityResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public final class ApiHelperImpl implements ApiHelper {
    private MyAppService myAppService;

    @Inject
    public ApiHelperImpl(MyAppService myAppService) {
        this.myAppService = myAppService;
    }

    @Override
    public Observable<NationalityResponse> getAllNationalities() {
        return myAppService.getAllNationalities();
    }
}