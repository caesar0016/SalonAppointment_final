package com.example.salonappointment.fragments_model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.Model.register_service_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.account_adapter;
import com.example.salonappointment.adapter.service_adapter;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    private RecyclerView rvAccount, rvService;
    private account_adapter accountAdapter;
    private service_adapter serviceAdapter;
    private List<register_acc_model> accountList;
    private List<register_service_model> serviceList;
    private DatabaseReference dbRef;
    public fragment_home() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvAccount = view.findViewById(R.id.fragmentHome_recyclerAccount);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvAccount.setLayoutManager(layoutManager1);
        accountList = new ArrayList<>();
        //accountlist.add is a sample data only
        accountList.add(new register_acc_model("Son Goku", "Goku@gmail.com", "admin"));
        accountList.add(new register_acc_model("Vegeta", "Vegeta@gmail.com", "user"));
        accountList.add(new register_acc_model("Piccolo", "Piccolo@gmail.com", "user"));
        accountList.add(new register_acc_model("Bulma", "Bulma@gmail.com", "admin"));
        accountList.add(new register_acc_model("Krillin", "Krillin@gmail.com", "user"));

        accountAdapter = new account_adapter((ArrayList<register_acc_model>) accountList);
        rvAccount.setAdapter(accountAdapter);

        //Recycler View for Service Initialization

        rvService = view.findViewById(R.id.fragmentHome_rvService);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvService.setLayoutManager(layoutManager2);
        serviceList = new ArrayList<>();
        serviceList.add(new register_service_model("Haircut", "This is a standard haircut service", 999));
        serviceList.add(new register_service_model("Shave", "This is a basic shave service", 599));
        serviceList.add(new register_service_model("Facial", "This is a relaxing facial service", 1499));
        serviceList.add(new register_service_model("Manicure", "This is a manicure service", 799));
        serviceList.add(new register_service_model("Pedicure", "This is a pedicure service", 899));
        serviceAdapter = new service_adapter((ArrayList<register_service_model>) serviceList);
        rvService.setAdapter(serviceAdapter);

     //   dbRef = FirebaseDatabase.getInstance().getReference("Account");
        return view;
    }

    //todo need to implement the snapshot of the two recycler viewer
}