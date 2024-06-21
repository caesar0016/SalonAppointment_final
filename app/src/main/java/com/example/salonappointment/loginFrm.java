package com.example.salonappointment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginFrm extends AppCompatActivity {
    private Button btnLogin_loginFrm;
    private TextView signUp, login_email, login_password;
    private ImageView img_Google, img_ig, img_fb;
    private FirebaseAuth mAuth;

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
        login_email = findViewById(R.id.etv_loginFrm_email);
        login_password = findViewById(R.id.etv_loginFrm_Password);


        img_ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add instagram login
            }
        });
        img_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add google account login
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
                String email = login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();
//                accountLogin(email, password);

                if (email.equals("2")) { //login without email and password which is also a super user
                    login2();
                } else if (email.equals("3")) {//login for admin account
                    login3();
                } else if (email.equals("4")) {//login for customer account
                    login4();
                } else {
                    Toast.makeText(loginFrm.this, "Please select login options", Toast.LENGTH_SHORT).show();
                    login_email.requestFocus();
                    return;
                }

            }
        });
    }

    private void accountLogin(String email, String password) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(loginFrm.this, main_page_frm.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(loginFrm.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void login1() {
        String email = login_email.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            login_email.setError("Email is Required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            login_password.setError("Password is Required");
            return;
        }
        accountLogin(email, password);
    }

    private void login2() {
        Intent intent = new Intent(loginFrm.this, main_page_frm.class);
        startActivity(intent);
    }

    private void login3() {//adminAccount
        String email = "dapitoncaesar26@gmail.com";
        String password = "123456789";

        if (TextUtils.isEmpty(email)) {
            login_email.setError("Email is Required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            login_password.setError("Password is Required");
            return;
        }
        accountLogin(email, password);
    }

    private void login4() {//userAccount
        String email = "yosh02.2023@gmail.com";
        String password = "123456789";

        if (TextUtils.isEmpty(email)) {
            login_email.setError("Email is Required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            login_password.setError("Password is Required");
            return;
        }
        accountLogin(email, password);
    }

}