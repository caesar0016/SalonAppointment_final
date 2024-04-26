package com.example.salonappointment.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;

import java.util.ArrayList;

public class slot_adapter extends RecyclerView.Adapter<slot_adapter.ViewHolder> {
    private ArrayList<staffSched_model> listSlot = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.startTime.setText(listSlot.get(position).getStartTime());
        holder.startAmOrPm.setText(listSlot.get(position).getStartAmOrPm());
        holder.endTime.setText(listSlot.get(position).getEndTime());
        holder.endAmOrPm.setText(listSlot.get(position).getEndAmOrPm());
    }

    @Override
    public int getItemCount() {
        Log.d("Data Size", "Size: " + listSlot.size());
        return listSlot.size();
    }

    public slot_adapter(ArrayList<staffSched_model> listSlot) {
        this.listSlot = listSlot;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView startTime, startAmOrPm, endTime, endAmOrPm;
        CardView cvContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.slot_tvStartTime);
            startAmOrPm = itemView.findViewById(R.id.slot_tvStartAmOrPm);
            endTime = itemView.findViewById(R.id.slot_tvEndTime);
            endAmOrPm = itemView.findViewById(R.id.slot_tvEndAmorPm);
            cvContainer = itemView.findViewById(R.id.slot_cardView);

        }
    }
}
