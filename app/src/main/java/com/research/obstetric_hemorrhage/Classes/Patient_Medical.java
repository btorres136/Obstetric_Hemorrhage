package com.research.obstetric_hemorrhage.Classes;

public class Patient_Medical {

    private String Name;
    private int Age;
    private String ID;
    private String Room;
    private int Stage;
    private String Added_by;

    Patient_Medical(){
    }

    Patient_Medical(String rname, String rid, String rroom, int rage, int rstage, String raddedby){
        Name=rname;
        Age=rage;
        ID=rid;
        Stage=rstage;
        Room=rroom;
        Added_by = raddedby;
    }

    public String getName(){
        return Name;
    }

    public String getID(){
        return ID;
    }

    public String getRoom(){
        return Room;
    }
    public int getAge(){
        return Age;
    }
    public int getStage(){
        return Stage;
    }
    public String getAddedby(){
        return Added_by;
    }

    public void setAddedby(String raddedby){
        Added_by = raddedby;
    }

    public void setName(String rname){
        Name=rname;
    }

    public void setAge(int rage){
        Age=rage;
    }

    public void setID(String rid){
        ID=rid;
    }

    public void setRoom(String rroom){
        Room=rroom;
    }

    public void setStage(int rstage){
        Stage=rstage;
    }






}
