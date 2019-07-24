package com.fikri.tourismapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TourismOnline implements Parcelable {
    int id;
    String title, location, description, image,latitude,longitude;

    public TourismOnline(int id, String title, String location, String description, String image, String latitude, String longitude) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.location);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
    }

    protected TourismOnline(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.location = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
    }

    public static final Creator<TourismOnline> CREATOR = new Creator<TourismOnline>() {
        @Override
        public TourismOnline createFromParcel(Parcel source) {
            return new TourismOnline(source);
        }

        @Override
        public TourismOnline[] newArray(int size) {
            return new TourismOnline[size];
        }
    };
}
