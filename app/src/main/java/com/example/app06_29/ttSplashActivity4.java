package com.example.app06_29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ttSplashActivity4 extends AppCompatActivity {
    private static int SPLASH_TIMER = 3500;
    //variables
    ImageView Image;
    TextView logoName;
    //Animation
    Animation sideAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt_splash4);
        getSupportActionBar().hide();
        //hooks
        Image = findViewById(R.id.tt4_imageView);
        logoName = findViewById(R.id.tt4_textView);

        //Animation hooks
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //set animations to elements
        Image.setAnimation(sideAnim);
        logoName.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ttSplashActivity4.this, TutorialActivity4.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }
}