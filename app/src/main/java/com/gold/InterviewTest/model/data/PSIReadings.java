package com.gold.InterviewTest.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PSIReadings implements Parcelable {
    @SerializedName("o3_sub_index")
    PSIReadingsItem o3SubIndex;
    @SerializedName("pm10_twenty_four_hourly")
    PSIReadingsItem pm10TwentyFourHourly;
    @SerializedName("pm10_sub_index")
    PSIReadingsItem pm10SubIndex;
    @SerializedName("co_sub_index")
    PSIReadingsItem coSubIndex;
    @SerializedName("pm25_twenty_four_hourly")
    PSIReadingsItem pm25TwentyFourHourly;
    @SerializedName("so2_sub_index")
    PSIReadingsItem so2SubIndex;
    @SerializedName("co_eight_hour_max")
    PSIReadingsItem coEightHourMax;
    @SerializedName("no2_one_hour_max")
    PSIReadingsItem no2OneHourMax;
    @SerializedName("so2_twenty_four_hourly")
    PSIReadingsItem so2TwentyFourHourly;
    @SerializedName("pm25_sub_index")
    PSIReadingsItem pm25SubIndex;
    @SerializedName("psi_twenty_four_hourly")
    PSIReadingsItem psiTwentyFourHourly;
    @SerializedName("o3_eight_hour_max")
    PSIReadingsItem o3EightHourMax;

    protected PSIReadings(Parcel in) {
        o3SubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        pm10TwentyFourHourly = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        pm10SubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        coSubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        pm25TwentyFourHourly = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        so2SubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        coEightHourMax = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        no2OneHourMax = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        so2TwentyFourHourly = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        pm25SubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        psiTwentyFourHourly = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        o3EightHourMax = in.readParcelable(PSIReadingsItem.class.getClassLoader());
    }

    public static final Creator<PSIReadings> CREATOR = new Creator<PSIReadings>() {
        @Override
        public PSIReadings createFromParcel(Parcel in) {
            return new PSIReadings(in);
        }

        @Override
        public PSIReadings[] newArray(int size) {
            return new PSIReadings[size];
        }
    };

    public PSIReadingsItem getO3SubIndex() {
        return o3SubIndex;
    }

    public PSIReadingsItem getPm10TwentyFourHourly() {
        return pm10TwentyFourHourly;
    }

    public PSIReadingsItem getPm10SubIndex() {
        return pm10SubIndex;
    }

    public PSIReadingsItem getCoSubIndex() {
        return coSubIndex;
    }

    public PSIReadingsItem getPm25TwentyFourHourly() {
        return pm25TwentyFourHourly;
    }

    public PSIReadingsItem getSo2SubIndex() {
        return so2SubIndex;
    }

    public PSIReadingsItem getCoEightHourMax() {
        return coEightHourMax;
    }

    public PSIReadingsItem getNo2OneHourMax() {
        return no2OneHourMax;
    }

    public PSIReadingsItem getSo2TwentyFourHourly() {
        return so2TwentyFourHourly;
    }

    public PSIReadingsItem getPm25SubIndex() {
        return pm25SubIndex;
    }

    public PSIReadingsItem getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }

    public PSIReadingsItem getO3EightHourMax() {
        return o3EightHourMax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(o3SubIndex, flags);
        dest.writeParcelable(pm10TwentyFourHourly, flags);
        dest.writeParcelable(pm10SubIndex, flags);
        dest.writeParcelable(coSubIndex, flags);
        dest.writeParcelable(pm25TwentyFourHourly, flags);
        dest.writeParcelable(so2SubIndex, flags);
        dest.writeParcelable(coEightHourMax, flags);
        dest.writeParcelable(no2OneHourMax, flags);
        dest.writeParcelable(so2TwentyFourHourly, flags);
        dest.writeParcelable(pm25SubIndex, flags);
        dest.writeParcelable(psiTwentyFourHourly, flags);
        dest.writeParcelable(o3EightHourMax, flags);
    }

    public double getLabel(String path) {
        double index = 0;
        switch (path) {
            case PSIReadingsItem.CENTRAL:
                return getO3SubIndex().getWest();
        }
        return index;
    }
}
