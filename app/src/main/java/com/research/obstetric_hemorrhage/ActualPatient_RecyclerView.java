package com.research.obstetric_hemorrhage;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;


public class ActualPatient_RecyclerView extends RecyclerView.ViewHolder{
    private TextView textView_Patient;
    private TextView textView_id;
    private TextView textView_status;
    private TextView textView_Age;
    private TextView textView_room;
    private CardView cardView_erase;


    public ActualPatient_RecyclerView(@NonNull View itemView) {
        super(itemView);
        textView_Patient = itemView.findViewById(R.id.patient_name_actual);
        textView_id = itemView.findViewById(R.id.patient_id_actual);
        textView_Age = itemView.findViewById(R.id.patient_age_actual);
        textView_status =itemView.findViewById(R.id.patient_state_actual);
        textView_room = itemView.findViewById(R.id.patient_room_actual);
        cardView_erase = itemView.findViewById(R.id.erase_my_patientCV);
    }

    public TextView getTextView_Patient() {
        return textView_Patient;
    }

    public void setTextView_Patient(TextView textView_Patient) {
        this.textView_Patient = textView_Patient;
    }

    public TextView getTextView_id() {
        return textView_id;
    }

    public void setTextView_id(TextView textView_id) {
        this.textView_id = textView_id;
    }

    public TextView getTextView_status() {
        return textView_status;
    }

    public void setTextView_status(TextView textView_status) {
        this.textView_status = textView_status;
    }

    public TextView getTextView_Age() {
        return textView_Age;
    }

    public void setTextView_Age(TextView textView_Age) {
        this.textView_Age = textView_Age;
    }

    public TextView getTextView_room() {
        return textView_room;
    }

    public void setTextView_room(TextView textView_room) {
        this.textView_room = textView_room;
    }

    public CardView getCardView_erase() {
        return cardView_erase;
    }
}