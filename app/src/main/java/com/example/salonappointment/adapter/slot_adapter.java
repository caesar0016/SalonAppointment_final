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

public class slot_adapter extends RecyclerView.Adapter<slot_adapter.Viewholder>{
    private Context context;
    private ArrayList<staffSched_model> listSched = new ArrayList<>();

    public slot_adapter(Context context, ArrayList<staffSched_model> listSched) {
        this.context = context;
        this.listSched = listSched;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_view_rows, parent, false);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tvTime.setText(listSched.get(position).getTime());
        holder.cvSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You click the service", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSched.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        private TextView tvTime;
        private CardView cvSched;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            tvTime = itemView.findViewById(R.id.slotTime);
            cvSched = itemView.findViewById(R.id.slot_cardView);
        }
    }
}
