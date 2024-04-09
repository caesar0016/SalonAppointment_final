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

import java.util.ArrayList;
import java.util.zip.Inflater;

public class service_adapter extends RecyclerView.Adapter<service_adapter.ViewHolder> {

    private ArrayList<register_service_model> serviceList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(serviceList.get(position).getServiceName());
        holder.description.setText(serviceList.get(position).getDescription());
        holder.price.setText(String.valueOf(serviceList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }
    public service_adapter(ArrayList<register_service_model> serviceList) {
        this.serviceList = serviceList;
        notifyDataSetChanged();
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
