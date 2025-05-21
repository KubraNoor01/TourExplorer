package com.example.evergreenevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AboutUs extends AppCompatActivity {
    Button btnGetInTouch;
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
        setContentView(R.layout.activity_about_us);

        btnGetInTouch = findViewById(R.id.btnGetInTouch);
        ivNav = findViewById(R.id.ivNavButton);
        drawerLayout = findViewById(R.id.drawer_layout);
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        viewFlipper = findViewById(R.id.viewFlipper);

        btnGetInTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutUs.this, ContactUs.class);
                startActivity(i);
                finish();
            }
        });

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
                if (id == R.id.nav_AboutUs) {
                } else if (id == R.id.nav_Venues) {
                    startActivity(new Intent(AboutUs.this, Venues.class));
                } else if (id == R.id.nav_ContactUs) {
                    startActivity(new Intent(AboutUs.this, ContactUs.class));
                }else if (id == R.id.nav_home) {
                    startActivity(new Intent(AboutUs.this, Dashboard.class));
                }else if (id == R.id.nav_VenueBooking) {
                    startActivity(new Intent(AboutUs.this, Venue_Booking.class));
                }else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(AboutUs.this, Login.class));
                    finish();
                }
                else if (id == R.id.nav_signup) {
                    startActivity(new Intent(AboutUs.this, MainActivity.class));
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

        int[] images = {R.drawable.venue1, R.drawable.venue3, R.drawable.venue5, R.drawable.venue, R.drawable.venue2};
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < images.length; i++) {
            View flipperItem = inflater.inflate(R.layout.flipper_item, null);
            ImageView imageView = flipperItem.findViewById(R.id.flipper_image);
            imageView.setBackgroundResource(images[i]);
            viewFlipper.addView(flipperItem);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }
}