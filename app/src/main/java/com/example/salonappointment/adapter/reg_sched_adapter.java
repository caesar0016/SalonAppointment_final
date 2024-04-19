package com.example.salonappointment.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class reg_sched_adapter extends RecyclerView.Adapter<reg_sched_adapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNum, tvFrom, tvTo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.regSched_Num);
            tvFrom = itemView.findViewById(R.id.regSched_tvFrom);
            tvTo = itemView.findViewById(R.id.regSched_tvTo);
        }
    }
}
