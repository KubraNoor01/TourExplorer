package com.example.evergreenevents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Venue_Booking extends AppCompatActivity {
    ImageView ivNav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    TextView navEmail;
    EditText etFullName, etEmail, etPhone, etVenueName, etEventType, etAddress, etCity,
            etProvince, etPostalCode, etEventDate, etTime, etAmPm, etTime2, etAmPm2, etATime, etAmPm3,
            etETime, etAmPm4, etMaxGuests, etCaterer, etAdditional;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_venue_booking);

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
                if (id == R.id.nav_VenueBooking) {
                } else if (id == R.id.nav_Venues) {
                    startActivity(new Intent(Venue_Booking.this, Venues.class));
                } else if (id == R.id.nav_ContactUs) {
                    startActivity(new Intent(Venue_Booking.this, ContactUs.class));
                }else if (id == R.id.nav_AboutUs) {
                    startActivity(new Intent(Venue_Booking.this, AboutUs.class));
                }else if (id == R.id.nav_home) {
                    startActivity(new Intent(Venue_Booking.this, Dashboard.class));
                }else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(Venue_Booking.this, Login.class));
                    finish();
                }
                else if (id == R.id.nav_signup) {
                    startActivity(new Intent(Venue_Booking.this, MainActivity.class));
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


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String venueName = etVenueName.getText().toString();
                String eventType = etEventType.getText().toString();
                String address = etAddress.getText().toString();
                String city = etCity.getText().toString();
                String province = etProvince.getText().toString();
                String postalCode = etPostalCode.getText().toString();
                String eventDate = etEventDate.getText().toString();
                String time = etTime.getText().toString();
                String amPm = etAmPm.getText().toString();
                String time2 = etTime2.getText().toString();
                String amPm2 = etAmPm2.getText().toString();
                String aTime = etATime.getText().toString();
                String amPm3 = etAmPm3.getText().toString();
                String eTime = etETime.getText().toString();
                String amPm4 = etAmPm4.getText().toString();
                String maxGuests = etMaxGuests.getText().toString();
                String caterer = etCaterer.getText().toString();
                String additionalInfo = etAdditional.getText().toString();


                VenueBookingData bookingData = new VenueBookingData(fullName, email, phone, venueName,
                        eventType, address, city, province, postalCode, eventDate, time, amPm, time2,
                        amPm2, aTime, amPm3, eTime, amPm4, maxGuests, caterer, additionalInfo);

                String bookingId = reference.push().getKey();
                ProgressDialog booking = new ProgressDialog(Venue_Booking.this);
                booking.setMessage("Sending Response.....");
                booking.show();

                reference.child("VenueBookingData")
                        .child(bookingId)
                        .setValue(bookingData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                booking.dismiss();
                                Toast.makeText(Venue_Booking.this, "Booking details saved successfully", Toast.LENGTH_SHORT).show();
                                etFullName.setText("");
                                etEmail.setText("");
                                etPhone.setText("");
                                etVenueName.setText("");
                                etEventType.setText("");
                                etAddress.setText("");
                                etCity.setText("");
                                etProvince.setText("");
                                etPostalCode.setText("");
                                etEventDate.setText("");
                                etTime.setText("");
                                etAmPm.setText("");
                                etTime2.setText("");
                                etAmPm2.setText("");
                                etATime.setText("");
                                etAmPm3.setText("");
                                etETime.setText("");
                                etAmPm4.setText("");
                                etMaxGuests.setText("");
                                etCaterer.setText("");
                                etAdditional.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                booking.dismiss();
                                Toast.makeText(Venue_Booking.this, "Could not save booking details. Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }
    private void init()
    {
        ivNav = findViewById(R.id.ivNavButton);
        drawerLayout = findViewById(R.id.drawer_layout);
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etVenueName = findViewById(R.id.etVenueName);
        etEventType = findViewById(R.id.etEventType);
        etAddress = findViewById(R.id.etAddress);
        etCity = findViewById(R.id.etCity);
        etProvince = findViewById(R.id.etProvince);
        etPostalCode = findViewById(R.id.etPostalCode);
        etEventDate = findViewById(R.id.etEventDate);
        etTime = findViewById(R.id.etTime);
        etAmPm = findViewById(R.id.etAmPm);
        etTime2 = findViewById(R.id.etTime2);
        etAmPm2 = findViewById(R.id.etAmPm2);
        etATime = findViewById(R.id.etATime);
        etAmPm3 = findViewById(R.id.etAmPm3);
        etETime = findViewById(R.id.etETime);
        etAmPm4 = findViewById(R.id.etAmPm4);
        etMaxGuests = findViewById(R.id.etMaxGuests);
        etMaxGuests = findViewById(R.id.etMaxGuests);
        etCaterer = findViewById(R.id.etcaterer);
        btnSubmit = findViewById(R.id.btnSubmit);

    }
}