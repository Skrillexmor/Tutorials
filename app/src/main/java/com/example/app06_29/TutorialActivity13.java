package com.example.app06_29;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class TutorialActivity13 extends AppCompatActivity {
    Button message_btn, call_btn;
    TextInputLayout phone_label, message_label;
    EditText phone_txt, message_txt;
    String call_string, message_string;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial13);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        getSupportActionBar().setTitle("Tutorial 13");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        message_btn = findViewById(R.id.tt13_Message_Button);
        call_btn = findViewById(R.id.tt13_Call_Button);
        phone_label = findViewById(R.id.tt13_phone_label);
        phone_txt = findViewById(R.id.tt13_phone_edittext);
        message_label = findViewById(R.id.tt13_message_label);
        message_txt = findViewById(R.id.tt13_message_edittext);

        call_btn.setOnClickListener(view -> {
            if (!validatePhoneData()) {
                return;
            }
            call_string = phone_txt.getText().toString().trim();
            if (checkPhonePermission(Manifest.permission.CALL_PHONE)) {
                String dial = "tel:" + call_string;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            } else {
                Toast.makeText(TutorialActivity13.this, "Calling permission denied", Toast.LENGTH_SHORT).show();
            }
        });

        message_btn.setOnClickListener(view -> {
            if (!validateMessageData() | !validatePhoneData()) {
                return;
            }
            message_string = message_txt.getText().toString().trim();
            if (ContextCompat.checkSelfPermission(TutorialActivity13.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                sendMessage();
            } else {
                ActivityCompat.requestPermissions(TutorialActivity13.this, new String[]{Manifest.permission.SEND_SMS}, 100);
            }
        });

        if (checkPhonePermission(Manifest.permission.CALL_PHONE)) {
            call_btn.setEnabled(true);
        } else {
            call_btn.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);
        }
        if (checkMessagePermission(Manifest.permission.SEND_SMS)) {
            message_btn.setEnabled(true);
        } else {
            message_btn.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MAKE_CALL_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendMessage();
        } else {
            Toast.makeText(this, "SMS permission denied.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendMessage() {
        if (!call_string.equals("") && !message_string.equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(call_string, null, message_string, null, null);
            Toast.makeText(this, "SMS SENT SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sorry", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPhonePermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkMessagePermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    //Validating Phone
    private boolean validatePhoneData() {
        String val = Objects.requireNonNull(phone_txt.getText()).toString().trim();
        if (val.isEmpty()) {
            phone_label.setError("Phone cannot be empty");
            return false;
        } else if (val.length() < 11 & val.length() > 9) {
            phone_label.setError(null);
            phone_label.setErrorEnabled(false);
            return true;
        } else {
            phone_label.setError("Phone must be of 10 digits");
            return false;
        }
    }

    //Validating Message
    private boolean validateMessageData() {
        String val = Objects.requireNonNull(message_txt.getText()).toString().trim();
        if (val.isEmpty()) {
            message_label.setError("Message cannot be empty");
            return false;
        } else {
            message_label.setError(null);
            message_label.setErrorEnabled(false);
            return true;
        }
    }

}