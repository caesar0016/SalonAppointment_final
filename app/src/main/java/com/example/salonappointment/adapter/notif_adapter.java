package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.notif_model;
import com.example.salonappointment.R;

import java.util.ArrayList;

public class notif_adapter extends RecyclerView.Adapter<notif_adapter.ViewHolder> {
    private ArrayList<notif_model> notifList = new ArrayList<>();
    Context context;

    public notif_adapter(ArrayList<notif_model> notifList) {
        this.notifList = notifList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notif_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(notifList.get(position).getDate());
        holder.notifMessage.setText(notifList.get(position).getNotif_message());
    }

    @Override
    public int getItemCount() {
        return notifList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date, notifMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.vrNotifDate);
            notifMessage = itemView.findViewById(R.id.rvNotif_Message);
        }
    }
}
