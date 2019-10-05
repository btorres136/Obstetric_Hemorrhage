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
    private String name;
    private int age;
    private String id;
    private String Room;
    private int Status;

    Patient_Medical(){
        Systolic = new ArrayList<>();
        Diastolic = new ArrayList<>();
        datapoint_dias = new LineGraphSeries<>();
        datapoint_sis = new LineGraphSeries<>();
        name="";
        age=0;
        id="";
        Room = "";
        Status=0;
    }
    Patient_Medical(String rname, String rid, String rroom, int rage, int rstatus){
        name=rname;
        age=rage;
        id=rid;
        Status=rstatus;
        Room=rroom;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getRoom(){
        return Room;
    }
    public int getAge(){
        return age;
    }
    public int getStatus(){
        return Status;
    }

    public void setName(String rname){
        name=rname;
    }

    public void setAge(int rage){
        age=rage;
    }

    public void setId(String rid){
        id=rid;
    }

    public void setRoom(String rroom){
        Room=rroom;
    }

    public void setStatus(int rstatus){
        Status=rstatus;
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
