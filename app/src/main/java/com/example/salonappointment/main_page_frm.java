package com.example.salonappointment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.salonappointment.fragments_model.fragment_appointment;
import com.example.salonappointment.fragments_model.fragment_home;
import com.example.salonappointment.fragments_model.fragment_message;
import com.example.salonappointment.fragments_model.fragment_notif;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class main_page_frm extends AppCompatActivity {

    private BottomNavigationView btmNavigationView;
    private BottomNavigationView bottom_navigation;
    fragment_home fragmentHome = new fragment_home();
    fragment_message fragmentMessage = new fragment_message();
    fragment_appointment fragmentAppointment= new fragment_appointment();
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



        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainPage_container, fragmentHome).commit();
                        return true;

                    case R.id.appointment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainPage_container, fragmentAppointment).commit();
                        return true;

                    case R.id.message:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainPage_container, fragmentMessage).commit();
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