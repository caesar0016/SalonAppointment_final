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
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.account_adapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    private RecyclerView rv_home;
    private account_adapter accountAdapter;
    private List<register_acc_model> accountList;
 //   private DatabaseReference dbRef;
    public fragment_home() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_home = view.findViewById(R.id.fragmentHome_recycler);
        rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        accountList = new ArrayList<>();
        //accountlist.add is a sample data only
        accountList.add(new register_acc_model("Son Goku", "Goku@gmail.com", "admin"));
        accountAdapter = new account_adapter((ArrayList<register_acc_model>) accountList);
        rv_home.setAdapter(accountAdapter);
        return view;
    }
    //todo snapshot for firebase retrival of accountlist
}