package com.example.salonappointment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.fragments_model.fragment_appointment;
import com.example.salonappointment.fragments_model.fragment_home;
import com.example.salonappointment.fragments_model.fragment_notif;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class main_page_frm extends AppCompatActivity {

    private BottomNavigationView btmNavigationView;
    private BottomNavigationView bottom_navigation;
    fragment_home fragmentHome = new fragment_home();
    fragment_appointment fragmentAppointment = new fragment_appointment();
    fragment_notif fragmentNotif = new fragment_notif();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_page_frm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialization
        bottom_navigation = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainPage_container, fragmentHome).commit();

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Disable back button functionality
                // Or implement custom behavior here
            }
        };

        // Add the callback to the OnBackPressedDispatcher
        this.getOnBackPressedDispatcher().addCallback(this, callback);


        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainPage_container, fragmentHome).commit();
                        return true;

                    case R.id.notif:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainPage_container, fragmentNotif).commit();
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }
}