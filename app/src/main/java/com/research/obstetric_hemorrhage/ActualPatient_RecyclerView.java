package com.research.obstetric_hemorrhage;


import android.app.Activity;
import android.content.Context;
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

import java.util.ArrayList;

public class ActualPatient_RecyclerView extends RecyclerView.Adapter<ActualPatient_RecyclerView.ViewHolder>{
    private ArrayList<String> mPatName = new ArrayList<>();
    private ArrayList<Integer> mAge = new ArrayList<>();
    private ArrayList<Integer> mSisPresion = new ArrayList<>();
    private ArrayList<Integer> mDiasPresion = new ArrayList<>();
    private ArrayList<Integer> mBloodLost = new ArrayList<>();
    private ArrayList<Integer> mIngLiquid = new ArrayList<>();
    private ArrayList<Integer> mEgreLiquid = new ArrayList<>();
    private ArrayList<Integer> mPulse = new ArrayList<>();
    private ArrayList<Integer> mSaturation = new ArrayList<>();
    private ArrayList<Integer> mShockIndex = new ArrayList<>();
    //private Patient patient = new Patient();



    public ActualPatient_RecyclerView(ArrayList<String> PatientName){
        mPatName = PatientName;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actualpatient_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView_Patient.setText("Patient: " + mPatName.get(position));
    }

    @Override
    public int getItemCount() {
        return mPatName.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_Patient;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Patient = itemView.findViewById(R.id.patient_name_actual);
        }
    }
}
