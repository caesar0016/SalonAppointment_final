package com.example.salonappointment.registration;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
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
    private Button btnRegister;
    private ImageView imgService;
    private Uri uri;
    private ProgressBar progressBar;

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
        spinnerList = new ArrayList<>();
        btnRegister = findViewById(R.id.serviceReg_btnRegister);
        NumberPicker hrPicker = findViewById(R.id.hoursPicker);
        NumberPicker mnPicker = findViewById(R.id.minutesPicker);
        progressBar = findViewById(R.id.pbProgress);
        imgService = findViewById(R.id.add_img_service);

        //picker Initialization
        hrPicker.setMinValue(1);
        hrPicker.setMaxValue(10);

        //minute Picker Initialization
        mnPicker.setMinValue(1);
        mnPicker.setMaxValue(60);

        adapter = new ArrayAdapter<>(service_registration_frm.this, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        imgService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() { //btnRegister Click Event
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(service_registration_frm.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        spinnerCategory.setAdapter(adapter);
        showData();
    }
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });
    private void chooseImage(){
        pickMedia.launch(new PickVisualMediaRequest());
    }


    private void InsertService(String photos, String name, String Description, int duration, double price){
        dbRef = FirebaseDatabase.getInstance().getReference("Service");

    }

    private void showData() {//gets the category name from the firebase
        dbRef = FirebaseDatabase.getInstance().getReference("Service Category");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
                for(DataSnapshot item : snapshot.getChildren()){

                    spinnerList.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //do Nothing
            }
        });
    }
}