package com.research.obstetric_hemorrhage;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Patient_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //vars
    private static ArrayList<String> mPatientNames = new ArrayList<>();
    private static ArrayList<String> mAges =  new ArrayList<>();
    private static ArrayList<String> mid = new ArrayList<>();
    private static ArrayList<String> mstatus = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private MainActivity main = new MainActivity();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_, container, false);
        recyclerView = rootView.findViewById(R.id.patient_recycle);

        swipe = rootView.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);
        main.get_pat();
        Patients_RecyclerView adapter = new Patients_RecyclerView(mPatientNames,mAges,mid,mstatus);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    public static void getallpat(ArrayList<String> name, ArrayList<String> age, ArrayList<String> id, ArrayList<String> status){
        mPatientNames=name;
        mAges=age;
        mid=id;
        mstatus=status;
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);

        main.get_pat();
        Patients_RecyclerView adapter = new Patients_RecyclerView(mPatientNames,mAges,mid,mstatus);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        swipe.setRefreshing(false);
    }
}
