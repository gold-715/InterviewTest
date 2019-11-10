package com.gold.InterviewTest.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PSIApiInfo implements Parcelable {
    @SerializedName("status")
    private String status;

    protected PSIApiInfo(Parcel in) {
        status = in.readString();
    }

    public static final Creator<PSIApiInfo> CREATOR = new Creator<PSIApiInfo>() {
        @Override
        public PSIApiInfo createFromParcel(Parcel in) {
            return new PSIApiInfo(in);
        }

        @Override
        public PSIApiInfo[] newArray(int size) {
            return new PSIApiInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
    }

    public String getStatus() {
        return status;
    }
}
