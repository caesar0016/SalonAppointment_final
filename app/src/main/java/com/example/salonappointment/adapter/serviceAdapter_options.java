package com.example.salonappointment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.Model.register_service_model;
import com.example.salonappointment.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class serviceAdapter_options extends FirebaseRecyclerAdapter<register_service_model, serviceAdapter_options.ViewHolder> {
    public serviceAdapter_options(@NonNull FirebaseRecyclerOptions<register_service_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull register_service_model model) {
        viewHolder.serviceName.setText(model.getServiceName());
        viewHolder.description.setText(model.getDescription());
        viewHolder.price.setText(String.valueOf(model.getReservationPrice()));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, description, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.tv_servicevr_serviceName);
            description = itemView.findViewById(R.id.tv_servicevr_serviceDescription);
        }
    }
}
