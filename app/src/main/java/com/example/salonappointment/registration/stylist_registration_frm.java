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

import java.util.ArrayList;

public class stylist_registration_frm extends AppCompatActivity {

    private RecyclerView rvStylist;
    private account_adapter acc_adpter;
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
        accList.add(new register_acc_model("Name1", "name1@gmail.com", "admin", "123"));

        acc_adpter = new account_adapter(accList);
        rvStylist.setAdapter(acc_adpter);
    }
}