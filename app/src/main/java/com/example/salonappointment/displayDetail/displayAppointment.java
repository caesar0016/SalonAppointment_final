package com.example.salonappointment.displayDetail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.appointment_model;
import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.slot_adapter;
import com.example.salonappointment.main_page_frm;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class displayAppointment extends AppCompatActivity {
    private Button btnSelectDate, btnConfirm;
    private RecyclerView rvSlot;
    private DatabaseReference dbRefSched;
    private slot_adapter adapterSlot;
    private ArrayList<staffSched_model> listSched;
    private ImageView btnBack;
    String chosenDate = "";
    // String staffUID; // Declare staffUID at the class level
    private TextView tvPrice;
    private String offeredService, staffUID, staffName;

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
        DatabaseReference dbRefAppointment = FirebaseDatabase.getInstance().getReference("appointments");
        btnConfirm = findViewById(R.id.dpAppoint_btnConfirm);
        btnBack = (ImageView) findViewById(R.id.dpAppoint_bck);
        TextView tvName = (TextView) findViewById(R.id.dpAppoint_tvStylistName);
        TextView tvService = (TextView) findViewById(R.id.dpAppoint_serviceName);
        tvPrice = (TextView) findViewById(R.id.dpAppoint_servicePrice);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            offeredService = extras.getString("offeredService");
            staffUID = extras.getString("staffID");
            staffName = extras.getString("staffName");
        } else {
            Toast.makeText(this, "Bundle in display Appointment Failed", Toast.LENGTH_SHORT).show();
        }

        displaySched();


        tvName.setText(staffName);
        tvService.setText(offeredService);

        showPriceForService(offeredService);//sets the price

        //----------------Recyclerview Initialization----------------
        rvSlot.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        listSched = new ArrayList<>();
        adapterSlot = new slot_adapter(this, (ArrayList<staffSched_model>) listSched);
        rvSlot.setAdapter(adapterSlot);


        //---------------------Date Picker Initialization---------------------
        MaterialDatePicker.Builder mDateBuilder = MaterialDatePicker.Builder.datePicker();

        // Set the title of the date picker
        mDateBuilder.setTitleText("Select A Date");

        // Get the current date
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.from(Calendar.getInstance().getTimeInMillis() + TimeUnit.DAYS.toMillis(0)));

        // Apply the constraints to the date picker
        mDateBuilder.setCalendarConstraints(constraintsBuilder.build());

        final MaterialDatePicker mDatePicker = mDateBuilder.build();

        //--------FirebaseUser Block ------------------
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userUid = user.getUid();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
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
                        chosenDate = mDatePicker.getHeaderText();
                    }
                });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chosenTime = staffSched_model.FinalTime;
                String archiveFlag = "1";

                if (chosenDate == null || chosenDate.equals("")) {
                    // Chosen date is either null or an empty string
                    Toast.makeText(displayAppointment.this, "Please Select a Date", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // Chosen date is not null and not an empty string
                    appointment_model model = new appointment_model(staffUID, userUid, chosenDate, chosenTime, 250, "Confirm", archiveFlag);
                    dbRefAppointment.push().setValue(model);
                  //  addDate(staffUID, chosenDate);

                    // Once appointment is confirmed and stored in the database, navigate to main_page_frm activity
                    Intent intent = new Intent(displayAppointment.this, main_page_frm.class);
                   // changeTime();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void changeTime() {
        String time = staffSched_model.FinalTime;
        String[] parts = time.split(" - ");

        // startTime Parts
        String[] startTimeParts = parts[0].split(" ");
        String startTimeHour = startTimeParts[0];
        String startTimePeriod = startTimeParts[1];

        // EndTime Parts
        String[] endTimeParts = parts[1].split(" ");
        String endTimeHour = endTimeParts[0];
        String endTimePeriod = endTimeParts[1];

        DatabaseReference dbRefSched = FirebaseDatabase.getInstance().getReference("Staff_Schedule");
        String staffUID = getIntent().getStringExtra("staffID");

        // Query the database to get the schedule
        Query scheduleQuery = dbRefSched.orderByChild("staff_uid").equalTo(staffUID);

        scheduleQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String startAmOrPm = snapshot.child("startAmOrPm").getValue(String.class);
                    String endAmOrPm = snapshot.child("endAmOrPm").getValue(String.class);
                    String startTimeStr = String.valueOf(snapshot.child("startTime").getValue(String.class));
                    String endTimeStr = String.valueOf(snapshot.child("endTime").getValue(String.class));

                    // Check if the schedule matches the extracted time
                    if (startAmOrPm.equals(startTimePeriod) && endAmOrPm.equals(endTimePeriod)
                            && startTimeStr.equals(startTimeHour) && endTimeStr.equals(endTimeHour)) {
                        // If the schedule matches, update the taken field to true
                        snapshot.getRef().child("taken").setValue(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors here
            }
        });

    }

    private void displaySched() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Staff_Schedule").child(staffUID);
        Query query = dbRef.orderByChild("staff_uid").equalTo(staffUID);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    // Iterate through each child under the UID
                    String time = item.child("time").getValue(String.class);
                    String keys = item.getKey();

                    // Create a staffSched_model object for each schedule
                    staffSched_model schedule = new staffSched_model("AccFive", time, false);
                    // Add the schedule to the list
                    listSched.add(schedule);
                }

                adapterSlot.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Handles error
            }
        });

    }
    private void showPriceForService(String serviceName) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Services");
        Query query = dbRef.orderByChild("serviceName").equalTo(serviceName);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean serviceFound = false;
                for (DataSnapshot item : snapshot.getChildren()) {
                    // Retrieve the price for the serviceName
                    Long priceLong = item.child("reservationPrice").getValue(Long.class);
                    if (priceLong != null) {
                        tvPrice.setText(String.valueOf(priceLong));
                        serviceFound = true;
                        break; // No need to continue iterating if service is found
                    }
                }
                // Handle case where serviceName is not found
                if (!serviceFound) {
                    tvPrice.setText("Service not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
                String TAG = "DatabaseError";
                Log.e(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private boolean validateTaken(String time, String staffID, String date) {
        DatabaseReference dbRefAppointment = FirebaseDatabase.getInstance().getReference("appointments");

        Query query = dbRefAppointment.orderByChild("date").equalTo(date);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if there are any appointments for the given date
                for (DataSnapshot appointmentSnapshot : snapshot.getChildren()) {
                    String appointmentTime = appointmentSnapshot.child("time").getValue(String.class);
                    String appointmentStaffID = appointmentSnapshot.child("staffID").getValue(String.class);
                    // If there is an appointment with the same date, time, and staffID, return true
                    if (appointmentTime != null && appointmentStaffID != null &&
                            appointmentTime.equals(time) && appointmentStaffID.equals(staffID)) {
                        // Appointment found for the same time and staff
                        // Return true to indicate that the time slot is taken
                        return;
                    }
                }
                // No conflicting appointments found, return false
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

        // Default return value, you may want to handle this differently based on your needs
        return false;
    }


}