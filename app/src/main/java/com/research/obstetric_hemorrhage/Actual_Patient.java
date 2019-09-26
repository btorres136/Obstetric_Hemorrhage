package com.research.obstetric_hemorrhage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Actual_Patient extends Fragment {
    private ArrayList<String> mPatientNames = new ArrayList<>();
    private ArrayList<Integer> mAges =  new ArrayList<>();
    private ArrayList<String> mid = new ArrayList<>();
    private ArrayList<String> mstatus = new ArrayList<>();
    private ActualPatient_RecyclerView adapter;
    private RecyclerView recyclerView;
    private MainActivity main = new MainActivity();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        adapter = new ActualPatient_RecyclerView(main.get());
        View rootView = inflater.inflate(R.layout.fragment_actual__patient, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_mypat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }


}
