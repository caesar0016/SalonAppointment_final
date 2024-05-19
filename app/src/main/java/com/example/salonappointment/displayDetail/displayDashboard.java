package com.example.salonappointment.displayDetail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class displayDashboard extends AppCompatActivity {
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.display_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBack = (ImageView) findViewById(R.id.btnBack);
        PieChart pieRevenue = (PieChart) findViewById(R.id.dashBoard_pieRevenue);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(100f, "Haircut"));
        entries.add(new PieEntry(90f, "Massage"));
        entries.add(new PieEntry(40f, "Manicure"));
        entries.add(new PieEntry(70f, "Pedicure"));

        PieDataSet pieDataSet = new PieDataSet(entries, "Revenues");
      //  pieDataSet.setColors(Color.BLUE, Color.RED, Color.GREEN, Color.WHITE);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieRevenue.setData(pieData);

        pieRevenue.getDescription().setEnabled(false);
        pieRevenue.animateY(1000);
        pieRevenue.invalidate();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}