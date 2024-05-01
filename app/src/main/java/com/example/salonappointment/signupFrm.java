package com.example.salonappointment;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.Model.register_acc_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class signupFrm extends AppCompatActivity {
    private ProgressBar progress;
    private EditText inputName, inputEmail, inputPass, inputConfirmPass;
    private Button btnSignUpFrm;
    private TextView tvLoginRedirect;
    private FirebaseAuth mAuth;
    DatabaseReference dbRef;
    static String userUID;
   // static String profileURl;

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
        mAuth = FirebaseAuth.getInstance();
        inputName = findViewById(R.id.etv_signUpFrm_name);
        inputEmail = findViewById(R.id.etv_signUpFrm_email);
        inputPass = findViewById(R.id.etv_signUpFrm_password);
        inputConfirmPass = findViewById(R.id.etv_signUpFrm_Confirmpassword);
        btnSignUpFrm = findViewById(R.id.btn_signUpFrm_signup);
        tvLoginRedirect = findViewById(R.id.signupFrm_loginDirect);

        //progressBar Initialization
        progress = findViewById(R.id.signUp_pb);
        progress.setVisibility(View.INVISIBLE);
        tvLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signupFrm.this, loginFrm.class);
                startActivity(intent);
                finish();
            }
        });
        dbRef = FirebaseDatabase.getInstance().getReference().child("User Accounts");
        btnSignUpFrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                String uploadName = inputName.getText().toString().trim();
                String uploadEmail = inputEmail.getText().toString().trim();
                String uploadPassword = inputPass.getText().toString().trim();
                String confirmPass = inputConfirmPass.getText().toString().trim();
                if (TextUtils.isEmpty(uploadName) || TextUtils.isEmpty(uploadEmail) || TextUtils.isEmpty(uploadPassword) ||
                        TextUtils.isEmpty(confirmPass)) {
                    Toast.makeText(signupFrm.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!emailValidation(uploadEmail)) {
                    inputEmail.setError("Invalid Email");
                    inputEmail.requestFocus();
                    return;
                }
                if (uploadPassword.length() < 6) {
                    inputPass.setError("Password length must be at least 6 characters");
                    inputPass.requestFocus();
                    return;
                }
                if (!uploadPassword.equals(confirmPass)) {
                    inputPass.setError("Password do not match");
                    inputPass.requestFocus();
                    return;
                }
                AuthAccount(uploadName, uploadEmail, uploadPassword);
            }
        });
        progress.setVisibility(View.INVISIBLE);
    }

    void SendData(String uploadName, String uploadEmail, String uploadPassword, String uid, String profileURl) {
        register_acc_model inputAccount = new register_acc_model(uploadName, uploadEmail, "Normal User", uid, profileURl);
        //push data
        dbRef.push().setValue(inputAccount).addOnCompleteListener(task -> {
            //Toast.makeText(signupFrm.this, "Success Send Data", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(signupFrm.this, "Failed to sendData", Toast.LENGTH_SHORT).show();
        });
    }

    void checkEmailExist(String name, String email, String password) {
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        SignInMethodQueryResult result = task.getResult();
                        List<String> signInMethods = result.getSignInMethods();
                        if (signInMethods != null && !signInMethods.isEmpty()) {
                            // Email already exists
                            Log.d(TAG, "Email already exist");
                        } else {
                            // Email does not exist, proceed with account creation
                            AuthAccount(name, email, password);
                        }
                    } else {
                        // Handle the error
                        Log.w(TAG, "fetchSignInMethodsForEmail:failure", task.getException());
                    }
                });
    }

    void AuthAccount(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success
                        Log.d(TAG, "createUserWithEmail:success");
                        Toast.makeText(signupFrm.this, "User account created successfully.", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        userUID = user.getUid();
                        Drawable drawable = getResources().getDrawable(R.drawable.profileicon);

                        // Convert the drawable to a Bitmap
                         Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                        // Create a byte array output stream to write the bitmap data
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();

                        // Compress the bitmap to PNG format and write it to the byte array output stream
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                        // Convert the byte array output stream to a byte array
                        byte[] imageData = baos.toByteArray();

                        // Upload the byte array to Firebase Storage
                        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                        StorageReference imageRef = storageRef.child("profilePictures/" + "defaultProfile.jpg"); // Set the path where you want to upload the image
                        UploadTask uploadTask = imageRef.putBytes(imageData);

                        // Listen for upload success/failure
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Image upload successful
                                Toast.makeText(getApplicationContext(), "Default image uploaded successfully", Toast.LENGTH_SHORT).show();

                                // Get the download URL of the uploaded image
                                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        // Handle the download URL
                                        String downloadUrl = uri.toString();
                                        SendData(name, email, password, userUID, downloadUrl);
                                        updateProfiles(name);
                                        // Use the download URL as needed
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Failed to get download URL
                                        Toast.makeText(getApplicationContext(), "Failed to get download URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Image upload failed
                                Toast.makeText(getApplicationContext(), "Failed to upload default image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        //signout incase
                        FirebaseAuth.getInstance().signOut();
                        //redirect the user to login page
                        Intent intent = new Intent(signupFrm.this, loginFrm.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        String message = "Authentication failed: " + task.getException().getMessage();
                        inputEmail.setError(message);
                        inputEmail.requestFocus();
                        return;
                    }
                });
    }

    void updateProfiles(String name) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User Update Success");

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, e.getMessage());
                    }
                });
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