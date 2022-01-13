package com.apilibrary.demo.data.preference;

public interface PrefHelper {
    void clearAll();

    String getSessionToken();

    void setSessionToken(String sessionToken);
}