package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class TutorialActivity3 extends AppCompatActivity {

    EditText user_txt, pass_txt;
    Button btn_login;
    TextInputLayout uname, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial3);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setTitle("Tutorial 3");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);
        btn_login = findViewById(R.id.Login_Button);
        user_txt = findViewById(R.id.username1);
        pass_txt = findViewById(R.id.password);
        uname = findViewById(R.id.uname_label);
        pass = findViewById(R.id.pass_label);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = user_txt.getText().toString();
                String s1 = pass_txt.getText().toString();
                if (!validateUserNameData()) {
                    return;
                }
                if (!validatePasswordData()) {
                    return;
                }
                if (s.equals("admin@gmail.com") && s1.equals("admin")) {
                    Intent i = new Intent(getApplicationContext(), TutorialActivity3Output.class);
                    Bundle b = new Bundle();
                    b.putString("username_get", s);
                    b.putString("password_get", s1);
                    i.putExtras(b);
                    i.putExtras(b);
                    startActivity(i);
                } else {
                    Toast.makeText(TutorialActivity3.this, "Username & Password\n\tare wrong try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateUserNameData() {
        String val = user_txt.getText().toString().trim();
        if (val.isEmpty()) {
            uname.setError("Field cannot be empty");
            return false;
        } else {
            uname.setError(null);
            uname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePasswordData() {
        String val = pass_txt.getText().toString().trim();
        if (val.isEmpty()) {
            pass.setError("Field cannot be empty");
            return false;
        } else {
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        }
    }
}