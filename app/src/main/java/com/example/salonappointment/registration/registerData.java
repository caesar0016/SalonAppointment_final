package com.example.salonappointment.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.Model.register_acc_model;
import com.example.salonappointment.Model.register_service_model;
import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerData extends AppCompatActivity {
    private Button btnService, btnAccount, btnSched;
    private ProgressBar pbMain;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pbMain = (ProgressBar) findViewById(R.id.registerProgressBar);
        btnService = (Button) findViewById(R.id.register_btnDataService);
        btnAccount = (Button) findViewById(R.id.register_btnDataAccount);
        btnSched = (Button) findViewById(R.id.register_btnDataSched);
        btnBack = (ImageView) findViewById(R.id.dpAppoint_bck);

        pbMain.setVisibility(View.INVISIBLE);

        btnSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbMain.setVisibility(View.VISIBLE);
                DatabaseReference dbRefSched = FirebaseDatabase.getInstance().getReference("Staff_Schedule");

                staffSched_model s11 = new staffSched_model("accOne", "8", "am", "9", "am", false);
                staffSched_model s12 = new staffSched_model("accOne", "9", "am", "10", "am", false);
                staffSched_model s13 = new staffSched_model("accOne", "11", "am", "12", "pm", false);
                staffSched_model s14 = new staffSched_model("accOne", "12", "pm", "1", "pm", false);
                staffSched_model s15 = new staffSched_model("accOne", "2", "pm", "3", "pm", false);

                dbRefSched.push().setValue(s11);
                dbRefSched.push().setValue(s12);
                dbRefSched.push().setValue(s13);
                dbRefSched.push().setValue(s14);
                dbRefSched.push().setValue(s15);


                staffSched_model s21 = new staffSched_model("accTwo", "8", "am", "9", "am", false);
                staffSched_model s22 = new staffSched_model("accTwo", "9", "am", "10", "am", false);
                staffSched_model s23 = new staffSched_model("accTwo", "11", "am", "12", "pm", false);
                staffSched_model s24 = new staffSched_model("accTwo", "12", "pm", "1", "pm", false);
                staffSched_model s25 = new staffSched_model("accTwo", "2", "pm", "3", "pm", false);

                dbRefSched.push().setValue(s21);
                dbRefSched.push().setValue(s22);
                dbRefSched.push().setValue(s23);
                dbRefSched.push().setValue(s24);
                dbRefSched.push().setValue(s25);


                staffSched_model s31 = new staffSched_model("accThree", "8", "am", "9", "am", false);
                staffSched_model s32 = new staffSched_model("accThree", "9", "am", "10", "am", false);
                staffSched_model s33 = new staffSched_model("accThree", "11", "am", "12", "pm", false);
                staffSched_model s34 = new staffSched_model("accThree", "12", "pm", "1", "pm", false);
                staffSched_model s35 = new staffSched_model("accThree", "2", "pm", "3", "pm", false);

                dbRefSched.push().setValue(s31);
                dbRefSched.push().setValue(s32);
                dbRefSched.push().setValue(s33);
                dbRefSched.push().setValue(s34);
                dbRefSched.push().setValue(s35);


                staffSched_model s41 = new staffSched_model("accFour", "8", "am", "9", "am", false);
                staffSched_model s42 = new staffSched_model("accFour", "9", "am", "10", "am", false);
                staffSched_model s43 = new staffSched_model("accFour", "11", "am", "12", "pm", false);
                staffSched_model s44 = new staffSched_model("accFour", "12", "pm", "1", "pm", false);
                staffSched_model s45 = new staffSched_model("accFour", "2", "pm", "3", "pm", false);

                dbRefSched.push().setValue(s41);
                dbRefSched.push().setValue(s42);
                dbRefSched.push().setValue(s43);
                dbRefSched.push().setValue(s44);
                dbRefSched.push().setValue(s45);


                staffSched_model s51 = new staffSched_model("accFive", "8", "am", "9", "am", false);
                staffSched_model s52 = new staffSched_model("accFive", "9", "am", "10", "am", false);
                staffSched_model s53 = new staffSched_model("accFive", "11", "am", "12", "pm", false);
                staffSched_model s54 = new staffSched_model("accFive", "12", "pm", "1", "pm", false);
                staffSched_model s55 = new staffSched_model("accFive", "2", "pm", "3", "pm", false);

                dbRefSched.push().setValue(s51);
                dbRefSched.push().setValue(s52);
                dbRefSched.push().setValue(s53);
                dbRefSched.push().setValue(s54);
                dbRefSched.push().setValue(s55);


                staffSched_model s61 = new staffSched_model("accSix", "8", "am", "9", "am", false);
                staffSched_model s62 = new staffSched_model("accSix", "9", "am", "10", "am", false);
                staffSched_model s63 = new staffSched_model("accSix", "11", "am", "12", "pm", false);
                staffSched_model s64 = new staffSched_model("accSix", "12", "pm", "1", "pm", false);
                staffSched_model s65 = new staffSched_model("accSix", "2", "pm", "3", "pm", false);

                dbRefSched.push().setValue(s61);
                dbRefSched.push().setValue(s62);
                dbRefSched.push().setValue(s63);
                dbRefSched.push().setValue(s64);
                dbRefSched.push().setValue(s65);

                pbMain.setVisibility(View.GONE);
                Toast.makeText(registerData.this, "Done inserting Sched", Toast.LENGTH_SHORT).show();
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbMain.setVisibility(View.VISIBLE);
                DatabaseReference dbRefAccount = FirebaseDatabase.getInstance().getReference("User Accounts");

                register_acc_model r1 = new register_acc_model("John Walker", "walkerJohn@gmail.com", "Admin", "accOne");
                register_acc_model r2 = new register_acc_model("Alice Johnson", "alice.johnson@example.com", "User", "accTwo");
                register_acc_model r3 = new register_acc_model("Michael Smith", "michael.smith@test.com", "Stylist", "accThree");
                register_acc_model r4 = new register_acc_model("Emily Brown", "emily.brown@gmail.com", "Manager", "accFour");
                register_acc_model r5 = new register_acc_model("David Lee", "david.lee@example.com", "Cashier", "accFive");
                register_acc_model r6 = new register_acc_model("Smith Rutherford", "rutherFord@gmail.com", "Stylist", "accSix");

                dbRefAccount.push().setValue(r1);
                dbRefAccount.push().setValue(r2);
                dbRefAccount.push().setValue(r3);
                dbRefAccount.push().setValue(r4);
                dbRefAccount.push().setValue(r5);
                dbRefAccount.push().setValue(r6);
                pbMain.setVisibility(View.GONE);

                Toast.makeText(registerData.this, "Done inserting multiple accounts", Toast.LENGTH_SHORT).show();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbMain.setVisibility(View.VISIBLE);
                DatabaseReference dbRefService = FirebaseDatabase.getInstance().getReference("Services");

                register_service_model r1 = new register_service_model("Haircuts", "This is a good Haircut", 250, "none");
                register_service_model r2 = new register_service_model("Manicures", "This is a good Manicures", 252, "none");
                register_service_model r3 = new register_service_model("Pedicures", "This is a good Pedicures", 253, "none");
                register_service_model r4 = new register_service_model("Threading", "This is a good Threading", 254, "none");
                register_service_model r5 = new register_service_model("Waxing", "This is a good Waxing", 255, "none");

                dbRefService.push().setValue(r1);
                dbRefService.push().setValue(r2);
                dbRefService.push().setValue(r3);
                dbRefService.push().setValue(r4);
                dbRefService.push().setValue(r5);

                Toast.makeText(registerData.this, "Done inserting Services", Toast.LENGTH_SHORT).show();
                pbMain.setVisibility(View.GONE);
            }
        });


    }
}