package com.example.salonappointment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class convertAcc_adapter extends FirebaseRecyclerAdapter<register_acc_model, convertAcc_adapter.ViewHolder> {
    public convertAcc_adapter(@NonNull FirebaseRecyclerOptions<register_acc_model> options) {
        super(options);
    }

    //  ArrayList<register_acc_model> cvAcc_list = new ArrayList<>();
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull register_acc_model registerAccModel) {
        Log.d("Adapter", "onBindViewHolder called for position: " + i);
        Log.d("Adapter", "Name: " + registerAccModel.getName());
        Log.d("Adapter", "Email: " + registerAccModel.getEmail());
        Log.d("Adapter", "UserType: " + registerAccModel.getUserType());

        viewHolder.name.setText(registerAccModel.getName());
        viewHolder.email.setText(registerAccModel.getEmail());
        viewHolder.userType.setText(registerAccModel.getUserType());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.convert_acc_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView accProfile;
        TextView name, email, userType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.convertAcc_name1);
            email = itemView.findViewById(R.id.convertAcc_Email1);
            userType = itemView.findViewById(R.id.convertAcc_UserType1);

        }
    }
}
