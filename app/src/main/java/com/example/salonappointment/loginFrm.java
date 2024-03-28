package com.example.salonappointment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.registration.categories_registration_frm;

public class loginFrm extends AppCompatActivity {
    private Button btnLogin_loginFrm;
    private TextView signUp;
    private ImageView img_Google, img_ig, img_fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_frm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialization find by View ID
        btnLogin_loginFrm = findViewById(R.id.btn_loginFrm_Login);
        signUp = findViewById(R.id.signupFrm_loginDirect);
        img_Google = findViewById(R.id.img_loginFrm_icGoogle);
        img_ig = findViewById(R.id.img_LoginFrm_icInstagram);
        img_fb = findViewById(R.id.img_LoginFrm_icFacebook);


        img_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginFrm.this, categories_registration_frm.class);
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginFrm.this, signupFrm.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin_loginFrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginFrm.this, appointment_designFrm.class);
                startActivity(intent);
                finish();
            }
        });
    }
}