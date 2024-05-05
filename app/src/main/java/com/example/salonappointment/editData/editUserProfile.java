package com.example.salonappointment.editData;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.salonappointment.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class editUserProfile extends AppCompatActivity {
    private Spinner spinnerUserTypes;
    private TextInputEditText inputName, inputEmail, inputDesc;
    private CircleImageView profileUrl;
    private Button btnSave;
    private static String selectedUserType;
    private ImageView btnBack;

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
        btnSave = (Button) findViewById(R.id.editUser_btnSave);
        btnBack = (ImageView) findViewById(R.id.userProfile_btnBack);

        String email = getIntent().getStringExtra("email");
        inputEmail.setText(getIntent().getStringExtra("email"));

        Glide.with(this).load(getIntent().getStringExtra("imgUrl")).into(profileUrl);


        spinnerUserTypes = findViewById(R.id.editUser_spinType);
        userType_spin();
        ArrayList<String> array_UserType = new ArrayList<>();
        array_UserType.add("Normal User");
        array_UserType.add("Hair Stylist");
        array_UserType.add("Nail Technicians");
        array_UserType.add("Makeup Artist");
        array_UserType.add("Massage Therapist");
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser(email, selectedUserType);
                Toast.makeText(editUserProfile.this, "Finish", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser(String email, String userType) {
        DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference("User Accounts");

        Query query = accountRef.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    // Update the userType field for the specific user found in the query
                    String userID = item.getKey();
                    accountRef.child(userID).child("userType").setValue(userType);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handles Error
            }
        });
    }


    private void userType_spin() {
        spinnerUserTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUserType = parent.getItemAtPosition(position).toString();
                Toast.makeText(editUserProfile.this, "Selected: " + selectedUserType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}