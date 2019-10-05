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
    /*private static ArrayList<String> mPatientNames = new ArrayList<>();
    private static ArrayList<String> mAges =  new ArrayList<>();
    private static ArrayList<String> mid = new ArrayList<>();
    private static ArrayList<String> mstatus = new ArrayList<>();
    private static ArrayList<String> mroom = new ArrayList<>();
    private static LineGraphSeries<DataPoint> systolic;
    private static LineGraphSeries<DataPoint> distolic;*/
    private ActualPatient_RecyclerView adapter;
    private RecyclerView recyclerView;
    private ArrayList<Patient_Medical> My_Patients_Array;
    //private static MainActivity main = new MainActivity();
    //private static ArrayList<Patient_Medical> Pat_graph_info = new ArrayList<>();
    private SwipeRefreshLayout swipe;
    private View rootView;

    Actual_Patient(){
        My_Patients_Array = new ArrayList<>();
    }

    public void setMy_Patients_Array(ArrayList<Patient_Medical> Patients_Array){
        My_Patients_Array = Patients_Array;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //main.get();
        //getgraph();
        rootView = inflater.inflate(R.layout.fragment_actual__patient, container, false);

        swipe = rootView.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);
        adapter = new ActualPatient_RecyclerView(My_Patients_Array);
        recyclerView = rootView.findViewById(R.id.recycler_mypat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    /*public static void getList(ArrayList<String> name, ArrayList<String> age, ArrayList<String> id, ArrayList<String> status, ArrayList<String> room){
        mPatientNames=name;
        mAges=age;
        mid=id;
        mstatus=status;
        mroom=room;
    }*/

    /*public static void getgraph(){
        for(int i=0; i<mid.size();i++)
        {
            main.getgraphdata(mid.get(i));
        }
    }
    public static void setgraphsinfo(Patient_Medical pat_info){
        Pat_graph_info.add(pat_info);
    }*/

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        //main.get();
        //getgraph();
        adapter = new ActualPatient_RecyclerView(My_Patients_Array);
        recyclerView = rootView.findViewById(R.id.recycler_mypat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe.setRefreshing(false);
    }
}
