package com.research.obstetric_hemorrhage.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.research.obstetric_hemorrhage.Classes.Patient_Info;
import com.research.obstetric_hemorrhage.Firebase.DatabaseTransactions;
import com.research.obstetric_hemorrhage.Fragments.mypatient_info_recyclerview;
import com.research.obstetric_hemorrhage.R;
import com.research.obstetric_hemorrhage.Services.Alarm;

import java.text.DecimalFormat;


public class mypatient_info extends AppCompatActivity {
    EditText sis_press;
    EditText dis_press;
    EditText ebl;
    CheckBox men_normal;
    CheckBox men_agitated;
    CheckBox men_Lethargic;
    CheckBox men_unconsicious;
    CheckBox per_normal;
    CheckBox per_pallor;
    CheckBox per_coldness;
    CheckBox per_sweating;
    CheckBox capillary;
    Spinner spinner;


    EditText hr;
    CardView update_infoCV;
    DatabaseTransactions db = new DatabaseTransactions();
    private RecyclerView recyclerView ;
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypatient_info_layout);
        recyclerView = findViewById(R.id.recycler_patientinfo);
        sis_press = findViewById(R.id.sis_press);
        dis_press = findViewById(R.id.dis_press);
        ebl = findViewById(R.id.ebl);
        men_normal = findViewById(R.id.normal);
        men_Lethargic = findViewById(R.id.lethargic);
        men_agitated = findViewById(R.id.agitated);
        men_unconsicious = findViewById(R.id.unconscious);
        per_normal = findViewById(R.id.normal1);
        per_pallor = findViewById(R.id.pallor);
        per_coldness = findViewById(R.id.coldness);
        per_sweating = findViewById(R.id.sweating);
        capillary = findViewById(R.id.capillary);

        spinner = findViewById(R.id.time_spinner);
        ArrayAdapter<CharSequence> adapterspinner = ArrayAdapter.createFromResource(this, R.array.time_spinner, android.R.layout.simple_spinner_item);
        adapterspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterspinner);


        hr = findViewById(R.id.hr);
        update_infoCV = findViewById(R.id.update_infoCV);
        final String PatientID = getIntent().getExtras().getString("PATIENT_ID");
        final String PatientName = getIntent().getExtras().getString("PATIENT_NAME");
        FirebaseRecyclerOptions<Patient_Info> options =
                new FirebaseRecyclerOptions.Builder<Patient_Info>()
                        .setQuery(databaseTransactions.ListentoDatabaseOnData(PatientID), Patient_Info.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Patient_Info, mypatient_info_recyclerview>(options) {
            @NonNull
            @Override
            public mypatient_info_recyclerview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.mypatientinfo,parent,false);
                mypatient_info_recyclerview patients_recyclerView =  new mypatient_info_recyclerview(view);
                return patients_recyclerView;
            }
            @Override
            protected void onBindViewHolder(@NonNull mypatient_info_recyclerview holder, int position, @NonNull Patient_Info model) {
                holder.getDate().setText("Date: "+model.getDate_added());
                holder.getHour().setText("Time: "+model.getTime_added());
                holder.getPerfusion().setText(model.getPerfusion());
                holder.getSis_press().setText(model.getSystolic_Presure());
                holder.getShock().setText(model.getShock_Index());
                holder.getEbl().setText(model.getBlood_loss());
                holder.getDis_press().setText(model.getDiastolic_Presure());
                holder.getMental().setText(model.getMental());
                holder.getHeart_Rate().setText(model.getHeart_Rate());
                holder.getStage().setText(model.getStage());
            }
        };
        adapter.startListening();
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayout);
        update_infoCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sis = sis_press.getText().toString();
                String dis = dis_press.getText().toString();
                String ebls = ebl.getText().toString();
                String hrs = hr.getText().toString();
                String men="";
                String per="";
                if(per_normal.isChecked()){
                    per+=" Normal";
                }
                if(per_coldness.isChecked()){
                    per+=" Coldness";
                }
                if(per_pallor.isChecked()){
                    per+=" Pallor";
                }
                if(per_sweating.isChecked()){
                    per+=" sweating";
                }
                if(men_normal.isChecked()){
                    men=" Normal";
                }
                if(men_agitated.isChecked()){
                    men+=" agitated";
                }
                if(men_Lethargic.isChecked()){
                    men+=" Lethargic";
                }
                if(men_unconsicious.isChecked()){
                    men+=" inconsicious";
                }
                if(capillary.isChecked()){
                    per+=" Capillary";
                }

                if(sis.isEmpty() || dis.isEmpty() || ebls.isEmpty() || hrs.isEmpty() || per.isEmpty() || men.isEmpty()){
                    Toast.makeText(view.getContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                }else{

                    men_unconsicious.setChecked(false);
                    men_Lethargic.setChecked(false);
                    men_agitated.setChecked(false);
                    men_normal.setChecked(false);
                    per_sweating.setChecked(false);
                    per_pallor.setChecked(false);


                    per_coldness.setChecked(false);
                    per_normal.setChecked(false);
                    capillary.setChecked(false);
                    sis_press.getText().clear();
                    dis_press.getText().clear();
                    ebl.getText().clear();
                    hr.getText().clear();
                    DecimalFormat df = new DecimalFormat("###.###");
                    double shock = (Double.parseDouble(hrs)/Double.parseDouble(sis));
                    final MediaPlayer md = MediaPlayer.create(view.getContext(), R.raw.alert);


                    if ((Integer.parseInt(sis) >= 90))
                    //ay diojjjj
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.green);
                        builder.setTitle("Stage 0");
                        builder.setNegativeButton("Ok", null);
                        databaseTransactions.addPatientinfo(getIntent().getExtras().getString("PATIENT_ID"),ebls,"0",
                                dis,hrs,men,per,df.format(shock),sis);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    else if (Integer.parseInt(sis) <=89 && Integer.parseInt(sis) >=80)
                    {
                        md.start();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext(), R.style.yellow);
                        builder1.setTitle("Stage 1");
                        databaseTransactions.addPatientinfo(getIntent().getExtras().getString("PATIENT_ID"),ebls,"1",
                                dis,hrs,men,per,df.format(shock),sis);
                        builder1.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                md.stop();
                            }
                        });


                        AlertDialog dialog1 = builder1.create();
                        dialog1.show();
                    }
                    else if (Integer.parseInt(sis) <=79 && Integer.parseInt(sis) >=71)
                    {
                        md.start();
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(view.getContext(), R.style.orange);
                        databaseTransactions.addPatientinfo(getIntent().getExtras().getString("PATIENT_ID"),ebls,"2",
                                dis,hrs,men,per,df.format(shock),sis);
                        builder2.setTitle("Stage 2");
                        builder2.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                md.stop();
                            }
                        });

                        AlertDialog dialog2 = builder2.create();
                        dialog2.show();
                    }
                    else if (Integer.parseInt(sis) <= 70)
                    {
                        md.start();
                        AlertDialog.Builder builder3 = new AlertDialog.Builder(view.getContext(), R.style.red);
                        builder3.setTitle("Stage 3");
                        databaseTransactions.addPatientinfo(getIntent().getExtras().getString("PATIENT_ID"),ebls,"3",
                                dis,hrs,men,per,df.format(shock),sis);
                        builder3.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                md.stop();
                            }
                        });

                        AlertDialog dialog3 = builder3.create();
                        dialog3.show();
                    }
                    int number = Integer.parseInt(spinner.getSelectedItem().toString());
                    Intent intent = new Intent(view.getContext(), Alarm.class);
                    intent.putExtra("PATIENT_NAME",PatientName);
                    final int _id = (int) System.currentTimeMillis();
                    intent.putExtra("ID",_id);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(view.getContext(),_id,intent,PendingIntent.FLAG_ONE_SHOT);
                    AlarmManager alarmManager = (AlarmManager) view.getContext().getSystemService(ALARM_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+number*1000,pendingIntent);
                    }
                }


            }
        });


    }
}