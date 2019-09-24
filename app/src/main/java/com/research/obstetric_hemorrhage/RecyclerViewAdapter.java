package com.research.obstetric_hemorrhage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "com.research.obstetric_hemorrhage.RecyclerViewAdapter";
    private ArrayList<String> mPatientNames = new ArrayList<>();
    private ArrayList<String> mAges =  new ArrayList<>();
    private ArrayList<String> mid = new ArrayList<>();
    private ArrayList<String> mstatus = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> PatientNames, ArrayList<String> Ages, ArrayList<String> id, ArrayList<String> status, Context context){
        mPatientNames= PatientNames;
        mAges = Ages;
        mid = id;
        mstatus = status;
        mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patientlist_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_Patient.setText("Patient: " + mPatientNames.get(position));
        holder.textView_Age.setText("Age: " + mAges.get(position));
        holder.textView_id.setText("ID: " + mid.get(position));
        holder.textView_status.setText("Status: " + mstatus.get(position));
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
        public ViewHolder(View itemView)
        {
            super(itemView);
            textView_Patient = itemView.findViewById(R.id.patient_name);
            textView_Age = itemView.findViewById(R.id.patient_age);
            textView_id = itemView.findViewById(R.id.patient_id);
            textView_status = itemView.findViewById(R.id.patient_state);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
