package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class TutorialActivity7 extends AppCompatActivity {

    Button tt7_signup, tt7_login;
    EditText email_txt, password_txt;
    MaterialCheckBox remeber_me;
    TextInputLayout email_label, password_label;
    DataBaseHelperClass dataBaseHelperClass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial7);

        //Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorial7");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        tt7_signup = findViewById(R.id.tt7_SignUp_Button);
        tt7_login = findViewById(R.id.tt7_Login_Button);
        email_label = findViewById(R.id.tt7_email_label);
        email_txt = findViewById(R.id.tt7_email_edittext);
        password_label = findViewById(R.id.tt7_pass_label);
        password_txt = findViewById(R.id.tt7_password);
        remeber_me = findViewById(R.id.tt7_remember_me);
        dataBaseHelperClass = new DataBaseHelperClass(this);

        sharedPreferences = getSharedPreferences("Tutorial7", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.contains("tt7_saved_email")) {
            startActivity(new Intent(TutorialActivity7.this, TutorialActivity7Output.class));
            Toast.makeText(this, "You are already logged in.", Toast.LENGTH_SHORT).show();
        } else {
            tt7_login.setOnClickListener(v -> {
                if (!validateEmailData() | !validatePasswordData()) {
                    return;
                }
                String email = email_txt.getText().toString();
                String pass = password_txt.getText().toString();
                boolean val1 = dataBaseHelperClass.checkEmail(email);
                if (val1 == true) {
                    boolean val = dataBaseHelperClass.checkLogin(email, pass);
                    if (val == true) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                        if (remeber_me.isChecked()) {
                            editor.putString("tt7_saved_email", email);
                            editor.commit();
                        }
                        startActivity(new Intent(TutorialActivity7.this, TutorialActivity7Output.class));
                    } else {
                        Toast.makeText(this, "Email or Password is wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No such user exits", Toast.LENGTH_SHORT).show();
                }
            });

            tt7_signup.setOnClickListener(v -> startActivity(new Intent(TutorialActivity7.this, TutorialSignUpActivity7.class)));
        }
    }

    private boolean validateEmailData() {
        String val = email_txt.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email_label.setError("Email cannot be empty");
            return false;
        } else if (val.matches(emailPattern)) {
            email_label.setError(null);
            email_label.setErrorEnabled(false);
            return true;
        } else {
            email_label.setError("Not a valid email");
            return false;
        }
    }

    private boolean validatePasswordData() {
        String val = password_txt.getText().toString().trim();
        if (val.isEmpty()) {
            password_label.setError("Password cannot be empty");
            return false;
        } else if (val.length() < 9 & val.length() > 7) {
            password_label.setError(null);
            password_label.setErrorEnabled(false);
            return true;
        } else {
            password_label.setError("Password must be of 8 characters");
            return false;
        }
    }
}