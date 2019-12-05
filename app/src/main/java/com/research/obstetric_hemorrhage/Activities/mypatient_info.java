package com.research.obstetric_hemorrhage.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.research.obstetric_hemorrhage.Classes.Patient_Info;
import com.research.obstetric_hemorrhage.Firebase.DatabaseTransactions;
import com.research.obstetric_hemorrhage.Fragments.mypatient_info_recyclerview;
import com.research.obstetric_hemorrhage.R;

import java.text.DecimalFormat;


public class mypatient_info extends AppCompatActivity {
    EditText sis_press;
    EditText dis_press;
    EditText ebl;
    EditText perfusion;
    EditText mental;
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
        perfusion = findViewById(R.id.perfusion);
        mental = findViewById(R.id.mental);
        hr = findViewById(R.id.hr);
        update_infoCV = findViewById(R.id.update_infoCV);
        String PatientID = getIntent().getExtras().getString("PATIENT_ID");
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
                String perf = perfusion.getText().toString();
                String mentals = mental.getText().toString();
                String hrs = hr.getText().toString();
                sis_press.getText().clear();
                dis_press.getText().clear();
                ebl.getText().clear();
                perfusion.getText().clear();
                mental.getText().clear();
                hr.getText().clear();
                if(sis.isEmpty() || dis.isEmpty() || ebls.isEmpty() || perf.isEmpty() ||
                mentals.isEmpty() || hrs.isEmpty()){
                    Toast.makeText(view.getContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                }else{
                    DecimalFormat df = new DecimalFormat("###.###");
                    double shock = (Double.parseDouble(hrs)/Double.parseDouble(sis));
                    final MediaPlayer md = MediaPlayer.create(view.getContext(), R.raw.alert);
                    databaseTransactions.addPatientinfo(getIntent().getExtras().getString("PATIENT_ID"),ebls,"0",
                            dis,hrs,mentals,perf,df.format(shock),sis);

                    if (Integer.parseInt(sis) >= 90)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.green);
                        builder.setTitle("Stage 0");
                        builder.setNegativeButton("Ok", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    else if (Integer.parseInt(sis) <=89 && Integer.parseInt(sis) >=80)
                    {
                        md.start();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext(), R.style.yellow);
                        builder1.setTitle("Stage 1");
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
                        builder3.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                md.stop();
                            }
                        });

                        AlertDialog dialog3 = builder3.create();
                        dialog3.show();
                    }

                }

            }
        });


    }
}