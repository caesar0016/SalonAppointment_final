package com.example.salonappointment.fragments_model;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.salonappointment.Model.notif_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.notif_adapter;

import java.util.ArrayList;

public class fragment_notif extends Fragment {
    private notif_adapter adapterNotif;
    private ArrayList<notif_model> notifList;
    private RecyclerView rvNotif;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notif, container, false);

        //Intitialization
        rvNotif = view.findViewById(R.id.rvNotif);
        notifList = new ArrayList<>();
        LinearLayoutManager lmNotif = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvNotif.setLayoutManager(lmNotif);
        notifList.add(new notif_model("July 5, 2024", "This a sample appointment notif"));
        notifList.add(new notif_model("July 3, 2024", "This a sample appointment notif"));
        notifList.add(new notif_model("July 3, 2024", "This a sample appointment notif"));
        notifList.add(new notif_model("July 2, 2024", "This a sample appointment notif"));
        notif_adapter adapter = new notif_adapter(notifList);
        rvNotif.setAdapter(adapter);

        return view;
    }
}