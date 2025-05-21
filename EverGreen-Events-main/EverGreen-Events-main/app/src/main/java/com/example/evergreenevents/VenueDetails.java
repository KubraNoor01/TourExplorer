package com.example.evergreenevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VenueDetails extends AppCompatActivity {
    TextView tvVenueName, tvDescription;
    ImageView ivVenueImage;
    Button btnBookVenue;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    ImageView ivNav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    TextView navEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details);

        tvVenueName = findViewById(R.id.tvVenueName);
        tvDescription = findViewById(R.id.tvDescription);
        ivVenueImage = findViewById(R.id.ivVenueImage);
        btnBookVenue = findViewById(R.id.btnBookVenue);
        ivNav = findViewById(R.id.ivNavButton);
        drawerLayout = findViewById(R.id.drawer_layout);

        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

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
                    // Handle home click
                } else if (id == R.id.nav_Venues) {
                    startActivity(new Intent(VenueDetails.this, Venues.class));
                } else if (id == R.id.nav_ContactUs) {
                    startActivity(new Intent(VenueDetails.this, ContactUs.class));
                } else if (id == R.id.nav_VenueBooking) {
                    startActivity(new Intent(VenueDetails.this, Venue_Booking.class));
                } else if (id == R.id.nav_AboutUs) {
                    startActivity(new Intent(VenueDetails.this, AboutUs.class));
                } else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(VenueDetails.this, Login.class));
                    finish();
                } else if (id == R.id.nav_signup) {
                    startActivity(new Intent(VenueDetails.this, MainActivity.class));
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

        btnBookVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VenueDetails.this, Venue_Booking.class);
                startActivity(i);
                finish();
            }
        });

        String venueName = getIntent().getStringExtra("venue_name");
        String venueDesc = getIntent().getStringExtra("venue_description");
        int venueImageResId = getIntent().getIntExtra("venue_image", -1);

        tvVenueName.setText(venueName);
        tvDescription.setText(venueDesc);
        Glide.with(this).load(venueImageResId).into(ivVenueImage);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
