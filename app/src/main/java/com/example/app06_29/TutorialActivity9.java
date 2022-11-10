package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TutorialActivity9 extends AppCompatActivity {

    TextView tt9_textView_show;
    Button view_assets, readFiles, writeFiles;
    TextInputLayout content_label;
    EditText content_edittext;
    ListView listView;
    String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial9);

        //Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorial 9");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        view_assets = findViewById(R.id.tt9_ViewAssets_Button);
        readFiles = findViewById(R.id.tt9_read_btn);
        writeFiles = findViewById(R.id.tt9_write_btn);
        content_label = findViewById(R.id.tt9_content_label);
        content_edittext = findViewById(R.id.tt9_content_edittext);
        tt9_textView_show = findViewById(R.id.tt9_textView_show);
        listView = findViewById(R.id.List_View);

        view_assets.setOnClickListener(v -> {
            listView.setVisibility(View.VISIBLE);
            tt9_textView_show.setText(null);
            LoadJsonFromAsset();
            try {
                JSONObject obj = new JSONObject(LoadJsonFromAsset());
                JSONArray array = obj.getJSONArray("formulas");
                HashMap<String, String> list;
                ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    String formule_v = o.getString("formula");
                    String url = o.getString("url");
                    list = new HashMap<>();
                    list.put("formula", formule_v);
                    list.put("url", url);
                    arrayList.add(list);
                }

                ListAdapter adapter = new SimpleAdapter(this, arrayList, R.layout.tt9_shoow_layout, new String[]{"formula", "url"}, new int[]{R.id.formula, R.id.url});
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        readFiles.setOnClickListener(v -> {
            listView.setVisibility(View.INVISIBLE);
            loadData();
        });

        writeFiles.setOnClickListener(v -> {
            if (!validateContentData()) {
                return;
            }
            saveData();
        });
    }

    public String LoadJsonFromAsset() {
        String json = null;
        try {
            InputStream in = this.getAssets().open("data.json");
            int size = in.available();
            byte[] bbuffer = new byte[size];
            in.read(bbuffer);
            in.close();
            json = new String(bbuffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    private void loadData() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String val;
            while ((val = bufferedReader.readLine()) != null) {
                stringBuilder.append(val).append("\n");
            }
            tt9_textView_show.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String val = tt9_textView_show.getText().toString().trim();
        if(val.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveData() {
        tt9_textView_show.setText(null);
        String val = content_edittext.getText().toString();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fileOutputStream.write(val.getBytes());
            content_edittext.getText().clear();
            content_edittext.requestFocus();
            Toast.makeText(this, "saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean validateContentData() {
        String val = content_edittext.getText().toString();
        if (val.isEmpty()) {
            content_label.setError("Content cannot be empty");
            return false;
        } else {
            content_label.setError(null);
            content_label.setErrorEnabled(false);
            return true;
        }
    }
}