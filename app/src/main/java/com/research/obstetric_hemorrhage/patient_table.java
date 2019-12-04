package com.research.obstetric_hemorrhage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class patient_table extends AppCompatActivity {

    private TableLayout patient_table;
    private EditText lhour;
    private EditText Syst_Pres;
    private EditText EBL;
    private EditText HR;
    private EditText MH;
    private EditText perfussion;
    private EditText HR_prc;
    private EditText Sys_press_prc;
    private EditText sat;
    private EditText shock_index;

    private TextView patien_name;
    private TextView patient_id;
    private TextView patient_room;
    private TextView patient_age;
    private TextView patient_state;
    private CardView update_info;
    private FirebaseDatabase database= FirebaseDatabase.getInstance();

    private DatabaseTransactions databaseTransactions = new DatabaseTransactions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_table_layout);

        patien_name = findViewById(R.id.patient_name_actual);
        patient_id = findViewById(R.id.patient_id_actual);
        patient_room = findViewById(R.id.patient_room_actual);
        patient_age = findViewById(R.id.patient_age_actual);
        patient_state = findViewById(R.id.patient_state_actual);
        update_info = findViewById(R.id.update_infoCV);

        patient_table = findViewById(R.id.patient_table);
        lhour = findViewById(R.id.hour_n);
        Syst_Pres = findViewById(R.id.sys_pressure_n);
        EBL = findViewById(R.id.ebl_n);
        HR = findViewById(R.id.hr_n);
        MH = findViewById(R.id.mh_n);
        perfussion = findViewById(R.id.perf_n);
        HR_prc = findViewById(R.id.hr_prc_n);
        Sys_press_prc = findViewById(R.id.sys_press_prc_n);
        sat = findViewById(R.id.sat_n);
        shock_index = findViewById(R.id.shock_n);

        patient_id.setText("ID: "+getIntent().getExtras().getString("PATIENT_ID"));
        patien_name.setText("Patient: "+getIntent().getExtras().getString("PATIENT_NAME"));
        patient_state.setText("Stage: "+getIntent().getExtras().getString("PATIENT_STATE"));
        patient_room.setText("Room: "+getIntent().getExtras().getString("PATIENT_ROOM"));
        patient_age.setText("Age: "+getIntent().getExtras().getString("PATIENT_AGE"));

        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar rightNow = Calendar.getInstance();
                String key = database.getReference().push().getKey();
                int hour = rightNow.get(Calendar.HOUR); // return the hour in 12 hrs format (ranging from 0-11)
                int minute = rightNow.get(Calendar.MINUTE);
                int seconds = rightNow.get(Calendar.SECOND);
                String time = Integer.toString(minute);

            }
        });
    }
}
