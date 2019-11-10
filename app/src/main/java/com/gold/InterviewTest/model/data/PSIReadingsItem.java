package com.gold.InterviewTest.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PSIReadingsItem implements Parcelable {
    public final static String WEST = "west";
    public final static String NATIONAL = "national";
    public final static String EAST = "east";
    public final static String CENTRAL = "central";
    public final static String SOUTH = "south";
    public final static String NORTH = "north";

    @SerializedName(WEST)
    private double west;
    @SerializedName(NATIONAL)
    private double national;
    @SerializedName(EAST)
    private double east;
    @SerializedName(CENTRAL)
    private double central;
    @SerializedName(SOUTH)
    private double south;
    @SerializedName(NORTH)
    private double north;

    protected PSIReadingsItem(Parcel in) {
        west = in.readDouble();
        national = in.readDouble();
        east = in.readDouble();
        central = in.readDouble();
        south = in.readDouble();
        north = in.readDouble();
    }

    public static final Creator<PSIReadingsItem> CREATOR = new Creator<PSIReadingsItem>() {
        @Override
        public PSIReadingsItem createFromParcel(Parcel in) {
            return new PSIReadingsItem(in);
        }

        @Override
        public PSIReadingsItem[] newArray(int size) {
            return new PSIReadingsItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(west);
        dest.writeDouble(national);
        dest.writeDouble(east);
        dest.writeDouble(central);
        dest.writeDouble(south);
        dest.writeDouble(north);
    }

    public double getWest() {
        return west;
    }

    public double getNational() {
        return national;
    }

    public double getEast() {
        return east;
    }

    public double getCentral() {
        return central;
    }

    public double getSouth() {
        return south;
    }

    public double getNorth() {
        return north;
    }

    public double getReadingItemByPath(String path) {
        switch (path) {
            case PSIReadingsItem.CENTRAL:
                return getCentral();
            case PSIReadingsItem.EAST:
                return getEast();
            case PSIReadingsItem.NATIONAL:
                return getNational();
            case PSIReadingsItem.NORTH:
                return getNorth();
            case PSIReadingsItem.SOUTH:
                return getSouth();
            case PSIReadingsItem.WEST:
                return getWest();
        }
        return 0;
    }
}
