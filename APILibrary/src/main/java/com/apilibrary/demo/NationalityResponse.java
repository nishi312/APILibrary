package com.apilibrary.demo;

public class NationalityResponse extends BaseResponse {
    private NationalityList data;

    public NationalityList getData() {
        return data;
    }

    public void setData(NationalityList data) {
        this.data = data;
    }
}