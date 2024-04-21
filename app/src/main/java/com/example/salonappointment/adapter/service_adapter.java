package com.example.salonappointment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.register_service_model;
import com.example.salonappointment.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class service_adapter extends FirebaseRecyclerAdapter<register_service_model, service_adapter.ViewHolder> {
    public service_adapter(@NonNull FirebaseRecyclerOptions<register_service_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull register_service_model model) {
        viewHolder.name.setText(model.getServiceName());
        viewHolder.description.setText(model.getDescription());
        viewHolder.price.setText(String.valueOf(model.getPrice()));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, description, price;
        ImageView imgService;//pang abang
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_servicevr_serviceName);
            description = itemView.findViewById(R.id.tv_servicevr_serviceDescription);
            price = itemView.findViewById(R.id.tv_servicevr_price);
        }
    }
}
