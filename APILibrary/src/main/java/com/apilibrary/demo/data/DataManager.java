package com.apilibrary.demo.data;


import com.apilibrary.demo.data.api.models.nationalities.NationalityResponse;

import io.reactivex.Observable;

public interface DataManager {
    Observable<NationalityResponse> getAllNationalities();
}