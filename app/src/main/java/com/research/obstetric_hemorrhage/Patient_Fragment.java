package com.research.obstetric_hemorrhage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Patient_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions();

    private ProgressBar bar;




    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_, container, false);
        recyclerView = rootView.findViewById(R.id.patient_recycle);
        swipe = rootView.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);
        bar=rootView.findViewById(R.id.waittime);

        FirebaseRecyclerOptions<Patient_Medical> options =
                new FirebaseRecyclerOptions.Builder<Patient_Medical>()
                        .setQuery(databaseTransactions.ListenToDatabaseOnAllPatients(), Patient_Medical.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Patient_Medical, Patients_RecyclerView>(options) {
            @NonNull
            @Override
            public Patients_RecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.patientlist_layout,parent,false);
                Patients_RecyclerView patients_recyclerView =  new Patients_RecyclerView(view);
                return patients_recyclerView;
            }

            @Override
            protected void onBindViewHolder(@NonNull Patients_RecyclerView holder, int position, @NonNull final Patient_Medical model) {
                holder.getTextView_Patient().setText("Patient: "+model.getName());
                holder.getTextView_Age().setText("Age: "+model.getAge());
                holder.getTextView_id().setText("ID: "+model.getID());
                holder.getTextView_room().setText("Room: "+model.getRoom());
                holder.getTextView_status().setText("Stage: "+model.getStage());
                holder.getAddtomypat().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseTransactions.add(model.getName(),
                                model.getAge(), model.getStage(), model.getRoom(), model.getID());
                    }
                });
                holder.getSee_info().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), patient_info.class);
                        intent.putExtra("PATIENT_ID",model.getID());
                        startActivity(intent);
                    }
                });



            }
        };
        adapter.startListening();

        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayout);

        bar.setVisibility(View.GONE);

        return rootView;
    }


    @Override
    public void onRefresh() {
        swipe.setRefreshing(false);
    }
}
