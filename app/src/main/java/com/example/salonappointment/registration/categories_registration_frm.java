package com.example.salonappointment.registration;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
    private static String url;

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
                category_registration_model r1 = new category_registration_model(category, description, url);
                dbRef.push().setValue(r1);
            }
        });
    }
    private void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            selectedImageUri = data.getData();
            imgCategory.setImageURI(selectedImageUri);
        }
    }

    private void uploadPicture() {
        if(selectedImageUri != null){
            StorageReference imageRef = storageRef.child("images/" + UUID.randomUUID().toString());
            url = imageRef.getDownloadUrl().toString();
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(categories_registration_frm.this, "Upload Successful", Toast.LENGTH_SHORT).show();
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

//todo remake the code where it upload the image first