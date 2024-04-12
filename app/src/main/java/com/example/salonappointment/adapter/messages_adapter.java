package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.acc_messages_model;
import com.example.salonappointment.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class messages_adapter extends RecyclerView.Adapter<messages_adapter.ViewHolder> {
    Context context;
    private ArrayList<acc_messages_model> messageList = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(messageList.get(position).getName());
        holder.messages.setText(messageList.get(position).getMessage());
        holder.timeStamp.setText(messageList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
    public messages_adapter(ArrayList<acc_messages_model> messageList) {
        this.messageList = messageList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleProfile; //pang abang lol
        private TextView name, messages, timeStamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialization
            name = itemView.findViewById(R.id.frMEssage_senderName);
            messages = itemView.findViewById(R.id.frMEssage_message);
            timeStamp = itemView.findViewById(R.id.frMEssage_timeStamp);
        }
    }
}
