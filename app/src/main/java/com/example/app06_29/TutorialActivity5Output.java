package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class TutorialActivity5Output extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_activity5_output);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Output");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            String str1 = b.getString("tt5_fname_get");
            String str2 = b.getString("tt5_lname_get");
            String str3 = b.getString("tt5_email_get");
            String str4 = b.getString("tt5_pass_get");
            String str5 = b.getString("tt5_branch_get");
            String str6 = b.getString("tt5_gender_get");
            String str7 = b.getString("tt5_city_get");
            String str8 = b.getString("tt5_status_get");
            TextView tv1 = findViewById(R.id.tt5_text1);
            TextView tv2 = findViewById(R.id.tt5_text2);
            TextView tv3 = findViewById(R.id.tt5_text3);
            TextView tv4 = findViewById(R.id.tt5_text4);
            TextView tv5 = findViewById(R.id.tt5_text5);
            TextView tv6 = findViewById(R.id.tt5_text6);
            TextView tv7 = findViewById(R.id.tt5_text7);
            TextView tv8 = findViewById(R.id.tt5_text8);
            tv1.append(str1);
            tv2.append(str2);
            tv3.append(str3);
            tv4.append(str4);
            tv5.append(str5);
            tv6.append(str6);
            tv7.append(str7);
            tv8.append(str8);
        }
    }
}