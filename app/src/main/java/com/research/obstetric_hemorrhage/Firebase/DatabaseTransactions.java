package com.research.obstetric_hemorrhage.Firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class DatabaseTransactions {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Query ListenToDatabaseOnAllPatients(){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("/Patients")
                .limitToLast(50);
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        query.addChildEventListener(childEventListener);
        return query;
    }

    public Query ListenToDatabaseOnMyPatients(){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("/User_Patients/"+mAuth.getUid())
                .limitToLast(50);
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addChildEventListener(childEventListener);
        return query;
    }



    public void add(String Pat_Name, int Age, int mStatus, String room, String key){
        DatabaseReference myRef = database.getReference("/User_Patients/"+mAuth.getUid()+"/");
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("Name", Pat_Name);
        usermap.put("Age", Age);
        usermap.put("Status", mStatus);
        usermap.put("Room", room);
        usermap.put("ID", key);
        myRef.child(key).setValue(usermap);
    }

    public void addtopat(String Pat_Name, String Age, String mStatus, String room){
        DatabaseReference myRef = database.getReference("/Patients/");
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("Name", Pat_Name);
        usermap.put("Age", Integer.parseInt(Age));
        usermap.put("Room",room);
        usermap.put("Stage", Integer.parseInt(mStatus));
        String key = myRef.push().getKey();
        usermap.put("ID",key);
        usermap.put("Added_by", mAuth.getUid());
        myRef.child(key).setValue(usermap);
        /*Map<String, String> pressure = new HashMap<>();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String timeformated = timeFormat.format(date);
        String dateformated = dateFormat.format(date);
        pressure.put("Time added", timeformated);
        pressure.put("Date added", dateformated);
        pressure.put("Added by", "Default");
        pressure.put("Value","0");
        DatabaseReference myRef2 = database.getReference("/Patients_Graphs/");
        String key2 = myRef.push().getKey();
        myRef2.child(key).child("PressureSys").child("Diastolic").child(key2).setValue(pressure);
        myRef2.child(key).child("PressureSys").child("Systolic").child(key2).setValue(pressure);
        myRef2.child(key).child("PressureSys").child("Diastolic").child(key2).setValue(pressure);
        myRef2.child(key).child("Blood_loss").child(key2).setValue(pressure);
        myRef2.child(key).child("Heart_Rate").child(key2).setValue(pressure);
        myRef2.child(key).child("Mental").child(key2).setValue(pressure);
        myRef2.child(key).child("Perfusion").child(key2).setValue(pressure);
        myRef2.child(key).child("Shock_Index").child(key2).setValue(pressure);*/
    }

    /*public void AddGraphDataDias(String Data, String Time, String id, String key){
        DatabaseReference myRef = database.getReference("/Patients/");
        Map<String, Object> data = new HashMap<>();
        data.put("Time", Time);
        data.put("Added by", mAuth.getUid());
        data.put("Data",Data);
        DatabaseReference myRef2 = database.getReference("/Patients_Graphs/");
        myRef2.child(id).child("PressureSys").child("Diastolic").child(key).setValue(data);

    }

    public void AddGraphDataSys(String Data, String Time, String id, String key){
        DatabaseReference myRef = database.getReference("/Patients/");
        Map<String, Object> data = new HashMap<>();
        data.put("Time", Time);
        data.put("Added by", mAuth.getUid());
        data.put("Data",Data);
        DatabaseReference myRef2 = database.getReference("/Patients_Graphs/");
        myRef2.child(id).child("PressureSys").child("Systolic").child(key).setValue(data);
    }*/

    public Query ListentoDatabaseOnData(String PatientID){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("/Data/"+PatientID+"/")
                .limitToLast(50);
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addChildEventListener(childEventListener);
        return query;
    }


    public void RemoveFromMyPatients(String ID){
        DatabaseReference myRef = database.getReference("/User_Patients/"+mAuth.getUid()+"/"+ID);
        myRef.removeValue();
    }
}
