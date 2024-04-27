package com.example.salonappointment.displayDetail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.slot_adapter;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class displayAppointment extends AppCompatActivity {
    private Button btnSelectDate;
    private RecyclerView rvSlot;
    private DatabaseReference dbRefSched;
    private slot_adapter adapterSlot;
    private ArrayList<staffSched_model> listSched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.display_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //---------- Initial Instance of the componenents ----------------------
        rvSlot = findViewById(R.id.rvAppointStylist_slot);
        btnSelectDate = findViewById(R.id.dpAppoint_btnSelectDate);
        dbRefSched = FirebaseDatabase.getInstance().getReference().child("Staff_Schedule");

        //----------------Recyclerview Initialization----------------
//        LinearLayoutManager layoutSched = new LinearLayoutManager(displayAppointment.this, LinearLayoutManager.HORIZONTAL, false);
//        rvSlot.setLayoutManager(layoutSched);
        rvSlot.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        listSched = new ArrayList<>();


        adapterSlot = new slot_adapter((ArrayList<staffSched_model>) listSched);
        rvSlot.setAdapter(adapterSlot);
        displaySched();

        MaterialDatePicker.Builder mDateBuilder = MaterialDatePicker.Builder.datePicker();

        mDateBuilder.setTitleText("Select A Date");

        final MaterialDatePicker mDatePicker = mDateBuilder.build();

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });


        mDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {

                        // if the user clicks on the positive
                        // button that is ok button update the
                        // selected date
                        btnSelectDate.setText(mDatePicker.getHeaderText());
                        // in the above statement, getHeaderText
                        // is the selected date preview from the
                        // dialog
                    }
                });


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
                adapterSlot.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle potential errors
            }
        });
    }

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