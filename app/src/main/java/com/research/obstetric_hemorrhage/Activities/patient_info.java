package com.research.obstetric_hemorrhage.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.research.obstetric_hemorrhage.R;

public class patient_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_info_layout);
        //patient_name.setText("Patient: "+getIntent().getExtras().getString("PATIENT_NAME"));
        //patient_state.setText("Stage: "+getIntent().getExtras().getString("PATIENT_STATE"));
        //patient_room.setText("Room: "+getIntent().getExtras().getString("PATIENT_ROOM"));
        //patient_age.setText("Age: "+getIntent().getExtras().getString("PATIENT_AGE"));


    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
