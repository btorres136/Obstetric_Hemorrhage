package com.research.obstetric_hemorrhage;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Systolic_Pressure {
    private ArrayList<String> X_Systolic;
    private ArrayList<String> Y_Systolic;
    private LineGraphSeries<DataPoint> datapoint_sis;

    public Systolic_Pressure(){
        X_Systolic = new ArrayList<>();
        Y_Systolic = new ArrayList<>();
        datapoint_sis = new LineGraphSeries<>();
    }

    public Systolic_Pressure(ArrayList<String> xsystolic, ArrayList<String> ysystolic){
        X_Systolic = xsystolic;
        Y_Systolic = ysystolic;
    }
    public Systolic_Pressure(Systolic_Pressure copy){
        X_Systolic = new ArrayList<>();
        Y_Systolic = new ArrayList<>();
        datapoint_sis = new LineGraphSeries<>();
        X_Systolic = copy.getXSystolic();
        Y_Systolic = copy.getYSystolic();

    }

    public LineGraphSeries<DataPoint> getDatapoint_sis(){
        DataPoint[] point = new DataPoint[X_Systolic.size()];
        for(int i=0; i < point.length; i++){
            point[i] = new DataPoint(Integer.parseInt(X_Systolic.get(i)),Integer.parseInt(Y_Systolic.get(i)));
        }
        datapoint_sis = new LineGraphSeries<>(point);
        return datapoint_sis;
    }

    public ArrayList<String> getXSystolic(){
        return X_Systolic;
    }

    public ArrayList<String> getYSystolic(){
        return Y_Systolic;
    }

    public void setXSystolic(ArrayList<String> sis){
        X_Systolic = sis;
    }
    public void setYSystolic(ArrayList<String> sis){
        Y_Systolic = sis;
    }
}
