package com.research.obstetric_hemorrhage;

public class Patient_Medical {

    private String name;
    private int age;
    private String id;
    private String Room;
    private int Status;

    Patient_Medical(){
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






}
