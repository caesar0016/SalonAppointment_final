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
        holder.time.setText(listSched.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return listSched.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView regSched_Num, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.schedTime);
        }
    }
}
