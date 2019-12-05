package com.research.obstetric_hemorrhage.Fragments;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.research.obstetric_hemorrhage.R;

public class mypatient_info_recyclerview extends RecyclerView.ViewHolder {

    private TextView date;
    private TextView hour;
    private TextView sis_press;
    private TextView ebl;
    private TextView shock;
    private TextView perfusion;
    private TextView sensorio;
    private TextView dis_press;
    private TextView Mental;
    private TextView Heart_Rate;


    public mypatient_info_recyclerview(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        hour = itemView.findViewById(R.id.hour);
        sis_press = itemView.findViewById(R.id.sis_press);
        ebl = itemView.findViewById(R.id.ebl);
        shock = itemView.findViewById(R.id.shock);
        perfusion = itemView.findViewById(R.id.perfusion);
        dis_press = itemView.findViewById(R.id.dis_press);
        Mental = itemView.findViewById(R.id.mental);
        Heart_Rate = itemView.findViewById(R.id.hr);
    }
    public TextView getHeart_Rate() {
        return Heart_Rate;
    }

    public void setHeart_Rate(TextView heart_Rate) {
        Heart_Rate = heart_Rate;
    }

    public TextView getMental() {
        return Mental;
    }

    public void setMental(TextView mental) {
        Mental = mental;
    }
    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public TextView getHour() {
        return hour;
    }

    public void setHour(TextView hour) {
        this.hour = hour;
    }

    public TextView getSis_press() {
        return sis_press;
    }

    public void setSis_press(TextView sis_press) {
        this.sis_press = sis_press;
    }

    public TextView getEbl() {
        return ebl;
    }

    public void setEbl(TextView ebl) {
        this.ebl = ebl;
    }

    public TextView getShock() {
        return shock;
    }

    public void setShock(TextView shock) {
        this.shock = shock;
    }

    public TextView getPerfusion() {
        return perfusion;
    }

    public void setPerfusion(TextView perfusion) {
        this.perfusion = perfusion;
    }

    public TextView getSensorio() {
        return sensorio;
    }

    public void setSensorio(TextView sensorio) {
        this.sensorio = sensorio;
    }

    public TextView getDis_press() {
        return dis_press;
    }

    public void setDis_press(TextView dis_press) {
        this.dis_press = dis_press;
    }

}
