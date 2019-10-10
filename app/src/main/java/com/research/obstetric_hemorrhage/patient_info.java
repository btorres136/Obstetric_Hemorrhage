package com.research.obstetric_hemorrhage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patient_info extends AppCompatActivity {
    private CardView back_to_pat;

    private FirebaseDatabase data_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_info_layout);

        back_to_pat = findViewById(R.id.back_to_patientsCV);
        back_to_pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(patient_info.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //////////////////////////////////////
        DatabaseReference ref_info = data_info.getReference("/Patients_Graphs/");


        /////////////////////////////////////

        String ID = getIntent().getExtras().getString("PATIENT_ID");

    }
}
