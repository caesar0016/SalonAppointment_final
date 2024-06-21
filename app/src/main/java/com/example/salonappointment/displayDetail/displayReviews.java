package com.example.salonappointment.displayDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.writeReviewsModel;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.reviews_adapter;
import com.example.salonappointment.registration.write_reviews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class displayReviews extends AppCompatActivity {
    private Button btnWriteReviews;
    private ImageView btnBack;
    private RecyclerView rvReviews;
    private reviews_adapter adapterReviews;
    private ArrayList<writeReviewsModel> listReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.display_reviews);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //------- Initializations
        btnWriteReviews = (Button) findViewById(R.id.dsReview_btnWrite);
        btnBack = (ImageView) findViewById(R.id.btnBack);

        //   ------ Initializations for RecyclerView ---------
        rvReviews = (RecyclerView) findViewById(R.id.dsReview_rv);
        LinearLayoutManager layoutReviews = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvReviews.setLayoutManager(layoutReviews);
        listReviews = new ArrayList<>();
        listReviews.add(new writeReviewsModel("Staff 1", "Customer 1", "Title 1", "Description1", 5, "Date Daw"));
        adapterReviews = new reviews_adapter(listReviews, this);
        rvReviews.setAdapter(adapterReviews);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnWriteReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(displayReviews.this, write_reviews.class);
                startActivity(intent);
            }
        });
    }
    void retrieveReviews(String staffID){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Reviews");

        Query query = dbRef.orderByChild("staffUID").equalTo(staffID);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listReviews.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    String key = item.getKey();
                    String customerID = item.child("customerID").getValue(String.class);
                    Long rateLong  = item.child("scoreRating").getValue(Long.class);
                    String date = item.child("customerID").getValue(String.class);
                    String title = item.child("title").getValue(String.class);
                    String description = item.child("description").getValue(String.class);
                    String staffUID = item.child("staffUID").getValue(String.class);

                    int rate = (rateLong != null) ? rateLong.intValue() : 0; // Provide a default value if rateLong is null


                    listReviews.add(new writeReviewsModel(staffUID, customerID, title, description, rate, date));


                }
                adapterReviews.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //handling errors
            }
        });
    }
}