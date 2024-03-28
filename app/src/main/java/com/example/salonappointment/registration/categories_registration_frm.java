package com.example.salonappointment.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.salonappointment.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class categories_registration_frm extends AppCompatActivity {

    private ImageView imgCategory;
    private EditText edCategoryName, edCategoryDesc;
    private Button btnRegisterCategory;
    private DatabaseReference dbRef;

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

        dbRef = FirebaseDatabase.getInstance().getReference().child("Service Category");

        btnRegisterCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}