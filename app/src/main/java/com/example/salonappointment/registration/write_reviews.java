package com.example.salonappointment.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.R;

public class write_reviews extends AppCompatActivity {
    private ImageView btnBack, starOne, starTwo, starThree, starFour, starFive;

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
            }
        });
        starFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starTwo.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starThree.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
                starFour.setImageDrawable(v.getContext().getDrawable(R.drawable.ic_stars_yellow));
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
            }
        });






        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void starToYellow(){


    }
}
