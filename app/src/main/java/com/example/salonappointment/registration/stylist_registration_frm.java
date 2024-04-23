package com.example.salonappointment.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.account_adapter;
import com.example.salonappointment.adapter.convertAcc_adapter;
import com.example.salonappointment.editData.editUserProfile;
import com.example.salonappointment.interfaces.rvInterface_convertAcc;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class stylist_registration_frm extends AppCompatActivity implements rvInterface_convertAcc {

    private RecyclerView rvStylist;
    private convertAcc_adapter accAdapter;
    private ArrayList<register_acc_model> accList;
    private Spinner spin_userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.stylist_registration_frm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Initialization
        rvStylist = findViewById(R.id.regStylist_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvStylist.setLayoutManager(layoutManager);

        //Spinnner
        spin_userType = findViewById(R.id.spinner_userType);
        userType_spin();
        ArrayList<String> array_UserType = new ArrayList<>();
        array_UserType.add("Normal User");
        array_UserType.add("Stylist");
        array_UserType.add("Cashier");
        array_UserType.add("Manager");
        array_UserType.add("Admin");

        ArrayAdapter<String> adapterSpin = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, array_UserType
        );
        adapterSpin.setDropDownViewResource(
                android.R.layout.select_dialog_singlechoice
        );
        spin_userType.setAdapter(adapterSpin);

        //-------------------Firebase RecyclerView data retrival for recybclerview ---------------------

        FirebaseRecyclerOptions<register_acc_model> options =
                new FirebaseRecyclerOptions.Builder<register_acc_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("User Accounts"), register_acc_model.class).build();

        accAdapter = new convertAcc_adapter(options, this);
        rvStylist.setAdapter(accAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        accAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accAdapter.stopListening();
    }

    private void userType_spin() {
        spin_userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(stylist_registration_frm.this, "Selected: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(stylist_registration_frm.this, editUserProfile.class);
        startActivity(intent);
    }
}