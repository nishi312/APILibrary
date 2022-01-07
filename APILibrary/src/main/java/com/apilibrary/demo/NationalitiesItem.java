package com.apilibrary.demo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NationalitiesItem implements Parcelable {

    @SerializedName("name_ar")
    private String nameAr;

    @SerializedName("nationalityId")
    private String nationalityId;

    @SerializedName("threeDigitIso")
    private String threeDigitIso;

    @SerializedName("name_en")
    private String nameEn;

    protected NationalitiesItem(Parcel in) {
        nameAr = in.readString();
        nationalityId = in.readString();
        threeDigitIso = in.readString();
        nameEn = in.readString();
    }

    public static final Creator<NationalitiesItem> CREATOR = new Creator<NationalitiesItem>() {
        @Override
        public NationalitiesItem createFromParcel(Parcel in) {
            return new NationalitiesItem(in);
        }

        @Override
        public NationalitiesItem[] newArray(int size) {
            return new NationalitiesItem[size];
        }
    };

    public String getCountryName() {
        return getNameEn();
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getThreeDigitIso() {
        return threeDigitIso;
    }

    public void setThreeDigitIso(String threeDigitIso) {
        this.threeDigitIso = threeDigitIso;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public String toString() {
        return
                "NationalitiesItem{" +
                        "name_ar = '" + nameAr + '\'' +
                        ",nationalityId = '" + nationalityId + '\'' +
                        ",threeDigitIso = '" + threeDigitIso + '\'' +
                        ",name_en = '" + nameEn + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameAr);
        dest.writeString(nationalityId);
        dest.writeString(threeDigitIso);
        dest.writeString(nameEn);
    }
}