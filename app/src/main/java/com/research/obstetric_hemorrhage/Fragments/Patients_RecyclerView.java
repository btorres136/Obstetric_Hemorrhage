package com.research.obstetric_hemorrhage.Fragments;


import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.research.obstetric_hemorrhage.R;


public class Patients_RecyclerView extends RecyclerView.ViewHolder{
    private TextView textView_Patient;
    private TextView textView_id;
    private TextView textView_status;
    private TextView textView_Age;
    private TextView textView_room;
    private CardView Addtomypat;
    private CardView See_info;
    private CardView Update_Info;

    public Patients_RecyclerView(@NonNull View itemView) {
        super(itemView);
        textView_Patient = itemView.findViewById(R.id.patient_name);
        textView_id = itemView.findViewById(R.id.patient_id);
        textView_Age = itemView.findViewById(R.id.patient_age);
        textView_status =itemView.findViewById(R.id.patient_state);
        textView_room = itemView.findViewById(R.id.room);
        Addtomypat = itemView.findViewById(R.id.add_mypat);
        See_info = itemView.findViewById(R.id.see_info);
        Update_Info = itemView.findViewById(R.id.update_infoCV);
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

    public CardView getAddtomypat() {
        return Addtomypat;
    }

    public CardView getSee_info() { return See_info;  }

    public CardView getUpdate_Info() { return Update_Info; }
}
