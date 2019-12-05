package com.research.obstetric_hemorrhage.Classes;

public class Patient_Info {
    private String Added_by;
    private String Blood_loss;
    private String Date_added;
    private String Diastolic_Pressure;
    private String Heart_Rate;
    private String Mental;
    private String Perfusion;
    private String Shock_Index;
    private String Systolic_Presure;
    private String Time_added;

    public Patient_Info(String added_by, String blood_loss, String date_added, String diastolic_Pressure,
                        String heart_Rate, String mental, String perfusion, String shock_Index,
                        String systolic_Presure, String time_added) {
        Added_by = added_by;
        Blood_loss = blood_loss;
        Date_added = date_added;
        Diastolic_Pressure = diastolic_Pressure;
        Heart_Rate = heart_Rate;
        Mental = mental;
        Perfusion = perfusion;
        Shock_Index = shock_Index;
        Systolic_Presure = systolic_Presure;
        Time_added = time_added;
    }


    Patient_Info(){}

    public String getTime_added() {
        return Time_added;
    }

    public void setTime_added(String time_added) {
        Time_added = time_added;
    }

    public String getAdded_by() {
        return Added_by;
    }

    public void setAdded_by(String added_by) {
        Added_by = added_by;
    }

    public String getBlood_loss() {
        return Blood_loss;
    }

    public void setBlood_loss(String blood_loss) {
        Blood_loss = blood_loss;
    }

    public String getDate_added() {
        return Date_added;
    }

    public void setDate_added(String date_added) {
        Date_added = date_added;
    }

    public String getDiastolic_Pressure() {
        return Diastolic_Pressure;
    }

    public void setDiastolic_Pressure(String diastolic_Pressure) {
        Diastolic_Pressure = diastolic_Pressure;
    }

    public String getHeart_Rate() {
        return Heart_Rate;
    }

    public void setHeart_Rate(String heart_Rate) {
        Heart_Rate = heart_Rate;
    }

    public String getMental() {
        return Mental;
    }

    public void setMental(String mental) {
        Mental = mental;
    }

    public String getPerfusion() {
        return Perfusion;
    }

    public void setPerfusion(String perfusion) {
        Perfusion = perfusion;
    }

    public String getShock_Index() {
        return Shock_Index;
    }

    public void setShock_Index(String shock_Index) {
        Shock_Index = shock_Index;
    }

    public String getSystolic_Presure() {
        return Systolic_Presure;
    }

    public void setSystolic_Presure(String systolic_Presure) {
        Systolic_Presure = systolic_Presure;
    }
}
