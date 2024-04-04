package com.example.salonappointment.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class service_registration_frm extends AppCompatActivity {

    private Spinner spinnerCategory;
    private DatabaseReference dbRef;
    private ArrayList<String> spinnerList;
    private ArrayAdapter<String> adapter;
    private EditText ed01_category;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.service_registration_frm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialization
        spinnerCategory = findViewById(R.id.serviceReg_spinner);
        dbRef = FirebaseDatabase.getInstance().getReference("Service Category");
        spinnerList = new ArrayList<>();
        ed01_category = findViewById(R.id.ed_Category01);
        btnRegister = findViewById(R.id.serviceReg_btnRegister);

        adapter = new ArrayAdapter<>(service_registration_frm.this, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = ed01_category.getText().toString();
                String key = dbRef.push().getKey();
                dbRef.child(key).setValue(value);
                ed01_category.setText(" ");
                spinnerList.clear();
                adapter.notifyDataSetChanged();

                Toast.makeText(service_registration_frm.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
        spinnerCategory.setAdapter(adapter);
        showData();
    }

    private void showData() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item : snapshot.getChildren()){

                    spinnerList.add(item.getValue().toString());

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}