package com.example.salonappointment.displayDetail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.reg_sched_adapter;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class displayAppointment extends AppCompatActivity {
    private Button btnSelectDate;
    private RecyclerView rvSlot;
    private DatabaseReference dbRefSched;
    private reg_sched_adapter adapterSched;
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

        //----------------Recyclerview Initialization----------------
//        LinearLayoutManager layoutSched = new LinearLayoutManager(displayAppointment.this, LinearLayoutManager.HORIZONTAL, false);
//        rvSlot.setLayoutManager(layoutSched);
        rvSlot.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        listSched = new ArrayList<>();
        listSched.add(new staffSched_model("1", "8", "am", "9", "pm"));
        listSched.add(new staffSched_model("1", "9", "am", "10", "am"));
        listSched.add(new staffSched_model("1", "10", "am", "11", "am"));
        listSched.add(new staffSched_model("1", "11", "am", "12", "pm"));
        listSched.add(new staffSched_model("1", "1", "pm", "2", "pm"));
        listSched.add(new staffSched_model("1", "3", "pm", "4", "pm"));
        listSched.add(new staffSched_model("1", "8", "am", "9", "pm"));
        listSched.add(new staffSched_model("1", "9", "am", "10", "am"));
        listSched.add(new staffSched_model("1", "10", "am", "11", "am"));
        listSched.add(new staffSched_model("1", "11", "am", "12", "pm"));
        listSched.add(new staffSched_model("1", "1", "pm", "2", "pm"));
        listSched.add(new staffSched_model("1", "3", "pm", "4", "pm"));

        adapterSched = new reg_sched_adapter((ArrayList<staffSched_model>) listSched);
        rvSlot.setAdapter(adapterSched);
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
    private void displaySched(){
        dbRefSched = FirebaseDatabase.getInstance().getReference("Schedule");
        dbRefSched.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}