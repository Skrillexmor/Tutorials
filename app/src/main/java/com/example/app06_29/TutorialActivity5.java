package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;

public class TutorialActivity5 extends AppCompatActivity {
    Spinner btn_spinner;
    RadioButton radio_btn;
    RadioGroup btn_gender;
    EditText fname_txt, lname_txt, email_txt, pass_txt;
    Button btn_signUp;
    MaterialCheckBox check_btn;
    SwitchMaterial btn_switch;
    TextInputLayout tt5_fname_label, tt5_lname_label, tt5_email_label, tt5_pass_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial5);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Tutorial 5");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        tt5_fname_label = findViewById(R.id.tt5_fname_label);
        tt5_lname_label = findViewById(R.id.tt5_lname_label);
        tt5_email_label = findViewById(R.id.tt5_email_label);
        tt5_pass_label = findViewById(R.id.tt5_pass_label);
        btn_signUp = findViewById(R.id.tt5_signUp_btn);
        btn_gender = findViewById(R.id.tt5_radio_group);
        fname_txt = findViewById(R.id.tt5_fname);
        lname_txt = findViewById(R.id.tt5_lname);
        email_txt = findViewById(R.id.tt5_email);
        pass_txt = findViewById(R.id.tt5_password);
        check_btn = findViewById(R.id.materialCheckBox);
        btn_switch = findViewById(R.id.tt5_switch);
        btn_spinner = findViewById(R.id.tt5_spinner_city);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tt5_fname = fname_txt.getText().toString();
                String tt5_lname = lname_txt.getText().toString();
                String tt5_email = email_txt.getText().toString();
                String tt5_pass = pass_txt.getText().toString();
                String city = null;
                String branch = null;
                String gender = null;
                String status = null;

                //Edit text Call
                if (!validateFnameData()) {
                    return;
                }
                if (!validateLnameData()) {
                    return;
                }
                if (!validateEmailData()) {
                    return;
                }
                if (!validatett5PasswordData()) {
                    return;
                }

                //Switch Call
                if (btn_switch.isChecked()) {
                    branch = btn_switch.getTextOn().toString();
                } else {
                    branch = btn_switch.getTextOff().toString();
                }

                //Radio button Call
                int id = btn_gender.getCheckedRadioButtonId();
                radio_btn = findViewById(id);
                if (id != -1) {
                    gender = radio_btn.getText().toString();
                } else {
                    Toast.makeText(TutorialActivity5.this, "Select the gender", Toast.LENGTH_SHORT).show();
                }

                //Spinner Call
                city = btn_spinner.getSelectedItem().toString();

                //Check box Call
                if (check_btn.isChecked()) {
                    status = "Active";
                } else {
                    status = "Inactive";
                }

                if (!tt5_fname.isEmpty() && !tt5_lname.isEmpty() && !tt5_email.isEmpty() && !tt5_pass.isEmpty()) {
                    Intent i = new Intent(getApplicationContext(), TutorialActivity5Output.class);
                    Bundle b = new Bundle();
                    b.putString("tt5_fname_get", tt5_fname);
                    b.putString("tt5_lname_get", tt5_lname);
                    b.putString("tt5_email_get", tt5_email);
                    b.putString("tt5_pass_get", tt5_pass);
                    b.putString("tt5_branch_get", branch);
                    b.putString("tt5_gender_get", gender);
                    b.putString("tt5_status_get", status);
                    b.putString("tt5_city_get", city);
                    i.putExtras(b);
                    i.putExtras(b);
                    i.putExtras(b);
                    i.putExtras(b);
                    i.putExtras(b);
                    i.putExtras(b);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });
    }

    private boolean validateFnameData() {
        String val = fname_txt.getText().toString().trim();
        if (val.isEmpty()) {
            tt5_fname_label.setError("First name cannot be empty");
            return false;
        } else {
            tt5_fname_label.setError(null);
            tt5_fname_label.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateLnameData() {
        String val = lname_txt.getText().toString().trim();
        if (val.isEmpty()) {
            tt5_lname_label.setError("Last name cannot be empty");
            return false;
        } else {
            tt5_lname_label.setError(null);
            tt5_lname_label.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmailData() {
        String val = email_txt.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            tt5_email_label.setError("Email cannot be empty");
            return false;
        } else if (val.matches(emailPattern)) {
            tt5_email_label.setError(null);
            tt5_email_label.setErrorEnabled(false);
            return true;
        } else {
            tt5_email_label.setError("Not a valid email");
            return false;
        }
    }

    private boolean validatett5PasswordData() {
        String val = pass_txt.getText().toString().trim();
        if (val.isEmpty()) {
            tt5_pass_label.setError("Password cannot be empty");
            return false;
        } else {
            tt5_pass_label.setError(null);
            tt5_pass_label.setErrorEnabled(false);
            return true;
        }
    }
}