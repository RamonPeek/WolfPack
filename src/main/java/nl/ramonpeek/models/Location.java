package nl.ramonpeek.models;

import javax.validation.constraints.NotNull;

public class Location {

    @NotNull
    private float latitude;
    @NotNull
    private float longitude;

    public Location() {}

    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
