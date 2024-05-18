package com.example.salonappointment.displayDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.salonappointment.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class displayStylist extends AppCompatActivity {
    private TextView styListName, userRole, tvServiceOffer;
    private CircleImageView imgProfile;
    private ImageView btnBack;
    private CardView cvRatings;

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
        btnBack = (ImageView) findViewById(R.id.displayStylist_btnBack);
        tvServiceOffer = (TextView) findViewById(R.id.display_serviceOffer);
        cvRatings = (CardView) findViewById(R.id.dpStylist_cvRatings);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String stylist = extras.getString("stylist");
            String stylistRole = extras.getString("role");
            String urlProfile = extras.getString("imgurl");
            String email = extras.getString("email");
            String staffID = extras.getString("staffID");

            if (urlProfile != null && !urlProfile.isEmpty()) {
                // If imgUrl is not null and not empty, load the image using Glide
                Glide.with(this)
                        .load(urlProfile)
                        .error(R.drawable.ic_profile_one) // Error image in case of loading failure
                        .into(imgProfile);
            } else {
                // If imgUrl is null or empty, set a placeholder image using Glide
                Glide.with(this)
                        .load(R.drawable.ic_profile_one) // Placeholder image
                        .into(imgProfile);
            }

            styListName.setText(stylist);
            userRole.setText(stylistRole);
            displayServiceOffered(email);
        } else {
            Toast.makeText(this, "Data Retrieval from the account adapter failed", Toast.LENGTH_SHORT).show();
        }
        cvRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(displayStylist.this, displayReviews.class);
                startActivity(intent);
            }
        });
        btnAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(displayStylist.this, displayAppointment.class);
                String staff = extras.getString("stylist");
                String staffUID = extras.getString("staffID");
                String offerService = tvServiceOffer.getText().toString();
                intent.putExtra("offeredService", offerService);
                intent.putExtra("staffID", staffUID);
                intent.putExtra("staffName", staff);
                startActivity(intent);
            }
        });
    }

    private void displayServiceOffered(String userEmail) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("User Accounts");
        Query query = dbRef.orderByChild("email").equalTo(userEmail);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    String offer = item.child("serviceOffer").getValue(String.class);
                    tvServiceOffer.setText(offer);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Handling Errors
            }
        });
    }
}