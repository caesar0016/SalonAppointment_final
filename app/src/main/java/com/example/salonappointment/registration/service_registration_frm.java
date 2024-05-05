package com.example.salonappointment.registration;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.Model.register_service_model;
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

public class service_registration_frm extends AppCompatActivity {
    private DatabaseReference dbRef;
    private ArrayAdapter<String> adapter;
    private Button btnRegister;
    private ImageView imgService, btnBack;
    private Uri selectedImageUri;
    private FirebaseStorage storage;
    private static String url;
    private StorageReference storageRef;
    private EditText edName, edDesc, edPrice, edDuration;
    private static String serviceName;
    private static String serviceUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.service_registration_frm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialization
        btnRegister = findViewById(R.id.serviceReg_btnRegister);
        imgService = findViewById(R.id.add_img_service);
        btnBack = (ImageView) findViewById(R.id.serviceReg_btnBack);

        //Edit Text Initialization
        edName = findViewById(R.id.serviceReg_edServiceName);
        edDesc = findViewById(R.id.serviceReg_edDesc);
        edPrice = findViewById(R.id.serviceReg_edPrice);

        /// Firebase Storage Initialization
        storageRef = FirebaseStorage.getInstance().getReference();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // ---------------------------Image Upload was click event---------------------------
        imgService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        //---------------------------btnRegister was click event---------------------------
        btnRegister.setOnClickListener(new View.OnClickListener() { //btnRegister Click Event
            @Override
            public void onClick(View v) {


                // If data is valid, proceed with uploading picture and saving data
                uploadPicture();
            }
        });
    }

    private void existingService(String name, String desc, double price, String url) {
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean existService = false;

                for (DataSnapshot item : snapshot.getChildren()) {
                    register_service_model serviceModel = item.getValue(register_service_model.class);

                    if (serviceModel != null && serviceModel.getServiceName().equals(serviceName)) {
                        existService = true;
                        break;
                    }
                }
                if (!existService) {
                    register_service_model r1 = new register_service_model(name, desc, price, url);
                    dbRef.push().setValue(r1);
                } else {
                    Toast.makeText(service_registration_frm.this, "This Service Already exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //----------------------This is for method only, down here ----------------------
    private void chooseImage() { //let the user choose only one image
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    //---------------------------pickMedia for choosing image method---------------------------
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia = //calls the method pickMedia which is associated to the Photo Picker
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                    selectedImageUri = uri;
                    imgService.setImageURI(selectedImageUri);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });

    //---------------------------Uploading the image to FIrebase ---------------------------
    private void uploadPicture() {
        if (selectedImageUri != null) {
            StorageReference imageRef = storageRef.child("serviceImages/" + serviceName + ".jpg");
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(service_registration_frm.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    dbRef = FirebaseDatabase.getInstance().getReference("Services");
                                    serviceName = edName.getText().toString().trim();
                                    String desc = edDesc.getText().toString().trim();
                                    String priceStr = edPrice.getText().toString().trim();
                                    double price = Double.parseDouble(priceStr);

                                    // Parse price and handle errors
                                    try {
                                        price = Double.parseDouble(priceStr);
                                    } catch (NumberFormatException e) {
                                        // Parsing failed, show an error message and return
                                        Toast.makeText(service_registration_frm.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    if (TextUtils.isEmpty(serviceName)) {
                                        edName.setError("Service name cannot be empty");
                                        edName.requestFocus();
                                        return;
                                    }
                                    if (TextUtils.isEmpty(desc)) {
                                        edDesc.requestFocus();
                                        edDesc.setError("Description cannot be empty");
                                        return;
                                    }
                                    if (TextUtils.isEmpty(priceStr)) {
                                        edPrice.requestFocus();
                                        edPrice.setError("Price cannot be empty");
                                        return;
                                    }
                                    serviceUrl = uri.toString();
                                    existingService(serviceName, edDesc.getText().toString().trim(), Double.parseDouble(edPrice.getText().toString().trim()), serviceUrl);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(service_registration_frm.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(service_registration_frm.this, "Upload Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }

    // ---------------------------shows the data for choosing the category of a service ---------------------------
}