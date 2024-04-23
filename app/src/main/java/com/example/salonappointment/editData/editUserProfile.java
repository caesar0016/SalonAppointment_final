package com.example.salonappointment.editData;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salonappointment.fragments_model.fragment_home;
import com.example.salonappointment.registration.stylist_registration_frm;

public class editUserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Your onCreate code here
    }

    @Override
    public void onBackPressed() {
        // Check if you need custom back button behavior
        // For example, if you want to navigate to a specific activity or finish the current activity
        // You can customize this according to your app's navigation requirements

        // Example: Navigate to MainActivity
        super.onBackPressed();
        Intent intent = new Intent(this, stylist_registration_frm.class);
        startActivity(intent);

        // Finish the current activity if necessary
        finish();
    }
}
