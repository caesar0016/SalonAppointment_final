package com.example.salonappointment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.Model.register_acc_model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupFrm extends AppCompatActivity {

    private EditText inputName, inputEmail, inputPass, inputConfirmPass;
    private Button btnSignUpFrm;

   // private StorageReference reference;
    FirebaseDatabase database;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup_frm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //this is their find by view id
        inputName = findViewById(R.id.etv_signUpFrm_name);
        inputEmail = findViewById(R.id.etv_signUpFrm_email);
        inputPass = findViewById(R.id.etv_signUpFrm_password);
        inputConfirmPass = findViewById(R.id.etv_signUpFrm_Confirmpassword);
        btnSignUpFrm = findViewById(R.id.btn_signUpFrm_signup);
        
        btnSignUpFrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uploadName = inputName.getText().toString();
                String uploadEmail = inputEmail.getText().toString();
                String uploadPassword = inputPass.getText().toString();
                String confirmPass = inputConfirmPass.getText().toString();
                if(TextUtils.isEmpty(uploadName) || TextUtils.isEmpty(uploadEmail) || TextUtils.isEmpty(uploadPassword) ||
                        TextUtils.isEmpty(confirmPass)){
                    Toast.makeText(signupFrm.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!uploadPassword.equals(confirmPass)){
                    Toast.makeText(signupFrm.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                SendData(uploadName, uploadEmail, uploadPassword);
            }
        });
    }
    void SendData(String uploadName, String uploadEmail, String uploadPassword){ //is a method that sends data into firebase
        dbRef = FirebaseDatabase.getInstance().getReference().child("Accounts");
        register_acc_model inputAccount = new register_acc_model(uploadName, uploadEmail, uploadPassword);
        dbRef.push().setValue(inputAccount).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(signupFrm.this, "SignUp Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signupFrm.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}