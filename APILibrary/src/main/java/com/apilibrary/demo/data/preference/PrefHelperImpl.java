package com.apilibrary.demo.data.preference;

import com.tumblr.remember.Remember;

import javax.inject.Inject;

public final class PrefHelperImpl implements PrefHelper {

    public static String KEY_SESSION_TOKEN = "KEY_SESSION_TOKEN";

    @Inject
    PrefHelperImpl() {
    }

    public void clearAll() {
        Remember.clear();
    }


    @Override
    public String getSessionToken() {
        return Remember.getString(KEY_SESSION_TOKEN, "");
    }

    @Override
    public void setSessionToken(String sessionToken) {
        Remember.putString(KEY_SESSION_TOKEN, sessionToken);
    }
}
