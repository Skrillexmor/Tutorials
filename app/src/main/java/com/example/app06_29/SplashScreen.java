package com.example.app06_29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_TIMER = 3500;
    //variables
    ImageView Image;
    TextView logoName, logoDesc;
    //Animation
    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        //hooks
        Image = findViewById(R.id.imageView);
        logoName = findViewById(R.id.Splash_textView);
        logoDesc = findViewById(R.id.Splash_textView2);

        //Animation hooks
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //set animations to elements
        logoName.setAnimation(bottomAnim);
        logoDesc.setAnimation(sideAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIMER);
    }
}