package com.gold.InterviewTest.model.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PSIItem implements Parcelable {
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("update_timestamp")
    private String updateTimestamp;
    @SerializedName("readings")
    private PSIReadings readings;

    protected PSIItem(Parcel in) {
        timestamp = in.readString();
        updateTimestamp = in.readString();
        readings = in.readParcelable(PSIReadings.class.getClassLoader());
    }

    public static final Creator<PSIItem> CREATOR = new Creator<PSIItem>() {
        @Override
        public PSIItem createFromParcel(Parcel in) {
            return new PSIItem(in);
        }

        @Override
        public PSIItem[] newArray(int size) {
            return new PSIItem[size];
        }
    };

    public String getTimestamp() {
        return timestamp;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public PSIReadings getReadings() {
        return readings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timestamp);
        dest.writeString(updateTimestamp);
        dest.writeParcelable(readings, flags);
    }
}
