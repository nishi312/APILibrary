package com.apilibrary.demo;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("refId:true")
    @GET("standard/getnationalities1")
    Call<NationalityResponse> getAllNationalities();
}
