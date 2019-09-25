package com.research.obstetric_hemorrhage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Actual_Patient extends Fragment {
    private ArrayList<String> mPatientNames = new ArrayList<>();
    private ArrayList<Integer> mAges =  new ArrayList<>();
    private ArrayList<String> mid = new ArrayList<>();
    private ArrayList<String> mstatus = new ArrayList<>();
    RecyclerView recyclerView;

    public void add(String Pat_Name, Integer Age, String id, String mStatus){
        mPatientNames.add(Pat_Name);
        mAges.add(Age);
        mid.add(id);
        mstatus.add(mStatus);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ActualPatient_RecyclerView adapter = new ActualPatient_RecyclerView(mPatientNames, mAges);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return inflater.inflate(R.layout.fragment_actual__patient, container, false );
    }
}
