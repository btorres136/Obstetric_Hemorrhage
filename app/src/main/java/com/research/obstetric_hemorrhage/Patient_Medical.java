package com.research.obstetric_hemorrhage;

import android.provider.ContactsContract;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Patient_Medical {
    private ArrayList<String> Systolic;
    private ArrayList<String> Diastolic;
    private LineGraphSeries<DataPoint> datapoint_sis;
    private LineGraphSeries<DataPoint> datapoint_dias;

    Patient_Medical(){
        Systolic = new ArrayList<>();
        Diastolic = new ArrayList<>();
    }

    public ArrayList<String> getSystolic(){
        return Systolic;
    }
    public ArrayList<String> getDiastolic(){
        return Diastolic;
    }
    public void setSystolic(ArrayList<String> sis){
        Systolic = sis;
    }
    public void setDiastolic(ArrayList<String> dias){
        Diastolic = dias;
    }
    public LineGraphSeries<DataPoint> getDatapoint_sis(){
        DataPoint[] point = new DataPoint[Systolic.size()];
        for(int i=0; i<Systolic.size(); i++){
            point[i]=new DataPoint(i,Integer.parseInt(Systolic.get(i)));
        }
        datapoint_sis = new LineGraphSeries<>(point);
        return datapoint_sis;
    }
    public LineGraphSeries<DataPoint> getDatapoint_dias(){
        DataPoint[] point = new DataPoint[Diastolic.size()];
        for(int i=0; i<Diastolic.size(); i++){
            point[i]=new DataPoint(i,Integer.parseInt(Diastolic.get(i)));
        }
        datapoint_dias = new LineGraphSeries<>(point);
        return datapoint_dias;
    }

}
