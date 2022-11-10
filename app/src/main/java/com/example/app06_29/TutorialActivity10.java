package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

public class TutorialActivity10 extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    TT12Adapter tt12Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial10);
        //Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorial 10");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        recyclerView = findViewById(R.id.rcv_tt10);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        volleyNetworkCallAPI();
    }

    private void volleyNetworkCallAPI() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, TT12MyUtil.URL_USERS, null, response -> {
            TT12MyUtil.userdata = response;
            tt12Adapter = new TT12Adapter(response);
            recyclerView.setAdapter(tt12Adapter);
        }, error -> {

        });
        requestQueue.add(jsonArrayRequest);
    }
}