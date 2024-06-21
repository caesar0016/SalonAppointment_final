package com.example.salonappointment.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.reg_sched_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class register_sched extends AppCompatActivity {

    private Spinner spin_ampm01, spin_amp02;
    private EditText edStartTime, edEndTime;
    private Button btnAdd, btnClear;
    String spinAmOrPm = "";
    String spinAmOrPm2 = "";
    String Staff_uid = null;
    private RecyclerView rvSched;
    private DatabaseReference dbRefSched;
    private ArrayList<staffSched_model> listSched;
    private reg_sched_adapter adapterSched;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_sched);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Initialization
        btnAdd = findViewById(R.id.regSched_btnAdd);
        edStartTime = findViewById(R.id.regSchedstartTime);
        edEndTime = findViewById(R.id.regSched_endTime);
        dbRefSched = FirebaseDatabase.getInstance().getReference().child("Staff_Schedule");
        rvSched = findViewById(R.id.regSched_rvSched);
        btnBack = (ImageView) findViewById(R.id.regSched_btnBack);
        btnClear = (Button) findViewById(R.id.regSched_btnClear);

        //Getting user uid
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Staff_uid = user.getUid();
        } else {
            //User not signin
        }


        //Recyclerview for displaying Schedule of the currently login user
        LinearLayoutManager layoutManagerSched = new LinearLayoutManager(register_sched.this, LinearLayoutManager.VERTICAL, false);
        rvSched.setLayoutManager(layoutManagerSched);
        listSched = new ArrayList<>();

        adapterSched = new reg_sched_adapter(this, (ArrayList<staffSched_model>) listSched);
        rvSched.setAdapter(adapterSched);
        displaySched();


        //-------------- First Spinner Initialization --------------
        spin_ampm01 = findViewById(R.id.ampmSpinner01);
        displayStartAmOrPm01();

        ArrayList<String> array_ampm = new ArrayList<>();
        array_ampm.add("am");
        array_ampm.add("pm");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, array_ampm
        );
        adapter.setDropDownViewResource(
                android.R.layout.select_dialog_singlechoice
        );
        spin_ampm01.setAdapter(adapter);

        //--------------Second Spinner Initialization--------------
        spin_amp02 = findViewById(R.id.ampmSpinner02);
        displayStartAmOrPm02();
        spin_amp02.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Get the user's UID
                    String userUID = user.getUid();

                    // Get a reference to the location in the database where you want to clear data
                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Staff_Schedule").child(userUID);

                    // Remove the data at the specified location
                    dbRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Data cleared successfully
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Data cleared successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle the error
                                Toast.makeText(getApplicationContext(), "Failed to clear data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // User is not authenticated
                    Toast.makeText(getApplicationContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startTime = edStartTime.getText().toString().trim();
                String endTime = edEndTime.getText().toString().trim();

                if (TextUtils.isEmpty(startTime)) {
                    edStartTime.setError("Please input starting time");
                    edStartTime.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(endTime)) {
                    edEndTime.setError("Please input end time");
                    edEndTime.requestFocus();
                    return;
                }
                DatabaseReference userRef = dbRefSched.child(Staff_uid);
                String time = startTime + " " + spinAmOrPm + " - " + endTime + " " + spinAmOrPm2;

                staffSched_model staffModel = new staffSched_model(Staff_uid, time, false);
                userRef.push().setValue(staffModel);
                clearFields();
                Toast.makeText(register_sched.this, "Success Transaction", Toast.LENGTH_SHORT).show();
                displaySched();
            }
        });
    }

    private void displayStartAmOrPm01() {
        spin_ampm01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spinAmOrPm = adapterView.getItemAtPosition(position).toString();
            //    Toast.makeText(register_sched.this, "Selected Item: " + spinAmOrPm, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void displayStartAmOrPm02() {
        spin_amp02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinAmOrPm2 = parent.getItemAtPosition(position).toString();
             //   Toast.makeText(register_sched.this, "Selected Item: " + spinAmOrPm2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing I guess
            }
        });
    }

    private void clearFields() {//clears the textFields of the android studio
        edStartTime.setText("");
        edEndTime.setText("");
    }

    private void displaySched(){
        DatabaseReference dbRefSched = FirebaseDatabase.getInstance().getReference().child("Staff_Schedule");
        DatabaseReference dbRefStaffUid = dbRefSched.child(Staff_uid);

        dbRefStaffUid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listSched.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    String key = item.getKey();

                    // Retrieve time value under each key
                    String time = item.child("time").getValue(String.class);

                    // Assuming you have a model class staffSched_model
                    staffSched_model model = new staffSched_model(key, time, false);
                    listSched.add(model);
                }
                adapterSched.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Handling errors
            }
        });

    }

}