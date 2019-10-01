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
import android.widget.ProgressBar;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


public class Actual_Patient extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static ArrayList<String> mPatientNames = new ArrayList<>();
    private static ArrayList<String> mAges =  new ArrayList<>();
    private static ArrayList<String> mid = new ArrayList<>();
    private static ArrayList<String> mstatus = new ArrayList<>();
    private static ArrayList<String> mroom = new ArrayList<>();
    private ActualPatient_RecyclerView adapter;
    private RecyclerView recyclerView;
    private MainActivity main = new MainActivity();
    private SwipeRefreshLayout swipe;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main.get();
        rootView = inflater.inflate(R.layout.fragment_actual__patient, container, false);

        swipe = rootView.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);
        adapter = new ActualPatient_RecyclerView(mPatientNames, mAges, mid, mstatus, mroom);
        recyclerView = rootView.findViewById(R.id.recycler_mypat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    public static void getList(ArrayList<String> name, ArrayList<String> age, ArrayList<String> id, ArrayList<String> status, ArrayList<String> room){
            mPatientNames=name;
            mAges=age;
            mid=id;
            mstatus=status;
            mroom=room;
    }


    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        adapter = new ActualPatient_RecyclerView(mPatientNames, mAges, mid, mstatus, mroom);
        recyclerView = rootView.findViewById(R.id.recycler_mypat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe.setRefreshing(false);
    }
}
