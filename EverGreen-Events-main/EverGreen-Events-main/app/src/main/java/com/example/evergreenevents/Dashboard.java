package com.example.evergreenevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    RecyclerView rvVideos;
    VideoAdapter videoAdapter;
    LinearLayoutManager llmanager;
    ImageView ivNav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    TextView navEmail;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        init();

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ivNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                } else if (id == R.id.nav_Venues) {
                    startActivity(new Intent(Dashboard.this, Venues.class));
                } else if (id == R.id.nav_ContactUs) {
                    startActivity(new Intent(Dashboard.this, ContactUs.class));
                }else if (id == R.id.nav_VenueBooking) {
                    startActivity(new Intent(Dashboard.this, Venue_Booking.class));
                }else if (id == R.id.nav_AboutUs) {
                    startActivity(new Intent(Dashboard.this, AboutUs.class));
                } else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(Dashboard.this, Login.class));
                    finish();
                }
                else if (id == R.id.nav_signup) {
                    startActivity(new Intent(Dashboard.this, MainActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        View headerView = navigationView.getHeaderView(0);
        navEmail = headerView.findViewById(R.id.navEmail);

        if(user!=null)
        {
            navEmail.setText(user.getEmail());
        }

        // Setup ViewFlipper
        int[] images = {R.drawable.venue1, R.drawable.venue3, R.drawable.venue5, R.drawable.venue, R.drawable.venue2};
        String[] captions = {"Timeless Elegance and Charm", "Sophistication and Style", "State-of-the-Art Facilities", "Where Dreams Come True", "Perfect for Weddings and Grand Events"};
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < images.length; i++) {
            View flipperItem = inflater.inflate(R.layout.flipper_item, null);
            ImageView imageView = flipperItem.findViewById(R.id.flipper_image);
            TextView captionView = flipperItem.findViewById(R.id.flipper_caption);
            imageView.setBackgroundResource(images[i]);
            captionView.setText(captions[i]);
            viewFlipper.addView(flipperItem);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
    }

    private void init() {
        rvVideos = findViewById(R.id.rvVideos);
        ivNav = findViewById(R.id.ivNavButton);
        drawerLayout = findViewById(R.id.drawer_layout);
        viewFlipper = findViewById(R.id.viewFlipper);
        llmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvVideos.setLayoutManager(llmanager);

        videoAdapter = new VideoAdapter(Dashboard.this, MyApplication.videos);
        rvVideos.setAdapter(videoAdapter);

        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvVideos.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down));
    }
}
