package com.research.obstetric_hemorrhage.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.research.obstetric_hemorrhage.Classes.Patient_Info;
import com.research.obstetric_hemorrhage.Classes.Patient_Medical;
import com.research.obstetric_hemorrhage.Firebase.DatabaseTransactions;
import com.research.obstetric_hemorrhage.Fragments.Patients_RecyclerView;
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
        String PatientID = getIntent().getExtras().getString("PATIENT_NAME");

        FirebaseRecyclerOptions<Patient_Info> options =
                new FirebaseRecyclerOptions.Builder<Patient_Info>()
                        .setQuery(databaseTransactions.ListentoDatabaseOnData(PatientID), Patient_Info.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Patient_Info, mypatient_info_recyclerview>(options) {
            @NonNull
            @Override
            public mypatient_info_recyclerview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            protected void onBindViewHolder(@NonNull mypatient_info_recyclerview holder, int position, @NonNull Patient_Info model) {

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