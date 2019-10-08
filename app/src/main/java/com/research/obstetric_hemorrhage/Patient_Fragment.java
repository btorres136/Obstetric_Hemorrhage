package com.research.obstetric_hemorrhage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class Patient_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions();

    private ProgressBar bar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

            }
        };
        adapter.startListening();

        //recyclerView.smoothScrollToPosition(0);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bar.setVisibility(View.GONE);

        return rootView;
    }


    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        //Patients_RecyclerView adapter = new Patients_RecyclerView(All_Patients_Array);
        //recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe.setRefreshing(false);
    }
}
