package com.example.app06_29;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;


public class Tutorial_Menu extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    Button tt8_open, tt9_open, tt10_open, tt12_open, tt13_open;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_menu);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorials : ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        swipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);
        tt8_open = findViewById(R.id.tt8_open);
        tt9_open = findViewById(R.id.tt9_open);
        tt10_open = findViewById(R.id.tt10_open);
        tt12_open = findViewById(R.id.tt12_open);
        tt13_open = findViewById(R.id.tt13_open);
        constraintLayout = findViewById(R.id.main_screen);

        int resId = R.anim.layout_animation_from_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        constraintLayout.setLayoutAnimation(animation);

        tt8_open.setOnClickListener(view -> startActivity(new Intent(Tutorial_Menu.this, TutorialActivity8.class)));

        tt9_open.setOnClickListener(view -> startActivity(new Intent(Tutorial_Menu.this, TutorialActivity9.class)));

        tt10_open.setOnClickListener(view -> startActivity(new Intent(Tutorial_Menu.this, TutorialActivity10.class)));

        tt12_open.setOnClickListener(view -> startActivity(new Intent(Tutorial_Menu.this, TutorialActivity12.class)));

        tt13_open.setOnClickListener(view -> startActivity(new Intent(Tutorial_Menu.this, TutorialActivity13.class)));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            Toast.makeText(Tutorial_Menu.this, "Tutorials Updated Successfully", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        });

    }

    public void tt1_Call(View view) {
        startActivity(new Intent(Tutorial_Menu.this, TutorialActivity1.class));
    }

    public void tt1_code_btn(View view) {
        String urL = "https://github.com/Vpadia717/App06_29/blob/master/app/src/main/java/com/example/app06_29/TutorialActivity1.java";
        Uri webpge = Uri.parse(urL);
        Intent i = new Intent(Intent.ACTION_VIEW, webpge);
        startActivity(i);
    }

    public void tt2_Call(View view) {
        startActivity(new Intent(Tutorial_Menu.this, TutorialActivity2.class));
    }

    public void tt2_code_btn(View view) {
        String urL = "https://github.com/Vpadia717/App06_29/blob/master/app/src/main/res/layout/activity_tutorial2.xml";
        Uri webpge = Uri.parse(urL);
        Intent i = new Intent(Intent.ACTION_VIEW, webpge);
        startActivity(i);
    }

    public void tt3_Call(View view) {
        startActivity(new Intent(Tutorial_Menu.this, TutorialActivity3.class));
    }

    public void tt3_code_btn(View view) {
        String urL = "https://github.com/Vpadia717/App06_29/blob/master/app/src/main/java/com/example/app06_29/TutorialActivity3.java";
        Uri webpge = Uri.parse(urL);
        Intent i = new Intent(Intent.ACTION_VIEW, webpge);
        startActivity(i);
    }

    public void tt4_Call(View view) {
        Intent i = new Intent(Tutorial_Menu.this, TutorialActivity4.class);
        startActivity(i);
    }

    public void tt4_code_btn(View view) {
        String urL = "https://github.com/Vpadia717/App06_29/blob/master/app/src/main/java/com/example/app06_29/TutorialActivity4.java";
        Uri webpge = Uri.parse(urL);
        new Intent(Intent.ACTION_VIEW, webpge);
    }

    public void tt5_Call(View view) {
        Intent i = new Intent(Tutorial_Menu.this, TutorialActivity5.class);
        startActivity(i);
    }

    public void tt5_code_btn(View view) {
        String urL = "https://github.com/Vpadia717/App06_29/blob/master/app/src/main/java/com/example/app06_29/TutorialActivity5.java";
        Uri webpge = Uri.parse(urL);
        Intent i = new Intent(Intent.ACTION_VIEW, webpge);
        startActivity(i);
    }

    public void tt6_Call(View view) {
        Intent i = new Intent(Tutorial_Menu.this, TutorialActivity6.class);
        startActivity(i);
    }

    public void tt6_code_btn(View view) {
        String urL = "https://github.com/Vpadia717/App06_29/blob/master/app/src/main/java/com/example/app06_29/TutorialActivity6.java";
        Uri webpge = Uri.parse(urL);
        Intent i = new Intent(Intent.ACTION_VIEW, webpge);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(this, item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.home_main_menu:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.menu_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://github.com/DharamBhojani/android";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;
            case R.id.menu_call:
                String phone = "6355762194";
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
                return true;
            case R.id.menu_code:
                String urL = "https://github.com/DharamBhojani/android";
                Uri webpge = Uri.parse(urL);
                Intent i = new Intent(Intent.ACTION_VIEW, webpge);
                startActivity(i);
                return true;
            case R.id.menu_help:
                String urlText = "http://21SOECE13030.epizy.com";
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tt7_Call(View view) {
        startActivity(new Intent(Tutorial_Menu.this, TutorialActivity7.class));
    }

    public void tt13_code_btn(View view) {
        String urL = "https://github.com/Vpadia717/App06_29/blob/master/app/src/main/java/com/example/app06_29/TutorialActivity13.java";
        Uri webpge = Uri.parse(urL);
        Intent i = new Intent(Intent.ACTION_VIEW, webpge);
        startActivity(i);

    }
}