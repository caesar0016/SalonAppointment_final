package com.example.salonappointment.displayDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.R;

public class displayStylist extends AppCompatActivity {
    private TextView styListName, userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.display_stylist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //--------- Initialization --------------
        Button btnAppoint = findViewById(R.id.displayStylist_btnBook);
        styListName = (TextView) findViewById(R.id.displayStylist_name);
        userRole = (TextView) findViewById(R.id.displayStylist_profession);

        String stylist = getIntent().getStringExtra("stylist");
        String stylistRole = getIntent().getStringExtra("role");

        styListName.setText(stylist);
        userRole.setText(stylistRole);
        btnAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(displayStylist.this, displayAppointment.class);
                startActivity(intent);
            }
        });
    }
}