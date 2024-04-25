package com.example.salonappointment.fragments_model;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_home extends Fragment {

    private RecyclerView rvAccount, rvService;
    private account_adapter adapterAcc;
    private service_adapter adapterService;
    private DatabaseReference dbRef;
    private CircleImageView profile;
    private FirebaseAuth mAuth;
    private TextView tvGetUserName;
    private ArrayList<register_service_model> serviceList;
    private ArrayList<register_acc_model> stylistList;

    public fragment_home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //-----other misc initialization-----
        tvGetUserName = view.findViewById(R.id.frHome_Username);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            tvGetUserName.setText(name);

        }
        //  tvGetUserName.setText(name);
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
                switch (menuItem.getItemId()) {
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

        //here lays the initialization adapter of the services and stylist

        //--------------------------------------This is the initialization for services recycler view--------------------------------------
        rvService = view.findViewById(R.id.fragmentHome_rvService);
        LinearLayoutManager layoutService = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvService.setLayoutManager(layoutService);
        serviceList = new ArrayList<>();
        adapterService = new service_adapter((ArrayList<register_service_model>) serviceList);
        rvService.setAdapter(adapterService);
        retrieveService();


        //--------------------------------------Stylist Recycler View Initialization--------------------------------------
        rvAccount = view.findViewById(R.id.fragmentHome_recyclerAccount);
        LinearLayoutManager layoutStylist = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvAccount.setLayoutManager(layoutStylist);
        stylistList = new ArrayList<>();
        adapterAcc = new account_adapter(getContext(),(ArrayList<register_acc_model>) stylistList);
        rvAccount.setAdapter(adapterAcc);
        retrieveStylist();
        return view;
    }
    private void retrieveStylist(){
        DatabaseReference dbRefStylist = FirebaseDatabase.getInstance().getReference("User Accounts");
        dbRefStylist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stylistList.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    register_acc_model stylist = item.getValue(register_acc_model.class);
                    stylistList.add(stylist);
                }
                adapterAcc.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Handles error
            }
        });
    }
    private void retrieveService(){
        DatabaseReference dbRefService = FirebaseDatabase.getInstance().getReference("Services");
        dbRefService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serviceList.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    register_service_model service = item.getValue(register_service_model.class);
                    serviceList.add(service);
                }
                adapterService.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Handles Error
            }
        });
    }
}