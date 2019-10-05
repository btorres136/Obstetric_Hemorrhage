package com.research.obstetric_hemorrhage;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;



public class Patient_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private ArrayList<Patient_Medical> All_Patients_Array;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    Patients_RecyclerView adapter;

    private ProgressBar bar;

    Patient_Fragment(){
        All_Patients_Array = new ArrayList<>();
        adapter = new Patients_RecyclerView();
    }

    public void setAll_Patients_Array(ArrayList<Patient_Medical> All_Patients){
        All_Patients_Array = All_Patients;
        adapter = new Patients_RecyclerView(All_Patients_Array);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public ArrayList<Patient_Medical> getAll_Patients_Array(){
        return All_Patients_Array;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_, container, false);
        recyclerView = rootView.findViewById(R.id.patient_recycle);
        swipe = rootView.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);
        bar=rootView.findViewById(R.id.waittime);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bar.setVisibility(View.GONE);

        //waittime();
        return rootView;
    }

    /*public void waittime(){
        if(wait == true){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Patients_RecyclerView adapter = new Patients_RecyclerView(mPatientNames,mAges,mid,mstatus,mroom);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    bar.setVisibility(View.GONE);
                    if(adapter.getItemCount() == 0){
                        Log.v("Slow internet", "Refresh");
                    }
                }}, 2000);
            wait=false;

        }
        else{
            Patients_RecyclerView adapter = new Patients_RecyclerView(mPatientNames,mAges,mid,mstatus,mroom);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            bar.setVisibility(View.GONE);
            wait=false;
        }
    }*/


    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        Reload();
        swipe.setRefreshing(false);
    }

    public void Reload(){
        DatabaseTransactions databaseTransactions = new DatabaseTransactions();
        ArrayList<Patient_Medical> data = new ArrayList<>();
        /*data = databaseTransactions.getnewdata();
        if(All_Patients_Array.size() < data.size()){
            Log.v("Siiiii","kkkkkkkk");
            All_Patients_Array = databaseTransactions.getnewdata();
        }*/
        Patients_RecyclerView adapter = new Patients_RecyclerView(All_Patients_Array);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}
