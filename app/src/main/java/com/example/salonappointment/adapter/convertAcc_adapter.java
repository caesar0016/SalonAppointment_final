package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;
import com.example.salonappointment.interfaces.rvInterface_convertAcc;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class convertAcc_adapter extends FirebaseRecyclerAdapter<register_acc_model, convertAcc_adapter.ViewHolder> {
    private final rvInterface_convertAcc interfaceConvertAcc;

    public convertAcc_adapter(@NonNull FirebaseRecyclerOptions<register_acc_model> options, rvInterface_convertAcc interfaceConvertAcc) {
        super(options);

        this.interfaceConvertAcc = interfaceConvertAcc;
    }

    //  ArrayList<register_acc_model> cvAcc_list = new ArrayList<>();
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull register_acc_model registerAccModel) {
        Context context = viewHolder.itemView.getContext();

        viewHolder.name.setText(registerAccModel.getName());
        viewHolder.email.setText(registerAccModel.getEmail());
        viewHolder.userType.setText(registerAccModel.getUserType());

        String imageUrl = registerAccModel.getProfilePic();
        Glide.with(context).load(imageUrl).into(viewHolder.accProfile);

        viewHolder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "You click conversion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.convert_acc_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view, interfaceConvertAcc);
        return holder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView accProfile;
        TextView name, email, userType;
        CardView cvContainer;

        public ViewHolder(@NonNull View itemView, rvInterface_convertAcc interfaceConvertAcc) {
            super(itemView);

            name = itemView.findViewById(R.id.convertAcc_name1);
            email = itemView.findViewById(R.id.convertAcc_Email1);
            userType = itemView.findViewById(R.id.convertAcc_UserType1);
            cvContainer = itemView.findViewById(R.id.convertAcc_cv);

        }
    }
}
