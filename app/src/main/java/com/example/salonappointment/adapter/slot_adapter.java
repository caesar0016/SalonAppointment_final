package com.example.salonappointment.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;
import com.example.salonappointment.displayDetail.displayAppointment;
import com.example.salonappointment.registration.register_sched;

import java.util.ArrayList;

public class slot_adapter extends RecyclerView.Adapter<slot_adapter.ViewHolder> {
    private ArrayList<staffSched_model> listSlot = new ArrayList<>();
    private Context context;
    private boolean isSelected = false;  // Track selection state

    int row_index = -1;

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

        final int currentPosition = position;

        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startTime = listSlot.get(currentPosition).getStartTime();
                String startAmOrPm = listSlot.get(currentPosition).getStartAmOrPm();
                String endTime = listSlot.get(currentPosition).getEndTime();
                String endAmOrPm = listSlot.get(currentPosition).getEndAmOrPm();
                String totalTime = startTime + " " + startAmOrPm + " - " + endTime + " " + endAmOrPm;
                staffSched_model.FinalTime = totalTime;
                if (row_index != currentPosition) {
                    // Reset previously selected item's color
                    if (row_index != -1) {
                        notifyItemChanged(row_index);
                    }
                    row_index = currentPosition;

                    Toast.makeText(context, "Time: " + totalTime, Toast.LENGTH_SHORT).show();
                    holder.cvContainer.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.pink)));
                }

            }
        });

        // Set background color based on selection state
        holder.cvContainer.setBackgroundTintList(row_index == position ? ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.pink)) : null);
    }


    @Override
    public int getItemCount() {
        Log.d("Data Size", "Size: " + listSlot.size());
        return listSlot.size();
    }

    public slot_adapter(Context context, ArrayList<staffSched_model> listSlot) {
        this.context = context;
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
