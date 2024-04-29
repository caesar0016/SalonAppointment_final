package com.example.salonappointment.registration;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.Model.category_registration_model;
import com.example.salonappointment.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class categories_registration_frm extends AppCompatActivity {

    private ImageView imgCategory;
    private EditText edCategoryName, edCategoryDesc;
    private Button btnRegisterCategory;
    private DatabaseReference dbRef;
    private StorageReference storageRef;
    private String url;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.categories_registration_frm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Initialization of the components
        imgCategory = findViewById(R.id.add_img_category);
        edCategoryName = findViewById(R.id.etv_signUpFrm_name);
        edCategoryDesc = findViewById(R.id.etvDesc_categoriesRegistration);
        btnRegisterCategory = findViewById(R.id.btnRegisterCategory);
        storageRef = FirebaseStorage.getInstance().getReference();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Service Category");


        //this goes the events
        imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        //btnRegisterCategory set on click event
        btnRegisterCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPicture();
                String category = edCategoryName.getText().toString().trim();
                String description = edCategoryDesc.getText().toString().trim();

                if(TextUtils.isEmpty(category)){
                    edCategoryName.requestFocus();
                    edCategoryName.setError("Category name cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(description)){
                    edCategoryDesc.requestFocus();
                    edCategoryDesc.setError("Description cannot be empty");
                    return;
                }

                category_registration_model r1 = new category_registration_model(category, description, url);
                dbRef.push().setValue(r1);
            }
        });
    }
    private void chooseImage(){
        pickMedia.launch(new PickVisualMediaRequest());
    }
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                    selectedImageUri = uri;
                    imgCategory.setImageURI(selectedImageUri);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });
    private void uploadPicture() {
        if(selectedImageUri != null){
            StorageReference imageRef = storageRef.child("categoryImages/" + UUID.randomUUID().toString());
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(categories_registration_frm.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            url = imageRef.getDownloadUrl().toString();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(categories_registration_frm.this, "Upload Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }

}
