package com.apilibrary.demo.data.api;


import com.apilibrary.demo.data.api.models.nationalities.NationalityResponse;

import io.reactivex.Observable;

public interface ApiHelper {

    Observable<NationalityResponse> getAllNationalities();
}