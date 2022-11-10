package com.example.app06_29;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class TutorialActivity12 extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    TT12Adapter tt12Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial12);

        //Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        getSupportActionBar().setTitle("Tutorial 12");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        recyclerView = findViewById(R.id.rcv_users);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        recyclerView.setLayoutAnimation(animation);

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