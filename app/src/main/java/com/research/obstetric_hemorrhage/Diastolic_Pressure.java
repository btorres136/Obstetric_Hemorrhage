package com.research.obstetric_hemorrhage;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Diastolic_Pressure {
    private ArrayList<String> X_Diastolic;
    private ArrayList<String> Y_Diastolic;
    private LineGraphSeries<DataPoint> datapoint_dias;

    public Diastolic_Pressure(){
        X_Diastolic = new ArrayList<>();
        Y_Diastolic = new ArrayList<>();
        datapoint_dias = new LineGraphSeries<>();
    }

    public Diastolic_Pressure(ArrayList<String> xdiastolic, ArrayList<String> ydiastolic){
        X_Diastolic = xdiastolic;
        Y_Diastolic = ydiastolic;
    }
    public Diastolic_Pressure(Diastolic_Pressure copy){
        X_Diastolic = new ArrayList<>();
        Y_Diastolic = new ArrayList<>();
        datapoint_dias = new LineGraphSeries<>();
        X_Diastolic = copy.getXDiastolic();
        Y_Diastolic = copy.getYDiastolic();

    }

    public LineGraphSeries<DataPoint> getDatapoint_dias(){
        DataPoint[] point = new DataPoint[X_Diastolic.size()];
        for(int i=0; i < point.length; i++){
            point[i] = new DataPoint(Integer.parseInt(X_Diastolic.get(i)),Integer.parseInt(Y_Diastolic.get(i)));
        }
        datapoint_dias = new LineGraphSeries<>(point);
        return datapoint_dias;
    }

    public ArrayList<String> getXDiastolic(){
        return X_Diastolic;
    }

    public ArrayList<String> getYDiastolic(){
        return Y_Diastolic;
    }

    public void setX_Diastolic(ArrayList<String> dias){
        X_Diastolic = dias;
    }
    public void setY_Diastolic(ArrayList<String> dias){
        Y_Diastolic = dias;
    }
}
