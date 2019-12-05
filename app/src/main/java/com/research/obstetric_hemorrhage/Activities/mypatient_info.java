package com.research.obstetric_hemorrhage.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.research.obstetric_hemorrhage.Classes.Patient_Info;
import com.research.obstetric_hemorrhage.Firebase.DatabaseTransactions;
import com.research.obstetric_hemorrhage.Fragments.mypatient_info_recyclerview;
import com.research.obstetric_hemorrhage.R;


public class mypatient_info extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypatient_info_layout);
        recyclerView = findViewById(R.id.recycler_patientinfo);
        //this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        //getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        //getSupportActionBar().setElevation(0);
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
                holder.getDate().setText(model.getDate_added());
                holder.getHour().setText(model.getTime_added());
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
    }
}