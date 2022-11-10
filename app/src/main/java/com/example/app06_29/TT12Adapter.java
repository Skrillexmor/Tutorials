package com.example.app06_29;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TT12Adapter extends RecyclerView.Adapter<TT12Adapter.MyViewHolder> {
    JSONArray jsonArray;

    public TT12Adapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @NonNull
    @Override
    public TT12Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tutorial_12_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TT12Adapter.MyViewHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.txtName.setText(jsonObject.getString("name"));
            holder.txtEmail.setText(jsonObject.getString("email"));
            holder.txtPhone.setText(jsonObject.getString("phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtEmail, txtPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tt12_heading_show);
            txtEmail = itemView.findViewById(R.id.tt12_subhead1_show);
            txtPhone = itemView.findViewById(R.id.tt12_subhead2_show);
        }
    }
}
