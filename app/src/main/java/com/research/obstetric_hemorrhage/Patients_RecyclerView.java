package com.research.obstetric_hemorrhage;

import android.content.Intent;
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

    private ArrayList<Patient_Medical> All_Patients_Array;
    private Patient_Medical Patient_Selected;
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions(0);

    Patients_RecyclerView(){
        All_Patients_Array= new ArrayList<>();
        Patient_Selected = new Patient_Medical();
    }



    Patients_RecyclerView(ArrayList<Patient_Medical> All_Patients){
        All_Patients_Array= new ArrayList<>(All_Patients);
        notifyDataSetChanged();
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
        holder.textView_Patient.setText("Patient: " + All_Patients_Array.get(position).getName());
        holder.textView_Age.setText("Age: " + All_Patients_Array.get(position).getAge());
        holder.textView_id.setText("ID: " + All_Patients_Array.get(position).getId());
        holder.textView_status.setText("Status: " + All_Patients_Array.get(position).getStatus());
        holder.textView_room.setText("Room: " + All_Patients_Array.get(position).getRoom());
        holder.cardView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseTransactions.add(All_Patients_Array.get(position).getName(),All_Patients_Array.get(position).getAge(), All_Patients_Array.get(position).getStatus(),
                        All_Patients_Array.get(position).getRoom(),All_Patients_Array.get(position).getId());
            }
        });
    }




    @Override
    public int getItemCount() {
        return All_Patients_Array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_Patient;
        TextView textView_Age;
        TextView textView_id;
        TextView textView_status;
        TextView textView_room;
        RelativeLayout parentLayout;
        CardView cardView_add;
        CardView see_info;
        public ViewHolder(View itemView)
        {
            super(itemView);
            cardView_add = itemView.findViewById(R.id.add_mypat);
            textView_Patient = itemView.findViewById(R.id.patient_name);
            textView_Age = itemView.findViewById(R.id.patient_age);
            textView_id = itemView.findViewById(R.id.patient_id);
            textView_room = itemView.findViewById(R.id.room);
            textView_status = itemView.findViewById(R.id.patient_state);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            cardView_add = itemView.findViewById(R.id.add_mypat);
            see_info = itemView.findViewById(R.id.see_info);
        }

    }
}
