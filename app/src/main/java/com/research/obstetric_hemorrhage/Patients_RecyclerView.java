package com.research.obstetric_hemorrhage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Patients_RecyclerView extends RecyclerView.Adapter<Patients_RecyclerView.ViewHolder>{

    private ArrayList<String> mPatientNames = new ArrayList<>();
    private ArrayList<Integer> mAges =  new ArrayList<>();
    private ArrayList<String> mid = new ArrayList<>();
    private ArrayList<String> mstatus = new ArrayList<>();
    private int mPosition;
    private Actual_Patient actual = new Actual_Patient();

    public Patients_RecyclerView(ArrayList<String> PatientNames, ArrayList<Integer> Ages, ArrayList<String> id, ArrayList<String> status){
        mPatientNames= PatientNames;
        mAges = Ages;
        mid = id;
        mstatus = status;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patientlist_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView_Patient.setText("Patient: " + mPatientNames.get(position));
        holder.textView_Age.setText("Age: " + mAges.get(position));
        holder.textView_id.setText("ID: " + mid.get(position));
        holder.textView_status.setText("Status: " + mstatus.get(position));
        mPosition=position;

        holder.cardView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actual.add(mPatientNames.get(mPosition), mAges.get(mPosition), mid.get(mPosition), mstatus.get(mPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPatientNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_Patient;
        TextView textView_Age;
        TextView textView_id;
        TextView textView_status;
        RelativeLayout parentLayout;
        CardView cardView_add;
        public ViewHolder(View itemView)
        {
            super(itemView);
            cardView_add = itemView.findViewById(R.id.add_mypat);
            textView_Patient = itemView.findViewById(R.id.patient_name);
            textView_Age = itemView.findViewById(R.id.patient_age);
            textView_id = itemView.findViewById(R.id.patient_id);
            textView_status = itemView.findViewById(R.id.patient_state);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
