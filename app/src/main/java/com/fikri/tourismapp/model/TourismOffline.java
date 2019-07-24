package com.fikri.tourismapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TourismOffline implements Parcelable {
    int image;
    String title;
    String description;
    String location;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.image);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.location);
    }

    public TourismOffline() {
    }

    protected TourismOffline(Parcel in) {
        this.image = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.location = in.readString();
    }

    public static final Creator<TourismOffline> CREATOR = new Creator<TourismOffline>() {
        @Override
        public TourismOffline createFromParcel(Parcel source) {
            return new TourismOffline(source);
        }

        @Override
        public TourismOffline[] newArray(int size) {
            return new TourismOffline[size];
        }
    };
}
