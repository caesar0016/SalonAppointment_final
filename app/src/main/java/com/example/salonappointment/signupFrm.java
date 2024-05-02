package com.example.salonappointment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class signupFrm extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser userProfile;
    private Button btnSignUp;
    private DatabaseReference dbRef;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private EditText inputName, inputEmail, inputPassword, inputConfirmPass;
    private ProgressBar progress;

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

        //---------Initializations-----------
        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        btnSignUp = findViewById(R.id.btn_signUpFrm_signup);
        inputName = (EditText) findViewById(R.id.etv_signUpFrm_name);
        inputEmail = findViewById(R.id.etv_signUpFrm_email);
        inputPassword = (EditText) findViewById(R.id.etv_signUpFrm_password);
        inputConfirmPass = (EditText) findViewById(R.id.etv_signUpFrm_Confirmpassword);
        progress = (ProgressBar) findViewById(R.id.signUp_pb);

        progress.setVisibility(View.GONE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDefaultPic();

            }
        });
    }

    private void uploadDefaultPic() {
        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String confirmPass = inputConfirmPass.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            inputName.setError("Please Input Name");
            inputName.requestFocus();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }

    @Override
    protected void onDestroy() { //needs to disable it when in production
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }

    private boolean emailValidation(String email) { //email validation function
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
//todo disable onStart when in production