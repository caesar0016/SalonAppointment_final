package com.example.salonappointment.editData;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.salonappointment.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class editUserAcccount extends AppCompatActivity {
    private CircleImageView userProfileImg;
    private Uri uriProfile;
    private StorageReference storageRef;
    private Button btnSave;
    private EditText edDesc;
    private TextInputEditText edName, edPass, edConfirmPass;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_user_acccount);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //-------------- Initialization ---------------------
        userProfileImg = findViewById(R.id.eua_UserProfileImg);
        btnSave = findViewById(R.id.eua_btnSave);
        storageRef = FirebaseStorage.getInstance().getReference();
        edName = findViewById(R.id.eua_editName);
        edPass = findViewById(R.id.eua_edCurrentPass);

        displayProfiles();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFirebase();
                //updateProfiles();
                edPass.setText(url);
            }
        });

        userProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();

            }
        });

    }

    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                    uriProfile = uri;
                    userProfileImg.setImageURI(uriProfile);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });

    private void chooseImage() {
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    private void uploadToFirebase() {
        if (uriProfile == null) {
            Toast.makeText(editUserAcccount.this, "No image selected", Toast.LENGTH_SHORT).show();
            return;
        }

        StorageReference strImgProfile = storageRef.child("profilePictures/" + UUID.randomUUID().toString());

        strImgProfile.putFile(uriProfile)
                .addOnSuccessListener(this::handleUploadSuccess)
                .addOnFailureListener(this::handleUploadFailure);
    }

    private void handleUploadSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        StorageReference strImgProfile = taskSnapshot.getStorage();

        // Get the download URL of the uploaded image
        strImgProfile.getDownloadUrl()
                .addOnSuccessListener(this::handleDownloadUrlSuccess)
                .addOnFailureListener(this::handleDownloadUrlFailure);
    }

    private void handleUploadFailure(Exception e) {
        Toast.makeText(editUserAcccount.this, "Error uploading image", Toast.LENGTH_SHORT).show();
    }

    private void handleDownloadUrlSuccess(Uri uri) {
        // Get the URL string
        String url = uri.toString();

        // Update the user's profile with the image URL
        updateProfileWithImageUrl(url);
        edPass.setText(url);
    }

    private void handleDownloadUrlFailure(Exception e) {
        Toast.makeText(editUserAcccount.this, "Error getting download URL", Toast.LENGTH_SHORT).show();
    }

    private void updateProfileWithImageUrl(String url) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(url))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(editUserAcccount.this, "User Profile Update", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void displayProfiles() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            if (name != null) {
                edName.setText(name);
            }

            Uri photoUrl = user.getPhotoUrl();
            if (photoUrl != null) {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_profile_one) // Placeholder image while loading
                        .error(R.drawable.ic_profile_one); // Image to show if loading fails

                Glide.with(this)
                        .load(photoUrl)
                        .apply(options)
                        .into(userProfileImg);
            } else {
                // Handle the case when user's photo URL is null
                // You can set a default profile picture or hide the ImageView
                userProfileImg.setImageResource(R.drawable.ic_profile_one);
                // profile.setVisibility(View.GONE);
            }
        } else {
            // Handle the case when the user is not signed in
            // You can navigate to the sign-in screen or show a message to prompt sign-in
        }
        //  tvGetUserName.setText(name);
    }
}