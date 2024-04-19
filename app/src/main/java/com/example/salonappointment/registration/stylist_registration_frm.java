package com.example.salonappointment.registration;

import android.os.Bundle;

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

import java.util.ArrayList;

public class stylist_registration_frm extends AppCompatActivity {

    private RecyclerView rvStylist;
    private convertAcc_adapter accAdapter;
    private ArrayList<register_acc_model> accList;

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
        rvStylist = findViewById(R.id.regStylist_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvStylist.setLayoutManager(layoutManager);
        accList = new ArrayList<>();

        accList.add(new register_acc_model("Name", "name@gmail.com", "Normal User", "123dd"));
        accList.add(new register_acc_model("Name1", "name@gmail.com", "Normal User", "123dd"));
        accList.add(new register_acc_model("Name2", "name@gmail.com", "Normal User", "123dd"));
        accList.add(new register_acc_model("Name3", "name@gmail.com", "Normal User", "123dd"));
        accList.add(new register_acc_model("Name4", "name@gmail.com", "Normal User", "123dd"));
        accAdapter = new convertAcc_adapter(accList);
        rvStylist.setAdapter(accAdapter);
    }
}