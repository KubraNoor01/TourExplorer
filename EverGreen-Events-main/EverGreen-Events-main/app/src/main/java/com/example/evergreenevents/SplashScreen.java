package com.example.evergreenevents;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.Transaction;

public class SplashScreen extends AppCompatActivity {
    ImageView ivLogo;
    TextView tvLogo, tvSLogan;
    Animation anim_logo, anim_image, anim_slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        init();
        ivLogo.setAnimation(anim_image);
        tvLogo.setAnimation(anim_logo);
        tvSLogan.setAnimation(anim_slogan);

        new Handler()
                .postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, Dashboard.class));
                finish();
            }
        }, 2000);
    }
    private void init()
    {
        ivLogo = findViewById(R.id.ivLogo);
        tvLogo = findViewById(R.id.tvLogo);
        tvSLogan = findViewById(R.id.tvSlogan);

        anim_image = AnimationUtils.loadAnimation(this, R.anim.logo_image);
        anim_logo = AnimationUtils.loadAnimation(this, R.anim.logo_title);
        anim_slogan = AnimationUtils.loadAnimation(this, R.anim.slogan);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}