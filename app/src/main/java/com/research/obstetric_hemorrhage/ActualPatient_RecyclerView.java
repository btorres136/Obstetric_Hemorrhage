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
    private ArrayList<Integer> mSisPresion = new ArrayList<>();
    private ArrayList<Integer> mDiasPresion = new ArrayList<>();
    private ArrayList<Integer> mBloodLost = new ArrayList<>();
    private ArrayList<Integer> mIngLiquid = new ArrayList<>();
    private ArrayList<Integer> mEgreLiquid = new ArrayList<>();
    private ArrayList<Integer> mPulse = new ArrayList<>();
    private ArrayList<Integer> mSaturation = new ArrayList<>();
    private ArrayList<Integer> mShockIndex = new ArrayList<>();
    //private Patient patient = new Patient();



    public ActualPatient_RecyclerView(ArrayList<String> PatientName, ArrayList<String> Age,
                                      ArrayList<String> id, ArrayList<String> status){
        mPatName = PatientName;
        mAgepat = Age;
        midpat = id;
        mstatuspat = status;
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

        LineGraphSeries<DataPoint> lineSeries = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        LineGraphSeries<DataPoint> lineSeries2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 6),
                new DataPoint(2, 9),
                new DataPoint(3, 6),
                new DataPoint(4, 8)
        });
        holder.linegraph_sis.addSeries(lineSeries);
        holder.linegraph_sis.addSeries(lineSeries2);
        holder.linegraph_sis.setTitle("Systolic Pressure");
        lineSeries.setColor(Color.GREEN);
        lineSeries2.setColor(Color.RED);

        lineSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(holder.itemView.getContext(), "Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPatName.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        GraphView linegraph_sis;
        TextView textView_Patient;
        TextView textView_id;
        TextView textView_status;
        TextView textView_Age;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView_Patient = itemView.findViewById(R.id.patient_name_actual);
            textView_id = itemView.findViewById(R.id.patient_id_actual);
            textView_Age = itemView.findViewById(R.id.patient_age_actual);
            textView_status =itemView.findViewById(R.id.patient_state_actual);
            linegraph_sis = itemView.findViewById(R.id.graph_presion);

        }
    }
}
