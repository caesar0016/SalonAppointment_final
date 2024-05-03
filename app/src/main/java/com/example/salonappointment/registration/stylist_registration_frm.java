package com.example.salonappointment.registration;

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
import com.example.salonappointment.adapter.convertAcc_adapter;

import java.util.ArrayList;

public class stylist_registration_frm extends AppCompatActivity {

    private RecyclerView rvStylist;
    private convertAcc_adapter adapterAcc;
    private ArrayList<register_acc_model> listAcc;
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

        //Spinner
        spin_userType = findViewById(R.id.spinner_userType);
        userType_spin();
        ArrayList<String> array_UserType = new ArrayList<>();
        array_UserType.add("Normal User");
        array_UserType.add("Hair Stylist");
        array_UserType.add("Nail Technicians");
        array_UserType.add("Makeup Artist");
        array_UserType.add("Message Therapist");
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

        //-------------------Firebase RecyclerView data ---------------------
        rvStylist = findViewById(R.id.regStylist_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(stylist_registration_frm.this);
        rvStylist.setLayoutManager(layoutManager);
        listAcc = new ArrayList<>();
        listAcc.add(new register_acc_model("Name1", "emailOne@gmail.com", "Admin", "123", "https://firebasestorage.googleapis.com/v0/b/salonmain.appspot.com/o/default_images%2Fdefault_image.png?alt=media&token=262f2e0c-5926-44f6-a22f-20ec627f72fb"));
        convertAcc_adapter adapter = new convertAcc_adapter(this, listAcc);
        rvStylist.setAdapter(adapter);

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
}