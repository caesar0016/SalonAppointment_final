package com.example.salonappointment.displayDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.salonappointment.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class displayStylist extends AppCompatActivity {
    private TextView styListName, userRole;
    private CircleImageView imgProfile;

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
        imgProfile = (CircleImageView) findViewById(R.id.displayStylist_imgProfile);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String stylist = extras.getString("stylist");
            String stylistRole = extras.getString("role");
            String urlProfile = extras.getString("imgurl");
            String email = extras.getString("email");
            String staffID = extras.getString("staffID");

            Glide.with(this).load(urlProfile)
                    .error(R.drawable.profile_pic)
                    .into(imgProfile);

            styListName.setText(stylist);
            userRole.setText(stylistRole);
        } else {
            Toast.makeText(this, "Data Retrieval from the account adapter failed", Toast.LENGTH_SHORT).show();
        }

        btnAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(displayStylist.this, displayAppointment.class);
                String staff = extras.getString("staffID");
                intent.putExtra("staffID",staff);
                startActivity(intent);
            }
        });
    }
}