package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class reg_sched_adapter extends RecyclerView.Adapter<reg_sched_adapter.ViewHolder> {
    Context context;
    ArrayList<staffSched_model> listSched = new ArrayList<>();

    public reg_sched_adapter(ArrayList<staffSched_model> listSched) {
        this.listSched = listSched;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sched_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.regSched_startTime.setText(listSched.get(position).getStartTime());
        holder.regSched_startAmOrPm.setText(listSched.get(position).getStartAmOrPm());
        holder.regSched_endTime.setText(listSched.get(position).getEndTime());
        holder.regSched_endTimeAmOrPm.setText(listSched.get(position).getEndAmOrPm());
    }

    @Override
    public int getItemCount() {
        return listSched.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView regSched_Num, regSched_startTime, regSched_startAmOrPm, regSched_endTime, regSched_endTimeAmOrPm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // regSched_Num = itemView.findViewById(R.id.regSched_Num);
            regSched_startTime = itemView.findViewById(R.id.regSched_startTime);
            regSched_startAmOrPm = itemView.findViewById(R.id.regSched_startAmOrPm);
            regSched_endTime = itemView.findViewById(R.id.regSched_endTime);
            regSched_endTimeAmOrPm = itemView.findViewById(R.id.regSched_endTimeAmOrPm);
        }
    }
}
