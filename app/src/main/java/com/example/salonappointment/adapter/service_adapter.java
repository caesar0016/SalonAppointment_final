package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salonappointment.Model.register_service_model;
import com.example.salonappointment.R;

import java.util.ArrayList;

public class service_adapter extends RecyclerView.Adapter<service_adapter.ViewHolder> {
    ArrayList<register_service_model> listService = new ArrayList<>();
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(listService.get(position).getServiceName());
        holder.description.setText(listService.get(position).getDescription());

        String imageUrl = listService.get(position).getPicture();
        Glide.with(context).load(imageUrl).into(holder.imgService);
        holder.cvService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Click Services", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listService.size();
    }

    public service_adapter(Context context, ArrayList<register_service_model> listService) {
        this.context = context;
        this.listService = listService;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView imgService; //pang abang
        CardView cvService;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_servicevr_serviceName);
            description = itemView.findViewById(R.id.tv_servicevr_serviceDescription);
            imgService = itemView.findViewById(R.id.img_servicevr_dpService);
            cvService = itemView.findViewById(R.id.serviceVr_cardView);

        }
    }
}
