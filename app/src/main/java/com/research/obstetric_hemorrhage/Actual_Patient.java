package com.research.obstetric_hemorrhage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;


public class Actual_Patient extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ActualPatient_RecyclerView adapter;
    private RecyclerView recyclerView;
    private ArrayList<Patient_Medical> My_Patients_Array;
    private SwipeRefreshLayout swipe;
    private View rootView;

    public Actual_Patient(){
        My_Patients_Array = new ArrayList<>();
    }

    public void setMy_Patients_Array(ArrayList<Patient_Medical> Patients_Array){
        My_Patients_Array = Patients_Array;
    }

    public ArrayList<Patient_Medical> getMy_Patients_Array(){
        return My_Patients_Array;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_actual__patient, container, false);

        swipe = rootView.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);
        adapter = new ActualPatient_RecyclerView(My_Patients_Array);
        adapter.notifyDataSetChanged();
        recyclerView = rootView.findViewById(R.id.recycler_mypat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }


    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        adapter = new ActualPatient_RecyclerView(My_Patients_Array);
        recyclerView = rootView.findViewById(R.id.recycler_mypat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe.setRefreshing(false);
    }
}
