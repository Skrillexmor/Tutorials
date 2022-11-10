package com.example.app06_29;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    ShapeableImageView QR_photo;
    Button QR_Scan, tt_btn, btn_Call, btn_Code, btn_Help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //hooks
        //QR_photo = findViewById(R.id.QR_image);
        QR_Scan = findViewById(R.id.QR_scan);
        tt_btn = findViewById(R.id.elevatedButton);
        btn_Call = findViewById(R.id.btn_Call);
        btn_Code = findViewById(R.id.btn_Code);
        btn_Help = findViewById(R.id.btn_Help);

        QR_Scan.setOnClickListener(v -> {
            String urL = "https://github.com/DharamBhojani/android";
            Uri webpge = Uri.parse(urL);
            Intent i = new Intent(Intent.ACTION_VIEW, webpge);
            startActivity(i);
        });

        tt_btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Tutorial_Menu.class)));

        btn_Call.setOnClickListener(v -> {
            String phone = "6355762194";
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        });

        btn_Code.setOnClickListener(v -> {
            String urL = "https://github.com/DharamBhojani/android";
            Uri webpge = Uri.parse(urL);
            Intent i = new Intent(Intent.ACTION_VIEW, webpge);
            startActivity(i);
        });

        btn_Help.setOnClickListener(v -> {
            String urlText = "http://21SOECE13030.epizy.com";
            Uri webpage = Uri.parse(urlText);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
            materialAlertDialogBuilder.setBackground(getResources().getDrawable(R.drawable.dialog_background));
            materialAlertDialogBuilder.setTitle(R.string.app_name);
            materialAlertDialogBuilder.setMessage("Do you want to exit the app ?");
            materialAlertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "Exit Successfully", Toast.LENGTH_SHORT).show();
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });
            materialAlertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    backPressedTime = 0;
                }
            });
            materialAlertDialogBuilder.show();
            //Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}