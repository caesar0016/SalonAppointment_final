package com.example.salonappointment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;
import com.example.salonappointment.displayDetail.displayStylist;

import java.util.ArrayList;

public class account_adapter extends RecyclerView.Adapter<account_adapter.ViewHolder> {
    private Context context;
    ArrayList<register_acc_model> accList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stylist_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(accList.get(position).getName());
        holder.role.setText(accList.get(position).getUserType());
        String imgUrl = accList.get(position).getProfileURl();

        Glide.with(context).load(imgUrl).into(holder.profile);

        final int currentPosition = position;
        holder.cvStylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, displayStylist.class);
                String stylist = accList.get(currentPosition).getName();
                String role = accList.get(currentPosition).getUserType();

                intent.putExtra("imgurl", imgUrl);
                intent.putExtra("stylist", stylist);
                intent.putExtra("role", role);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accList.size();
    }

    public account_adapter(Context context, ArrayList<register_acc_model> accList) {
        this.context = context;
        this.accList = accList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name, role, description;
        CardView cvStylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvStylistName_stylistvr);
            role = itemView.findViewById(R.id.tvStylistName_role);
            description = itemView.findViewById(R.id.tvDescription_stylistvr);
            cvStylist = itemView.findViewById(R.id.stylistvr_cardview);
            profile = itemView.findViewById(R.id.img_stylistvr_imgStylist);

        }
    }
}