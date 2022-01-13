package com.apilibrary.demo;

public class AppConstants {
    public static final long CACHE_SIZE = 10 * 1024 * 1024L;
    public static final long API_TIMEOUT_TIME = 300L;
    public static final String API_BASE_URL = "https://online.aecb.gov.ae:8000/api/v1/";

    public static final class ApkBuildType {
        public static final String UAT = "uat";
        public static final String DEV = "dev";
        public static final String PROD = "prod";
    }
}
