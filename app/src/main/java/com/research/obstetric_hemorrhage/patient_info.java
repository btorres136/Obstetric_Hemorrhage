package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class patient_info extends AppCompatActivity {
    private TextView syspresOp;
    private GraphView graph_pres;
    private TextView dias_presOp;
    private TextView liquid_entryOp;
    private GraphView graph_liquid;
    private TextView liquid_dischargeOp;
    private GraphView graph_pulse;
    private TextView pulse_Op;
    private GraphView graph_blood_lost;
    private TextView blood_lostOp;
    private TextView patien_name;
    private TextView patient_id;
    private TextView patient_room;
    private TextView patient_age;
    private TextView patient_state;

    //private graph_data graph_data_obj = new graph_data();
    LineGraphSeries series;
    LineGraphSeries series2;
    String last_sys;
    String last_dias;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_info_layout);

        syspresOp = findViewById(R.id.input_syspres);
        graph_pres = findViewById(R.id.graph_presion);
        dias_presOp = findViewById(R.id.input_diaspres);
        liquid_entryOp = findViewById(R.id.input_ing_liquid);
        liquid_dischargeOp = findViewById(R.id.input_dis_liquid);
        graph_liquid = findViewById(R.id.graph_liquids);
        graph_pulse = findViewById(R.id.graph_pulso);
        pulse_Op = findViewById(R.id.input_pulso);
        graph_blood_lost = findViewById(R.id.graph_blood_lost);
        blood_lostOp = findViewById(R.id.input_blood_lost);

        patien_name = findViewById(R.id.patient_name_actual);
        patient_id = findViewById(R.id.patient_id_actual);
        patient_room = findViewById(R.id.patient_room_actual);
        patient_age = findViewById(R.id.patient_age_actual);
        patient_state = findViewById(R.id.patient_state_actual);

        patient_id.setText("ID: "+getIntent().getExtras().getString("PATIENT_ID"));
        patien_name.setText("Patient: "+getIntent().getExtras().getString("PATIENT_NAME"));
        patient_state.setText("Stage: "+getIntent().getExtras().getString("PATIENT_STATE"));
        patient_room.setText("Room: "+getIntent().getExtras().getString("PATIENT_ROOM"));
        patient_age.setText("Age: "+getIntent().getExtras().getString("PATIENT_AGE"));






        series = new LineGraphSeries();
        series2 = new LineGraphSeries();
        series.setColor(Color.RED);
        series2.setColor(Color.BLUE);
        graph_pres.addSeries(series);
        graph_pres.addSeries(series2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("/Patients_Graphs/"+getIntent().getExtras().getString("PATIENT_ID")+"/Pressure/Systolic/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                syspresOp = findViewById(R.id.input_syspres);
                DataPoint[] dataPoint = new DataPoint[(int)dataSnapshot.getChildrenCount()];
                int index = 0;

                for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
                    PointValue_sys pointValueSys = myDataSnapshot.getValue(PointValue_sys.class);

                    dataPoint[index] = new DataPoint(Integer.parseInt(pointValueSys.getTime()),Integer.parseInt(pointValueSys.getData()));
                    last_sys = pointValueSys.getData();
                    index++;
                }
                series.resetData(dataPoint);
                syspresOp.setText(Integer.toString((int)series.getHighestValueY()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference myRef2 = FirebaseDatabase.getInstance().getReference("/Patients_Graphs/"+getIntent().getExtras().getString("PATIENT_ID")+"/Pressure/Diastolic/");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dias_presOp = findViewById(R.id.input_diaspres);
                DataPoint[] dataPoint = new DataPoint[(int)dataSnapshot.getChildrenCount()];
                int index = 0;

                for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
                    PointValue_sys pointValueSys = myDataSnapshot.getValue(PointValue_sys.class);

                    dataPoint[index] = new DataPoint(Integer.parseInt(pointValueSys.getTime()),Integer.parseInt(pointValueSys.getData()));
                    last_dias= pointValueSys.getData();
                    index++;
                }

                series2.resetData(dataPoint);
                dias_presOp.setText(Integer.toString((int)series2.getHighestValueY()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
