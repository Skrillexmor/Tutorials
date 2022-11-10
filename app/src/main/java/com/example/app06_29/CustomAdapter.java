package com.example.app06_29;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<tt7UserHelperClass> user;
    Context context;
    com.example.app06_29.DataBaseHelperClass dataBaseHelperClass;

    public CustomAdapter(List<tt7UserHelperClass> user, Context context) {
        this.user = user;
        this.context = context;
        dataBaseHelperClass = new com.example.app06_29.DataBaseHelperClass(context);
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tt8_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        final tt7UserHelperClass tt7UserHelperClass = user.get(position);

        holder.fname_show.setText(tt7UserHelperClass.getFname());
        holder.lname_show.setText(tt7UserHelperClass.getLname());
        holder.email_show.setText(tt7UserHelperClass.getEmail());
        holder.pass_show.setText(tt7UserHelperClass.getPass());
        holder.branch_show.setText(tt7UserHelperClass.getBranch());
        holder.gender_show.setText(tt7UserHelperClass.getGender());

        /*holder.call_btn.setOnClickListener(v -> {
            String phone = postModalClass.getPhone();
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone));
            context.startActivity(callIntent);
        });*/
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fname_show, lname_show, email_show, pass_show, branch_show, gender_show;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fname_show = itemView.findViewById(R.id.tt8_fname_show);
            lname_show = itemView.findViewById(R.id.tt8_lname_show);
            email_show = itemView.findViewById(R.id.tt8_email_show);
            pass_show = itemView.findViewById(R.id.tt8_pass_show);
            branch_show = itemView.findViewById(R.id.tt8_branch_show);
            gender_show = itemView.findViewById(R.id.tt8_gender_show);
        }
    }
}