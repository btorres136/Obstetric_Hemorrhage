package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


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
        usermap.put("Status", Integer.parseInt(mStatus));
        String key = myRef.push().getKey();
        usermap.put("ID",key);
        usermap.put("Added by", mAuth.getUid());
        myRef.child(key).setValue(usermap);
        Map<String, String> pressure = new HashMap<>();
        pressure.put("Time", "0");
        pressure.put("Added by:", "Default");
        pressure.put("Data","0");
        DatabaseReference myRef2 = database.getReference("/Patients_Graphs/");
        String key2 = myRef.push().getKey();
        myRef2.child(key).child("Pressure").child("Diastolic").child(key2).setValue(pressure);
        myRef2.child(key).child("Pressure").child("Systolic").child(key2).setValue(pressure);
    }

    public void submitgraphinfo(String sys, String dias, String id){
        Map<String, String> pressure = new HashMap<>();
        pressure.put("Time", "15");
        pressure.put("Added by:", "Default");
        pressure.put("Data",dias);
        DatabaseReference myRef = database.getReference("/Patients_Graphs/");
        String key = myRef.push().getKey();
        myRef.child(id).child("Pressure").child("Diastolic").child(key).setValue(pressure);
    }
}
