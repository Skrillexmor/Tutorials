package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class TutorialSignUpActivity7 extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String[] branch_array = {"Computer Engineering", "Information Technology", "Other"};
    DataBaseHelperClass dataBaseHelperClass;
    ProgressBar progressBar;
    RadioButton male_btn, female_btn;
    String tt7_fname, tt7_lname, tt7_email, tt7_pass, tt7_branch, tt7_gender;
    EditText fname_txt, lname_txt, email_txt, pass_txt;
    Button btn_signUp, btn_login;
    MaterialAutoCompleteTextView materialAutoCompleteTextView;
    TextInputLayout tt7_fname_label, tt7_lname_label, tt7_email_label, tt7_pass_label, tt7_branch_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_sign_up7);

        //Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorial 7 Sign Up");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        tt7_fname_label = findViewById(R.id.tt7_fname_label);
        fname_txt = findViewById(R.id.tt7_fname);
        tt7_lname_label = findViewById(R.id.tt7_lname_label);
        lname_txt = findViewById(R.id.tt7_lname);
        tt7_email_label = findViewById(R.id.signup_tt7_email_label);
        email_txt = findViewById(R.id.signup_tt7_email);
        tt7_pass_label = findViewById(R.id.signup_tt7_pass_label);
        pass_txt = findViewById(R.id.signup_tt7_password);
        tt7_branch_label = findViewById(R.id.tt7_branch_label);
        male_btn = findViewById(R.id.male_radio);
        female_btn = findViewById(R.id.female_radio);

        btn_signUp = findViewById(R.id.signup_tt7_signUp_btn);
        btn_login = findViewById(R.id.tt7_Reg_Log_Button);
        progressBar = findViewById(R.id.tt7_reg_progressbar);
        progressBar.setVisibility(View.GONE);

        materialAutoCompleteTextView = findViewById(R.id.tt7_AutoCompleteTextView);
        dataBaseHelperClass = new DataBaseHelperClass(this);

        adapter = new ArrayAdapter<>(this, R.layout.branch_view_layout, branch_array);
        materialAutoCompleteTextView.setAdapter(adapter);

        btn_signUp.setOnClickListener(v -> {
            String val = materialAutoCompleteTextView.getText().toString();
            if (!validateFnameData() | !validateLnameData() | !validateEmailData() | !validatePasswordData() | !validateBranchData(val)) {
                if (!male_btn.isSelected() | !female_btn.isSelected()) {
                    Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
                } else if (male_btn.isSelected()) {
                    tt7_gender = male_btn.getText().toString();
                } else if (female_btn.isSelected()) {
                    tt7_gender = female_btn.getText().toString();
                }
                return;
            }

            tt7_fname = fname_txt.getText().toString();
            tt7_lname = lname_txt.getText().toString();
            tt7_email = email_txt.getText().toString();
            tt7_pass = pass_txt.getText().toString();
            tt7_branch = materialAutoCompleteTextView.getText().toString();

            boolean value = dataBaseHelperClass.checkEmail(tt7_email);
            if (value) {
                tt7_email_label.setError("Email is associated with another account");
            } else {
                tt7_email_label.setError(null);
                tt7_email_label.setErrorEnabled(false);
                tt7UserHelperClass tt7UserHelperClass = new tt7UserHelperClass(tt7_fname, tt7_lname, tt7_email, tt7_pass, tt7_branch, tt7_gender);
                boolean bol = dataBaseHelperClass.addUser(tt7UserHelperClass);
                if (bol) {
                    Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(TutorialSignUpActivity7.this, TutorialActivity7.class));
                    finish();
                } else {
                    Toast.makeText(this, "some thing went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_login.setOnClickListener(v -> startActivity(new Intent(TutorialSignUpActivity7.this, TutorialActivity7.class)));
    }

    //Validating First Name
    private boolean validateFnameData() {
        String val = fname_txt.getText().toString().trim();
        if (val.isEmpty()) {
            tt7_fname_label.setError("First name cannot be empty");
            return false;
        } else {
            tt7_fname_label.setError(null);
            tt7_fname_label.setErrorEnabled(false);
            return true;
        }
    }

    //Validating Last Name
    private boolean validateLnameData() {
        String val = lname_txt.getText().toString().trim();
        if (val.isEmpty()) {
            tt7_lname_label.setError("Last name cannot be empty");
            return false;
        } else {
            tt7_lname_label.setError(null);
            tt7_lname_label.setErrorEnabled(false);
            return true;
        }
    }

    //Validating Email
    private boolean validateEmailData() {
        String val = email_txt.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            tt7_email_label.setError("Email cannot be empty");
            return false;
        } else if (val.matches(emailPattern)) {
            tt7_email_label.setError(null);
            tt7_email_label.setErrorEnabled(false);
            return true;
        } else {
            tt7_email_label.setError("Not a valid email");
            return false;
        }
    }

    //Validating Password
    private boolean validatePasswordData() {
        String val = pass_txt.getText().toString().trim();
        if (val.isEmpty()) {
            tt7_pass_label.setError("Password cannot be empty");
            return false;
        } else if (val.length() < 9 & val.length() > 7) {
            tt7_pass_label.setError(null);
            tt7_pass_label.setErrorEnabled(false);
            return true;
        } else {
            tt7_pass_label.setError("Password must be of 8 characters");
            return false;
        }
    }

    //Validating Name
    private boolean validateBranchData(String val) {
        if (val.isEmpty()) {
            tt7_branch_label.setError("Branch cannot be empty");
            return false;
        } else {
            tt7_branch_label.setError(null);
            tt7_branch_label.setErrorEnabled(false);
            return true;
        }
    }
}