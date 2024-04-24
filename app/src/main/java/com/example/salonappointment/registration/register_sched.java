package com.example.salonappointment.registration;

import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.reg_sched_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class register_sched extends AppCompatActivity {

    private Spinner spin_ampm01, spin_amp02;
    private EditText edStartTime, edEndTime;
    private Button btnAdd;
    String spinAmOrPm = "";
    String spinAmOrPm2 = "";
    String uid = "1";
    private RecyclerView rvSched;
    private DatabaseReference dbRefSched;
    private ArrayList<staffSched_model> listSched;
    private reg_sched_adapter adapterSched;

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

        //Recyclerview for displaying Schedule of the currently login user
        LinearLayoutManager layoutManagerSched = new LinearLayoutManager(register_sched.this, LinearLayoutManager.VERTICAL, false);
        rvSched.setLayoutManager(layoutManagerSched);
        listSched = new ArrayList<>();
        adapterSched = new reg_sched_adapter((ArrayList<staffSched_model>) listSched);
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
                DatabaseReference userRef = dbRefSched.child(uid);
                staffSched_model staffModel = new staffSched_model(uid, startTime, spinAmOrPm, endTime, spinAmOrPm2);
                userRef.push().setValue(staffModel);
                clearFields();
                Toast.makeText(register_sched.this, "Success Transaction", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayStartAmOrPm01() {
        spin_ampm01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spinAmOrPm = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(register_sched.this, "Selected Item: " + spinAmOrPm, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(register_sched.this, "Selected Item: " + spinAmOrPm2, Toast.LENGTH_SHORT).show();
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

    private void displaySched() {
        DatabaseReference userRef = dbRefSched.child("1");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSched.clear();
                for (DataSnapshot scheduleSnapshot : dataSnapshot.getChildren()) {
                    // Iterate through each child under the UID "1"
                    String startTime = scheduleSnapshot.child("startTime").getValue(String.class);
                    String startAmOrPm = scheduleSnapshot.child("startAmOrPm").getValue(String.class);
                    String endTime = scheduleSnapshot.child("endTime").getValue(String.class);
                    String endAmOrPm = scheduleSnapshot.child("endAmOrPm").getValue(String.class);

                    // Create a staffSched_model object for each schedule
                    staffSched_model schedule = new staffSched_model("1", startTime, startAmOrPm, endTime, endAmOrPm);

                    // Add the schedule to the list
                    listSched.add(schedule);
                }

                // Sort the list of schedules by start time
                Collections.sort(listSched, new Comparator<staffSched_model>() {
                    @Override
                    public int compare(staffSched_model schedule1, staffSched_model schedule2) {
                        // Convert start time strings to integers for comparison
                        int time1 = convertTimeTo24Hours(schedule1.getStartTime(), schedule1.getStartAmOrPm());
                        int time2 = convertTimeTo24Hours(schedule2.getStartTime(), schedule2.getStartAmOrPm());

                        // Compare start times
                        return Integer.compare(time1, time2);
                    }
                });
                // Notify the adapter that the data has changed
                adapterSched.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle potential errors
            }
        });
    }

    // Helper method to convert time to 24-hour format for comparison
    private int convertTimeTo24Hours(String time, String amOrPm) {
        int hour = Integer.parseInt(time.split(":")[0]);
        if (amOrPm.equalsIgnoreCase("pm") && hour != 12) {
            hour += 12;
        } else if (amOrPm.equalsIgnoreCase("am") && hour == 12) {
            hour = 0;
        }
        return hour;
    }
}