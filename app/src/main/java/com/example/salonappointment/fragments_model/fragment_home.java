package com.example.salonappointment.fragments_model;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.salonappointment.Model.category_registration_model;
import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.Model.register_service_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.account_adapter;
import com.example.salonappointment.adapter.service_adapter;
import com.example.salonappointment.loginFrm;
import com.example.salonappointment.registration.categories_registration_frm;
import com.example.salonappointment.registration.register_sched;
import com.example.salonappointment.registration.service_registration_frm;
import com.example.salonappointment.registration.stylist_registration_frm;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_home extends Fragment {

    private RecyclerView rvAccount, rvService;
    private account_adapter accountAdapter;
    private service_adapter serviceAdapter;
    private List<register_acc_model> accountList;
    private List<register_service_model> serviceList;
    private DatabaseReference dbRef;
    private CircleImageView profile;
    private FirebaseAuth mAuth;

    public fragment_home() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //-----other misc initialization-----
        NavigationView navigationView = view.findViewById(R.id.NavigationMain);
        profile = view.findViewById(R.id.frHome_profilePic);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (navigationView.getVisibility() == View.VISIBLE) {
                    navigationView.setVisibility(View.GONE);
                } else {
                    navigationView.setVisibility(View.VISIBLE);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.editProfile:
                        break;
                    case R.id.about:
                        break;
                    case R.id.share:
                        break;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent intentLogout = new Intent(requireContext(), loginFrm.class);
                        startActivity(intentLogout);
                        getActivity().finish();//this close the activity
                        break;
                    case R.id.menu_regCategory:
                        Intent intent0 = new Intent(requireContext(), categories_registration_frm.class);
                        startActivity(intent0);
                        return true;
                    case R.id.menu_regStylist:
                        Intent intent1 = new Intent(requireContext(), stylist_registration_frm.class);
                        startActivity(intent1);
                        return true;
                    case R.id.menu_regService:
                        Intent intent2 = new Intent(requireContext(), service_registration_frm.class);
                        startActivity(intent2);
                        return true;
                    case R.id.menu_regSched:
                        Intent intent3 = new Intent(requireContext(), register_sched.class);
                        startActivity(intent3);
                        return true;
                }
                return true;
            }
        });

        rvAccount = view.findViewById(R.id.fragmentHome_recyclerAccount);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvAccount.setLayoutManager(layoutManager1);
        accountList = new ArrayList<>();
        //accountlist.add is a sample data only
        accountList.add(new register_acc_model("Son Goku", "Goku@gmail.com", "Stylist"));
        accountList.add(new register_acc_model("Vegeta", "Vegeta@gmail.com", "Makeup Artist"));
        accountList.add(new register_acc_model("Piccolo", "Piccolo@gmail.com", "Nail Technician"));
        accountList.add(new register_acc_model("Bulma", "Bulma@gmail.com", "Message Therapist"));
        accountList.add(new register_acc_model("Krillin", "Krillin@gmail.com", "Nail Technician"));

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