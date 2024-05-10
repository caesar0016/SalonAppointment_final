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

        pbMain.setVisibility(View.GONE);

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