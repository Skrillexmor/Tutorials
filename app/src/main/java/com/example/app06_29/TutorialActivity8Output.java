package com.example.app06_29;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class TutorialActivity8Output extends AppCompatActivity {

    DataBaseHelperClass dataBaseHelperClass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_activity8_output);

        //Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorial 8 Output");

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        recyclerView = findViewById(R.id.tt8_recycler_view);
        sharedPreferences = getSharedPreferences("Tutorial8", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        dataBaseHelperClass = new DataBaseHelperClass(TutorialActivity8Output.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(TutorialActivity8Output.this));

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        recyclerView.setLayoutAnimation(animation);

        DataBaseHelperClass dataBaseHelperClass = new DataBaseHelperClass(this);
        List<tt7UserHelperClass> tt7UserHelperClasses = dataBaseHelperClass.getUsers();

        if (tt7UserHelperClasses.size() > 0) {
            customAdapter = new CustomAdapter(tt7UserHelperClasses, TutorialActivity8Output.this);
            recyclerView.setAdapter(customAdapter);
        } else {
            Toast.makeText(this, "No service available", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tt6_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.tt6_logout) {
            editor.clear();
            editor.commit();
            Toast.makeText(TutorialActivity8Output.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TutorialActivity8Output.this, TutorialActivity8.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}