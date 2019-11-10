package com.gold.InterviewTest.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PSIRegionMetaData implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("label_location")
    private PSILocation location;

    public PSIRegionMetaData(String name, PSILocation location) {
        this.name = name;
        this.location = location;
    }

    protected PSIRegionMetaData(Parcel in) {
        name = in.readString();
    }

    public static final Creator<PSIRegionMetaData> CREATOR = new Creator<PSIRegionMetaData>() {
        @Override
        public PSIRegionMetaData createFromParcel(Parcel in) {
            return new PSIRegionMetaData(in);
        }

        @Override
        public PSIRegionMetaData[] newArray(int size) {
            return new PSIRegionMetaData[size];
        }
    };

    public String getName() {
        return name;
    }

    public PSILocation getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
