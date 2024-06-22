package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.appointment_model;
import com.example.salonappointment.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.convert_acc_view_rows, parent, false);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText(customerList.get(position).getCustomerID());
      //  holder.email.setText(customerList.get(position).get());
        holder.type.setText(customerList.get(position).getCustomerID());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }


    public static class Viewholder extends RecyclerView.ViewHolder {
        CardView cvConvert;
        TextView name, email, type;
        CircleImageView profilePic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            cvConvert = itemView.findViewById(R.id.convertAcc_cv);
            name = itemView.findViewById(R.id.convertAcc_name);
            email = itemView.findViewById(R.id.convertAcc_name);
            type = itemView.findViewById(R.id.convertAcc_name);
            profilePic = itemView.findViewById(R.id.convertAcc_Profile);
        }
    }
}
