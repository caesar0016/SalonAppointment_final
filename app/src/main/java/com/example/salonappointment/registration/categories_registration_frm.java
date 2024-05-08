package com.example.salonappointment.registration;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.Model.category_registration_model;
import com.example.salonappointment.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class categories_registration_frm extends AppCompatActivity {

    private ImageView imgCategory, btnBack;
    private EditText edCategoryName, edCategoryDesc;
    private Button btnRegisterCategory;
    private DatabaseReference dbRef;
    private StorageReference storageRef;
    private String url;
    private Uri selectedImageUri;
    private static String categoryName;

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
        btnBack = (ImageView) findViewById(R.id.categReg_btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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
                categoryName = edCategoryName.getText().toString().trim();
                String description = edCategoryDesc.getText().toString().trim();

                if (TextUtils.isEmpty(categoryName)) {
                    edCategoryName.requestFocus();
                    edCategoryName.setError("Category name cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    edCategoryDesc.requestFocus();
                    edCategoryDesc.setError("Description cannot be empty");
                    return;
                }
                uploadPicture();
                checkCategory(categoryName, description, url);

            }
        });
    }

    private void chooseImage() {
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
        if (selectedImageUri != null) {
            StorageReference imageRef = storageRef.child("categoryImages/" + categoryName + ".jpg");
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get the download URL of the uploaded image
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    url = uri.toString();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(categories_registration_frm.this, "Failed to get download URL" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                            Toast.makeText(categories_registration_frm.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(categories_registration_frm.this, "Upload Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }


    private void checkCategory(String category, String desc, String url) {
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean existingCategory = false;
                for (DataSnapshot item : snapshot.getChildren()) {
                    category_registration_model existCateg = item.getValue(category_registration_model.class);
                    if (existCateg != null && existCateg.getCategoryName().equals(category)) {
                        existingCategory = true;
                        Toast.makeText(categories_registration_frm.this, "Category already exist", Toast.LENGTH_SHORT).show();
                        break; // Break out of the loop if category already exists

                    }
                }

                // Check if the category doesn't exist, then add it
                if (!existingCategory) {
                    category_registration_model r1 = new category_registration_model(category, desc, url);
                    dbRef.push().setValue(r1);
                } else {
                    // Display a message indicating that the category already exists
                    Toast.makeText(getApplicationContext(), "Category already exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Text");

        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInput = input.getText().toString();
                // Handle the user input here
                Toast.makeText(getApplicationContext(), "You entered: " + userInput, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void clear(){

    }

}
