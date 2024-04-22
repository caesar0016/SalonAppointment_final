package com.example.salonappointment.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class account_adapter extends RecyclerView.Adapter<account_adapter.ViewHolder> {
    Context context;
    ArrayList<register_acc_model> accList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stylist_view_rows,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(accList.get(position).getName());
        holder.role.setText(accList.get(position).getUserType());
    }

    @Override
    public int getItemCount() {
        return accList.size();
    }

    public account_adapter(ArrayList<register_acc_model> accList) {
        this.accList = accList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name, role, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvStylistName_stylistvr);
            role = itemView.findViewById(R.id.tvStylistName_role);
            description = itemView.findViewById(R.id.tvDescription_stylistvr);
        }
    }
}