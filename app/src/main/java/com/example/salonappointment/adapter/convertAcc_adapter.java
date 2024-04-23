package com.example.salonappointment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;
import com.example.salonappointment.interfaces.rvInterface_convertAcc;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class convertAcc_adapter extends FirebaseRecyclerAdapter<register_acc_model, convertAcc_adapter.ViewHolder> {
    static Context context;
    private final rvInterface_convertAcc interfaceConvertAcc;

    public convertAcc_adapter(@NonNull FirebaseRecyclerOptions<register_acc_model> options, rvInterface_convertAcc interfaceConvertAcc) {
        super(options);

        this.interfaceConvertAcc = interfaceConvertAcc;
    }

    //  ArrayList<register_acc_model> cvAcc_list = new ArrayList<>();
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull register_acc_model registerAccModel) {
        viewHolder.name.setText(registerAccModel.getName());
        viewHolder.email.setText(registerAccModel.getEmail());
        viewHolder.userType.setText(registerAccModel.getUserType());

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

        public ViewHolder(@NonNull View itemView, rvInterface_convertAcc interfaceConvertAcc) {
            super(itemView);

            name = itemView.findViewById(R.id.convertAcc_name1);
            email = itemView.findViewById(R.id.convertAcc_Email1);
            userType = itemView.findViewById(R.id.convertAcc_UserType1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (interfaceConvertAcc != null) {
                        int pos = getBindingAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            interfaceConvertAcc.onItemClick(pos);

                        }
                    }
                }
            });
        }
    }
}
