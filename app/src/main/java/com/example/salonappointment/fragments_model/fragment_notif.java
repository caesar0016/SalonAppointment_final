package com.example.salonappointment.fragments_model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.notif_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.notif_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragment_notif extends Fragment {
    private notif_adapter adapterNotif;
    private ArrayList<notif_model> notifList;
    private RecyclerView rvNotif;
    private DatabaseReference dbRefNotif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notif, container, false);

        //Intitialization
        dbRefNotif = FirebaseDatabase.getInstance().getReference("appointments");
        rvNotif = view.findViewById(R.id.rvNotif);
        notifList = new ArrayList<>();
        LinearLayoutManager lmNotif = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvNotif.setLayoutManager(lmNotif);
        adapterNotif = new notif_adapter(notifList);
        rvNotif.setAdapter(adapterNotif);
        displayNotif();

        return view;
    }

    private void displayNotif() {
        dbRefNotif.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    String date = item.child("date").getValue(String.class);
                    String timeSlot = item.child("timeSlot").getValue(String.class);

                    String dateMessage = "Appointment Date: " + date;
                    String timeMessage = "Appointment Time: " + timeSlot;
                    String fullMessage = "You have an appointment on " + date + " \nat " + timeSlot;

                    notif_model model = new notif_model(dateMessage, fullMessage);
                    notifList.add(model);

                }
                adapterNotif.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //do nothing

            }
        });
    }
}