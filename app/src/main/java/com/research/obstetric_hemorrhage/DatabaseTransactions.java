package com.research.obstetric_hemorrhage;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseTransactions {
    private ArrayList<Patient_Medical> My_Patients_Array;
    private ArrayList<Patient_Medical> All_Patients_Array;
    private Patient_Medical patient_medical = new Patient_Medical();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Actual_Patient actual_patient;
    private Patient_Fragment patient_fragment;
    private DataSnapshot datasnapshot;

    DatabaseTransactions(){
        actual_patient = new Actual_Patient();
        patient_fragment = new Patient_Fragment();
        All_Patients_Array = new ArrayList<>();
        My_Patients_Array = new ArrayList<>();
        //snapshot = null;
    }

    public Actual_Patient get(){
        database.getReference().child("User_Patients/"+mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("Patient Name").getValue().toString();
                        String id = snapshot.child("mid").getValue().toString();
                        String status = snapshot.child("Status").getValue().toString();
                        String age = snapshot.child("Age").getValue().toString();
                        String room = snapshot.child("Room").getValue().toString();
                        patient_medical = new Patient_Medical(name, id,room,
                                Integer.parseInt(age), Integer.parseInt(status));
                        My_Patients_Array.add(patient_medical);
                        actual_patient.setMy_Patients_Array(My_Patients_Array);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return actual_patient;
    }

    public Patient_Fragment get_pat(){
        database.getReference().child("/Patients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("Patient Name").getValue().toString();
                        String age = snapshot.child("Age").getValue().toString();
                        String status = snapshot.child("Status").getValue().toString();
                        String id = snapshot.child("mid").getValue().toString();
                        String room = snapshot.child("Room").getValue().toString();
                        patient_medical = new Patient_Medical(name, id,room,
                                Integer.parseInt(age), Integer.parseInt(status));
                        All_Patients_Array.add(patient_medical);
                        patient_fragment.setAll_Patients_Array(All_Patients_Array);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return patient_fragment;
    }

    public ArrayList<Patient_Medical> getnewdata(){
        database.getReference().child("User_Patients/"+mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("Patient Name").getValue().toString();
                        String id = snapshot.child("mid").getValue().toString();
                        String status = snapshot.child("Status").getValue().toString();
                        String age = snapshot.child("Age").getValue().toString();
                        String room = snapshot.child("Room").getValue().toString();
                        patient_medical = new Patient_Medical(name, id,room,
                                Integer.parseInt(age), Integer.parseInt(status));
                        My_Patients_Array.add(patient_medical);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return My_Patients_Array;
    }

    public DataSnapshot getSnapshot(){
        database.getReference().child("/Patients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    datasnapshot = dataSnapshot;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return datasnapshot;
    }

    public void getgraphdata(String id){
        final Patient_Medical patient_medical = new Patient_Medical();
        database.getReference().child("/Patients_Graphs/"+id+"/Pressure/Diastolic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot data;
                if(dataSnapshot.exists()){
                    ArrayList<String> pressure = new ArrayList<>();
                    pressure = (ArrayList<String>) dataSnapshot.getValue();
                    patient_medical.setDiastolic(pressure);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database.getReference().child("/Patients_Graphs/"+id+"/Pressure/Systolic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    ArrayList<String> pressure = new ArrayList<>();
                    pressure = (ArrayList<String>) dataSnapshot.getValue();
                    patient_medical.setSystolic(pressure);
                    //setgraphsinfo(patient_medical);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
