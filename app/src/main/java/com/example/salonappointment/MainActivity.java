package com.example.salonappointment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnSignUp_main, btnLogin_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnSignUp_main = findViewById(R.id.btn_Main_GetStarted);
        btnLogin_main = findViewById(R.id.btn_Main_Login);

        btnSignUp_main.setOnClickListener(new View.OnClickListener() {//if click redirect to signupfrm
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signupFrm.class);
                startActivity(intent);
            }
        });

        btnLogin_main.setOnClickListener(new View.OnClickListener() { //btn click then redirect to the loginfrm
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginFrm.class);
                startActivity(intent);
            }
        });
    }
}