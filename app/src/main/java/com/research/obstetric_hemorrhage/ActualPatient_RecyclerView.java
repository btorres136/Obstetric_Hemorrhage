package com.research.obstetric_hemorrhage;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

public class ActualPatient_RecyclerView extends RecyclerView.Adapter<ActualPatient_RecyclerView.ViewHolder>{

    private ArrayList<Patient_Medical> My_Patients_Array;
    private ArrayList<Systolic_Pressure> my_SystolicPressureArray;
    private Systolic_Pressure systolicPressure = new Systolic_Pressure();
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions(0);

    public ActualPatient_RecyclerView(ArrayList<Patient_Medical> My_Patients, ArrayList<Systolic_Pressure> my_Patients_Graphs){
        My_Patients_Array = new ArrayList<>(My_Patients);
        my_SystolicPressureArray = new ArrayList<>(my_Patients_Graphs);
        Log.v("hola",""+my_SystolicPressureArray.size());
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actualpatient_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //holder.setIsRecyclable(true);


        holder.textView_Patient.setText("Patient: " + My_Patients_Array.get(position).getName());
        holder.textView_Age.setText("Age: " + My_Patients_Array.get(position).getAge());
        holder.textView_id.setText("ID: " + My_Patients_Array.get(position).getId());
        holder.textView_status.setText("Status: " + My_Patients_Array.get(position).getStatus());
        holder.textView_room.setText("Room: " + My_Patients_Array.get(position).getRoom());
        holder.linegraph_pressure.addSeries(my_SystolicPressureArray.get(position).getDatapoint_sis());
        holder.cardview_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseTransactions.submitgraphinfo(holder.systolic.getText().toString(), holder.diastolic.getText().toString(),My_Patients_Array.get(position).getId());
            }
        });
        //holder.linegraph_pressure.addSeries(my_SystolicPressureArray.get(position).getDatapoint_dias());


    }

    @Override
    public int getItemCount() {
        return My_Patients_Array.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        GraphView linegraph_pressure;
        TextView textView_Patient;
        TextView textView_id;
        TextView textView_status;
        TextView textView_Age;
        TextView textView_room;
        CardView cardview_submit;
        EditText systolic;
        EditText diastolic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_Patient = itemView.findViewById(R.id.patient_name_actual);
            textView_id = itemView.findViewById(R.id.patient_id_actual);
            textView_Age = itemView.findViewById(R.id.patient_age_actual);
            textView_status =itemView.findViewById(R.id.patient_state_actual);
            textView_room = itemView.findViewById(R.id.patient_room_actual);
            linegraph_pressure = itemView.findViewById(R.id.pressure);
            cardview_submit = itemView.findViewById(R.id.update_infoCV);
            systolic = itemView.findViewById(R.id.input_syspres);
            diastolic = itemView.findViewById(R.id.input_diaspres);
        }
    }
}