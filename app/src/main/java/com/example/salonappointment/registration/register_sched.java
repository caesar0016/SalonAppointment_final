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

import com.example.salonappointment.R;

import java.util.ArrayList;

public class register_sched extends AppCompatActivity {

    private Spinner spin_ampm01, spin_amp02;

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
        //-------------- First Spinner Initialization --------------
        spin_ampm01 = findViewById(R.id.ampmSpinner01);
        firstSpin();

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
        secondSpin();
        spin_amp02.setAdapter(adapter);

    }

    private void firstSpin() {
        spin_ampm01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(register_sched.this, "Selected Item: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void secondSpin() {
        spin_amp02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(register_sched.this, "Selected Item: " + item, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}