package com.research.obstetric_hemorrhage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Patient_Fragment extends Fragment {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mPatientNames = new ArrayList<>();
    private ArrayList<String> mAges =  new ArrayList<>();
    private ArrayList<String> mid = new ArrayList<>();
    private ArrayList<String> mstatus = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_, container, false);
        recyclerView = rootView.findViewById(R.id.patient_recycle);
        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        mPatientNames.add("Giana Alvarado");
        mAges.add("22");
        mid.add("AS453AD");
        mstatus.add("Stage 3");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mPatientNames,mAges,mid,mstatus, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

}
