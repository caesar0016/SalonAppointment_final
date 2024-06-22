package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.appointment_model;

import java.util.ArrayList;

public class adapterCustomerQueue extends RecyclerView.Adapter<adapterCustomerQueue.Viewholder> {

    private ArrayList<appointment_model> customerList = new ArrayList<>();
    private Context context;

    public adapterCustomerQueue(ArrayList<appointment_model> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
