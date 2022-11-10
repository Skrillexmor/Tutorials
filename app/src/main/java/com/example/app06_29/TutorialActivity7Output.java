package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class TutorialActivity7Output extends AppCompatActivity {

    Button logout_btn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_activity7_output);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorial 7 Output");

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        logout_btn = findViewById(R.id.tt7_logout_btn);
        sharedPreferences = getSharedPreferences("Tutorial7", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        logout_btn.setOnClickListener(v -> {
            editor.clear();
            editor.commit();
            Toast.makeText(TutorialActivity7Output.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TutorialActivity7Output.this, TutorialActivity7.class));
            finish();
        });
    }
}