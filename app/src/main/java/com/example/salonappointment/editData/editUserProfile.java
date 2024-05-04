package com.example.salonappointment.editData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.salonappointment.R;
import com.example.salonappointment.registration.stylist_registration_frm;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class editUserProfile extends AppCompatActivity {
    private Spinner spinnerUserTypes;
    private TextInputEditText inputName, inputEmail, inputDesc;
    private CircleImageView profileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //--------- Initialization -----------------------
        inputName = (TextInputEditText) findViewById(R.id.etv_loginFrm_name);
        inputEmail = (TextInputEditText) findViewById(R.id.edEmail);
        profileUrl = (CircleImageView) findViewById(R.id.editUserProfile_img);

        inputName.setText(getIntent().getStringExtra("name"));
        inputEmail.setText(getIntent().getStringExtra("email"));

        Glide.with(this).load(getIntent().getStringExtra("imgUrl")).into(profileUrl);


        spinnerUserTypes = findViewById(R.id.editUser_spinType);
        userType_spin();
        ArrayList<String> array_UserType = new ArrayList<>();
        array_UserType.add("Normal User");
        array_UserType.add("Hair Stylist");
        array_UserType.add("Nail Technicians");
        array_UserType.add("Makeup Artist");
        array_UserType.add("Message Therapist");
        array_UserType.add("Cashier");
        array_UserType.add("Manager");
        array_UserType.add("Admin");

        ArrayAdapter<String> adapterSpin = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                array_UserType
        );
        adapterSpin.setDropDownViewResource(
                android.R.layout.select_dialog_singlechoice
        );
        spinnerUserTypes.setAdapter(adapterSpin);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, stylist_registration_frm.class);
        startActivity(intent);

        // Finish the current activity if necessary
        finish();
    }

    private void userType_spin() {
        spinnerUserTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Toast.makeText(editUserProfile.this, "Selected: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}