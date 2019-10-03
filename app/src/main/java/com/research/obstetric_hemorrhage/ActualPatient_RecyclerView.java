package com.research.obstetric_hemorrhage;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;

public class ActualPatient_RecyclerView extends RecyclerView.Adapter<ActualPatient_RecyclerView.ViewHolder>{
    private ArrayList<String> mPatName = new ArrayList<>();
    private ArrayList<String> mAgepat = new ArrayList<>();
    private ArrayList<String> midpat = new ArrayList<>();
    private ArrayList<String> mstatuspat =  new ArrayList<>();
    private ArrayList<String> mroompat = new ArrayList<>();
    private ArrayList<Integer> mSisPresion = new ArrayList<>();
    private ArrayList<Integer> mDiasPresion = new ArrayList<>();
    private ArrayList<Integer> mBloodLost = new ArrayList<>();
    private ArrayList<Integer> mIngLiquid = new ArrayList<>();
    private ArrayList<Integer> mEgreLiquid = new ArrayList<>();
    private ArrayList<Integer> mPulse = new ArrayList<>();
    private ArrayList<Integer> mSaturation = new ArrayList<>();
    private ArrayList<Integer> mShockIndex = new ArrayList<>();
    private LineGraphSeries<DataPoint> graph_sis;
    private LineGraphSeries<DataPoint> graph_dias;
    private ArrayList<Patient_Medical> Pat_graph_info = new ArrayList<>();
    //private Patient patient = new Patient();




    public ActualPatient_RecyclerView(ArrayList<String> PatientName, ArrayList<String> Age,
                                      ArrayList<String> id, ArrayList<String> status, ArrayList<String> room, ArrayList<Patient_Medical> Pat_graph){
        mPatName = PatientName;
        mAgepat = Age;
        midpat = id;
        mstatuspat = status;
        mroompat=room;
        Pat_graph_info = Pat_graph;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actualpatient_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.textView_Patient.setText("Patient: " + mPatName.get(position));
        holder.textView_Age.setText("Age: " + mAgepat.get(position));
        holder.textView_id.setText("ID: " + midpat.get(position));
        holder.textView_status.setText("Status: " + mstatuspat.get(position));
        holder.textView_room.setText("Room: " + mroompat.get(position));


        //holder.linegraph_pressure.addSeries(Pat_graph_info.get(position).getDatapoint_dias());
        //holder.linegraph_pressure.addSeries(Pat_graph_info.get(position).getDatapoint_sis());
        /*holder.linegraph_sis.addSeries(lineSeries);
        holder.linegraph_sis.addSeries(lineSeries2);
        holder.linegraph_sis.setTitle("Systolic Pressure");
        lineSeries.setColor(Color.GREEN);
        lineSeries2.setColor(Color.RED);

        lineSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(holder.itemView.getContext(), "Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mPatName.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        GraphView linegraph_pressure;
        TextView textView_Patient;
        TextView textView_id;
        TextView textView_status;
        TextView textView_Age;
        TextView textView_room;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView_Patient = itemView.findViewById(R.id.patient_name_actual);
            textView_id = itemView.findViewById(R.id.patient_id_actual);
            textView_Age = itemView.findViewById(R.id.patient_age_actual);
            textView_status =itemView.findViewById(R.id.patient_state_actual);
            textView_room=itemView.findViewById(R.id.patient_room_actual);
            linegraph_pressure = itemView.findViewById(R.id.graph_presion);

        }
    }
}