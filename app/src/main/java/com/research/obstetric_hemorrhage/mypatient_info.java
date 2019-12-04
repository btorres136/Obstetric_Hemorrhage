package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class mypatient_info extends AppCompatActivity {

    private GraphView graph_pres;
    private EditText input_sys;
    private EditText input_dias;

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
        setContentView(R.layout.mypatient_info_layout);


        graph_pres = findViewById(R.id.graph_presion);



        patien_name = findViewById(R.id.patient_name_actual);
        patient_id = findViewById(R.id.patient_id_actual);
        patient_room = findViewById(R.id.patient_room_actual);
        patient_age = findViewById(R.id.patient_age_actual);
        patient_state = findViewById(R.id.patient_state_actual);
        update_info = findViewById(R.id.update_infoCV);

        input_sys = findViewById(R.id.input_syspres);
        input_dias = findViewById(R.id.input_diaspres);

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
                int hour = rightNow.get(Calendar.HOUR); //return the hour in 12 hrs format (ranging from 0-11)
                int minute = rightNow.get(Calendar.MINUTE);
                int seconds = rightNow.get(Calendar.SECOND);
                String time = Integer.toString(minute);
                final MediaPlayer mp = MediaPlayer.create(view.getContext(), R.raw.alert);

                if (Integer.parseInt(input_sys.getText().toString()) >= 90)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.green);
                    builder.setTitle("verde!");
                    builder.setNegativeButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                        }
                    });
                }
                else if (Integer.parseInt(input_sys.getText().toString()) <=89 && Integer.parseInt(input_sys.getText().toString()) >=80)
                {
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                        }
                    });
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext(), R.style.yellow);
                    builder1.setTitle("amarilla!");
                    builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    mediaPlayer.stop();
                                }
                            });
                        }
                    });
                    AlertDialog dialog1 = builder1.create();
                    dialog1.show();


                }
                else if (Integer.parseInt(input_sys.getText().toString()) <=79 && Integer.parseInt(input_sys.getText().toString()) >=71)
                {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(view.getContext(), R.style.orange);
                    builder2.setTitle("orange!");
                    builder2.setNegativeButton("Cancel", null);
                    AlertDialog dialog2 = builder2.create();
                    dialog2.show();
                }
                else if (Integer.parseInt(input_sys.getText().toString()) <= 70)
                {
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(view.getContext(), R.style.red);
                    builder3.setTitle("rojo!");
                    builder3.setNegativeButton("Cancel", null);
                    AlertDialog dialog3 = builder3.create();
                    dialog3.show();

                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                        }
                    });
                }

                databaseTransactions.AddGraphDataSys(input_sys.getText().toString(),time,getIntent().getExtras().getString("PATIENT_ID"),key);
                databaseTransactions.AddGraphDataDias(input_dias.getText().toString(),time,getIntent().getExtras().getString("PATIENT_ID"),key);

            }
        });

    }

    public void onStart() {
        super.onStart();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("/Patients_Graphs/"+getIntent().getExtras().getString("PATIENT_ID")+"/Pressure/Systolic/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference myRef2 = FirebaseDatabase.getInstance().getReference("/Patients_Graphs/"+getIntent().getExtras().getString("PATIENT_ID")+"/Pressure/Diastolic/");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}