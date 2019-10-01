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

import static com.research.obstetric_hemorrhage.MainActivity.wait;

public class Patient_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //vars
    private static ArrayList<String> mPatientNames = new ArrayList<>();
    private static ArrayList<String> mAges =  new ArrayList<>();
    private static ArrayList<String> mid = new ArrayList<>();
    private static ArrayList<String> mstatus = new ArrayList<>();
    private static ArrayList<String> mroom = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private MainActivity main = new MainActivity();
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

        main.get_pat();
        waittime();
        return rootView;
    }

    public void waittime(){
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
    }

    public static void getallpat(ArrayList<String> name, ArrayList<String> age, ArrayList<String> id, ArrayList<String> status, ArrayList<String> room){
        mPatientNames=name;
        mAges=age;
        mid=id;
        mstatus=status;
        mroom = room;
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);

        main.get_pat();
        Patients_RecyclerView adapter = new Patients_RecyclerView(mPatientNames,mAges,mid,mstatus,mroom);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        swipe.setRefreshing(false);

    }
}
