package com.example.evergreenevents;

import android.annotation.SuppressLint;
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

public class ContactUs extends AppCompatActivity {

    ImageView ivNav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    TextView navEmail;
    EditText etFullName, etEmail, etPhone, etEventType, etEventBudget, etEventDate, etEventLocation, etNumberOfGuests, etHearAboutUs;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_us);

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
                if (id == R.id.nav_ContactUs) {
                } else if (id == R.id.nav_Venues) {
                    startActivity(new Intent(ContactUs.this, Venues.class));
                } else if (id == R.id.nav_home) {
                    startActivity(new Intent(ContactUs.this, Dashboard.class));
                }else if (id == R.id.nav_AboutUs) {
                    startActivity(new Intent(ContactUs.this, AboutUs.class));
                }else if (id == R.id.nav_VenueBooking) {
                    startActivity(new Intent(ContactUs.this, Venue_Booking.class));
                }else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(ContactUs.this, Login.class));
                    finish();
                }
                else if (id == R.id.nav_signup) {
                    startActivity(new Intent(ContactUs.this, MainActivity.class));
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
                String fullName = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String phone = etPhone.getText().toString();
                String eventType = etEventType.getText().toString().trim();
                String eventBudget = etEventBudget.getText().toString().trim();
                String eventDate = etEventDate.getText().toString().trim();
                String eventLocation = etEventLocation.getText().toString().trim();
                String numberOfGuests = etNumberOfGuests.getText().toString();
                String hearAboutUs = etHearAboutUs.getText().toString().trim();

                if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || eventType.isEmpty() ||
                        eventBudget.isEmpty() || eventDate.isEmpty() || eventLocation.isEmpty() ||
                        numberOfGuests.isEmpty() || hearAboutUs.isEmpty()) {
                    Toast.makeText(ContactUs.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                ProgressDialog contact = new ProgressDialog(ContactUs.this);
                contact.setMessage("Sending Response.....");
                contact.show();

                String key = reference.push().getKey();

                ContsctUsData dataContact = new ContsctUsData( fullName,  email,  phone,  eventType,  eventBudget,  eventDate,  eventLocation,  numberOfGuests,  hearAboutUs);
                reference.child("ContactUs")
                        .child(key)
                        .setValue(dataContact)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                contact.dismiss();
                                Toast.makeText(ContactUs.this, "Your Response has been Sent!", Toast.LENGTH_SHORT).show();
                                etFullName.setText("");
                                etEmail.setText("");
                                etPhone.setText("");
                                etEventType.setText("");
                                etEventBudget.setText("");
                                etEventDate.setText("");
                                etEventLocation.setText("");
                                etNumberOfGuests.setText("");
                                etHearAboutUs.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                contact.dismiss();
                                Toast.makeText(ContactUs.this, "Failed to send your Response. Please try again!", Toast.LENGTH_SHORT).show();
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
        etEventType = findViewById(R.id.etEventType);
        etEventBudget = findViewById(R.id.etEventBudget);
        etEventDate = findViewById(R.id.etEventDate);
        etEventLocation = findViewById(R.id.etEventLocation);
        etNumberOfGuests = findViewById(R.id.etNumberOfGuests);
        etHearAboutUs = findViewById(R.id.etHearAboutUs);

        btnSubmit = findViewById(R.id.submitButton);
    }
}