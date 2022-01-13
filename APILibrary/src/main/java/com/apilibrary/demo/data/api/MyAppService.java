package com.apilibrary.demo.data.api;

import com.apilibrary.demo.data.api.models.nationalities.NationalityResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MyAppService {

    @Headers("refId:true")
    @GET("standard/getnationalities1")
    Observable<NationalityResponse> getAllNationalities();
}