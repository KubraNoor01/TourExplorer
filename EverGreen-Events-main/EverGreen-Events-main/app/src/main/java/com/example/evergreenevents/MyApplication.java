package com.example.evergreenevents;

import android.app.Application;
import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Videos> videos;
    public static ArrayList<VenueClass> venue;

    @Override
    public void onCreate() {
        super.onCreate();
        videos = new ArrayList<>();
        venue = new ArrayList<>();

        videos.add(new Videos(R.raw.video));
        videos.add(new Videos(R.raw.video1));
        videos.add(new Videos(R.raw.video3));
        videos.add(new Videos(R.raw.video));


        venue.add(new VenueClass(R.drawable.venue, "Timeless Terrace", getString(R.string.Timeless_terrace_detail)));
        venue.add(new VenueClass(R.drawable.venue1, "Prime Pavilion",getString(R.string.Prime_pavilion_detail)));
        venue.add(new VenueClass(R.drawable.venue2, "Harmony Halls",getString(R.string.Harmony_halls_detail)));
        venue.add(new VenueClass(R.drawable.venue3, "Steller Space",getString(R.string.Steller_space_detail)));
        venue.add(new VenueClass(R.drawable.venue4, "Ball Room",getString(R.string.Ball_room_detail)));
        venue.add(new VenueClass(R.drawable.venue5, "Villa",getString(R.string.Villa_detail)));
        }
}
