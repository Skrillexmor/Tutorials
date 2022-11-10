package com.example.app06_29;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class TutorialActivity6Output extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_activity6_output);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setTitle("Output");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);
        sharedPreferences = getSharedPreferences("tt_6preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            String str1 = b.getString("tt6_username_get");
            String str2 = b.getString("tt6_password_get");
            TextView tv1 = findViewById(R.id.tt6_text1);
            TextView tv2 = findViewById(R.id.tt6_text2);
            tv1.append(str1);
            tv2.append(str2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tt6_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(this, item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
        if (item.getItemId() == R.id.tt6_logout) {
            editor.clear();
            editor.commit();
            Toast.makeText(this, "Logout SuccessFully.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, TutorialActivity6.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}