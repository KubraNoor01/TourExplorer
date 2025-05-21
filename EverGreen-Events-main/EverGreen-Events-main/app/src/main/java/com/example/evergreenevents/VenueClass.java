package com.example.evergreenevents;

public class VenueClass {
    private int imageResId;
    private String venueName;
    private String venueDescription;

    public VenueClass() {
    }

    public VenueClass(int imageResId, String venueName, String venueDescription) {
        this.imageResId = imageResId;
        this.venueName = venueName;
        this.venueDescription = venueDescription;
    }

    public int getImage() {
        return imageResId;
    }

    public void setImage(int image) {
        this.imageResId = image;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueDescription() {
        return venueDescription;
    }

    public void setVenueDescription(String venueDescription) {
        this.venueDescription = venueDescription;
    }
}
