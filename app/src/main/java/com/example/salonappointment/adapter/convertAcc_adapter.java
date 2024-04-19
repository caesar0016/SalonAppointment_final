package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class convertAcc_adapter extends RecyclerView.Adapter<convertAcc_adapter.ViewHolder> {

    ArrayList<register_acc_model> cvAcc_list = new ArrayList<>();
    Context context;

    public convertAcc_adapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.convert_acc_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(cvAcc_list.get(position).getName());
        holder.email.setText(cvAcc_list.get(position).getEmail());
        holder.userType.setText(cvAcc_list.get(position).getUserType());
    }

    @Override
    public int getItemCount() {
        return cvAcc_list.size();
    }

    public convertAcc_adapter(ArrayList<register_acc_model> cvAcc_list) {
        this.cvAcc_list = cvAcc_list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

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
