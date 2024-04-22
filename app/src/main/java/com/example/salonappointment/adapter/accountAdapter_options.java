package com.example.salonappointment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class accountAdapter_options extends FirebaseRecyclerAdapter<register_acc_model, accountAdapter_options.ViewHolder> {
    public accountAdapter_options(@NonNull FirebaseRecyclerOptions<register_acc_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull register_acc_model model) {
        viewHolder.name.setText(model.getName());
        viewHolder.userRole.setText(model.getUserType());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stylist_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, userRole;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvStylistName_stylistvr);
            userRole = itemView.findViewById(R.id.tvStylistName_role);
        }
    }
}
