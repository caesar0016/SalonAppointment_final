package com.example.salonappointment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;
import com.example.salonappointment.editData.editUserProfile;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class convertAcc_adapter extends RecyclerView.Adapter<convertAcc_adapter.ViewHolder> {
    private final Context context;
    private ArrayList<register_acc_model> listAcc = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.convert_acc_view_rows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(listAcc.get(position).getName());
        holder.email.setText(listAcc.get(position).getEmail());
        holder.userType.setText(listAcc.get(position).getUserType());

        //------String Conversion----------
        String name = listAcc.get(position).getName();
        String email = listAcc.get(position).getEmail();
        String userType = listAcc.get(position).getUserType();
        String imgUrl = listAcc.get(position).getProfileURl();

        Glide.with(context).load(imgUrl).into(holder.accProfile);

        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, editUserProfile.class);

                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("userType", userType);
                intent.putExtra("imgUrl", imgUrl);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listAcc.size();
    }

    public convertAcc_adapter(Context context, ArrayList<register_acc_model> listAcc) {
        this.context = context;
        this.listAcc = listAcc;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView accProfile;
        TextView name, email, userType;
        CardView cvContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.convertAcc_name1);
            email = itemView.findViewById(R.id.convertAcc_Email1);
            userType = itemView.findViewById(R.id.convertAcc_UserType1);
            cvContainer = itemView.findViewById(R.id.convertAcc_cv);
            accProfile = itemView.findViewById(R.id.convertAcc_Profile);

        }
    }
}
