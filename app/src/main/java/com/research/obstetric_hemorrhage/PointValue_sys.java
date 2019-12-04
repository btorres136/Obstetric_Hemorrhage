package com.research.obstetric_hemorrhage;

public class PointValue_sys {
    private String Date_added;
    private String Time_added;
    private String Data;
    private String Added_by;

    public PointValue_sys(){
    }

    public PointValue_sys(String mData, String mTime, String madded_by, String rtime){
        this.Date_added = mTime;
        this.Data = mData;
        this.Added_by = madded_by;
        this.Time_added = rtime;
    }

    public String getDate_added() {
        return Date_added;
    }

    public void setDate_added(String date_added) {
        Date_added = date_added;
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
    public String getTime_added() {
        return Time_added;
    }

    public void setTime_added(String time_added) {
        Time_added = time_added;
    }
}
