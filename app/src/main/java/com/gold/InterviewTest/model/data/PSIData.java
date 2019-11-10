package com.gold.InterviewTest.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PSIData implements Parcelable {
    @SerializedName("region_metadata")
    private List<PSIRegionMetaData> regionMetaDatas;
    @SerializedName("items")
    private List<PSIItem> items;
    @SerializedName("api_info")
    private PSIApiInfo apiInfo;

    protected PSIData(Parcel in) {
        regionMetaDatas = in.createTypedArrayList(PSIRegionMetaData.CREATOR);
        items = in.createTypedArrayList(PSIItem.CREATOR);
        apiInfo = in.readParcelable(PSIApiInfo.class.getClassLoader());
    }

    public static final Creator<PSIData> CREATOR = new Creator<PSIData>() {
        @Override
        public PSIData createFromParcel(Parcel in) {
            return new PSIData(in);
        }

        @Override
        public PSIData[] newArray(int size) {
            return new PSIData[size];
        }
    };

    public List<PSIRegionMetaData> getRegionMetaDatas() {
        return regionMetaDatas;
    }

    public List<PSIItem> getItems() {
        return items;
    }

    public PSIApiInfo getApiInfo() {
        return apiInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(regionMetaDatas);
        dest.writeTypedList(items);
        dest.writeParcelable(apiInfo, flags);
    }
}
