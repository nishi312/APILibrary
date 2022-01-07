package com.apilibrary.demo;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallAPI {
    public static  List<NationalitiesItem> nationalitiesItemList = new ArrayList<>();

    public static void fetchNationalities(Context context) {
        ApiService apiService = ApiClient.getClient(context)
                .create(ApiService.class);
        Call<NationalityResponse> call = apiService.getAllNationalities();
        call.enqueue(new Callback<NationalityResponse>() {
            @Override
            public void onResponse(Call<NationalityResponse> call, Response<NationalityResponse> response) {
                NationalityResponse response1 = response.body();
                nationalitiesItemList = response1.getData().getNationalities();
                Toast.makeText(context, nationalitiesItemList.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<NationalityResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
