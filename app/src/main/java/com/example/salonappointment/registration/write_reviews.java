package com.example.salonappointment.registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.Model.writeReviewsModel;
import com.example.salonappointment.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class write_reviews extends AppCompatActivity {
    private ImageView btnBack, starOne, starTwo, starThree, starFour, starFive;
    private Button btnSubmit;
    private EditText edTitle, edDescription;
    private static int rate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.write_reviews);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //------- This is the initialization ---------------
        btnBack = (ImageView) findViewById(R.id.btnBack);
        edTitle = (EditText) findViewById(R.id.writeReviews_title);
        edDescription = (EditText) findViewById(R.id.writeReviews_desc);

        //----- Getting the date from localDate
        LocalDate currentDate = LocalDate.now();
        // Format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formattedDate = currentDate.format(formatter);


        //----- Initialization of Stars
        starOne = (ImageView) findViewById(R.id.wr_starOne);
        starTwo = (ImageView) findViewById(R.id.wr_starTwo);
        starThree = (ImageView) findViewById(R.id.wr_starThree);
        starFour = (ImageView) findViewById(R.id.wr_starFour);
        starFive = (ImageView) findViewById(R.id.wr_starFive);

        starOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starTwo.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));
                starThree.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));
                starFour.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));
                starFive.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));

                rate = 1;
            }
        });
        starTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starTwo.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starThree.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));
                starFour.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));
                starFive.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));

                rate = 2;
            }
        });
        starThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starTwo.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starThree.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starFour.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));
                starFive.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));

                rate = 3;
            }
        });
        starFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starTwo.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starThree.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starFour.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starFive.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_rate_star));

                rate = 4;
            }
        });
        starFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starTwo.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starThree.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starFour.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starFive.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));

                rate = 5;
            }
        });

        btnSubmit = (Button) findViewById(R.id.writeReviews_btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reviewsRef = FirebaseDatabase.getInstance().getReference("Reviews");
                String title = edTitle.getText().toString().trim();
                String description = edDescription.getText().toString().trim();
                if(TextUtils.isEmpty(title)){
                    edTitle.requestFocus();
                    edTitle.setError("Please Input Title");
                    return;
                }
                else if(TextUtils.isEmpty(description)){
                    edDescription.requestFocus();
                    edDescription.setError("Please Input Description");
                    return;
                }
                else if(rate == 0){
                    Toast.makeText(write_reviews.this, "Please rate the experience", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    writeReviewsModel wr = new writeReviewsModel("1", "2", "Title 1", "This is Description", 5, formattedDate);
                    reviewsRef.push().setValue(wr);
                    Toast.makeText(write_reviews.this, "Success Submit Reviews", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
