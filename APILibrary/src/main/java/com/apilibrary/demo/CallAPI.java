package com.apilibrary.demo;

import android.content.Context;

import com.apilibrary.demo.ui.view.MainActivity;

public class CallAPI {
    public static MainActivity act;

    public CallAPI(MainActivity act) {

    }

    public static void fetchNationalities(Context context) {
        act.callNationalityAPI();
    }
}
