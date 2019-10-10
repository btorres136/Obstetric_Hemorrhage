package com.research.obstetric_hemorrhage;

public class PointValue_sys {
    private String Time;
    private String Data;
    private String Added_by;

    public PointValue_sys(){
    }

    public PointValue_sys(String mData, String mTime, String madded_by){
        this.Time = mTime;
        this.Data = mData;
        this.Added_by = madded_by;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getAdded_by() {
        return Added_by;
    }

    public void setAdded_by(String added_by) {
        Added_by = added_by;
    }
}
