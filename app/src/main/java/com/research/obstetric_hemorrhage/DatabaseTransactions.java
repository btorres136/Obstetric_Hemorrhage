package com.research.obstetric_hemorrhage;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseTransactions {
    private ArrayList<Patient_Medical> My_Patients_Array;
    private ArrayList<Patient_Medical> All_Patients_Array;
    private ArrayList<Systolic_Pressure> My_PatientsGraps_Array;
    private Patient_Medical patient_medical = new Patient_Medical();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Actual_Patient actual_patient;
    private Patient_Fragment patient_fragment;
    private Systolic_Pressure systolicPressure;

    private ArrayList<String> X_Systolic;
    private ArrayList<String> Y_Systolic;
    private ArrayList<String> X_Diastolic;
    private ArrayList<String> Y_Diastolic;

    public DatabaseTransactions(){
        actual_patient = new Actual_Patient();
        patient_fragment = new Patient_Fragment();
        All_Patients_Array = new ArrayList<>();
        My_Patients_Array = new ArrayList<>();
        systolicPressure = new Systolic_Pressure();
        My_PatientsGraps_Array = new ArrayList<>();

        X_Diastolic = new ArrayList<>();
        Y_Diastolic = new ArrayList<>();
        X_Systolic = new ArrayList<>();
        Y_Systolic = new ArrayList<>();
    }
    public DatabaseTransactions(int i){
    }


    public Patient_Fragment ListenToDatabaseOnAllPatients(){
        database.getReference().child("/Patients").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("Patient Name").getValue().toString();
                String id = dataSnapshot.child("mid").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String age = dataSnapshot.child("Age").getValue().toString();
                String room = dataSnapshot.child("Room").getValue().toString();
                patient_medical = new Patient_Medical(name, id,room,
                        Integer.parseInt(age), Integer.parseInt(status));
                All_Patients_Array.add(0,patient_medical);
                Log.v("Hola","quien soy");
                patient_fragment.setAll_Patients_Array(All_Patients_Array);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("Patient Name").getValue().toString();
                String id = dataSnapshot.child("mid").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String age = dataSnapshot.child("Age").getValue().toString();
                String room = dataSnapshot.child("Room").getValue().toString();
                patient_medical = new Patient_Medical(name, id,room,
                        Integer.parseInt(age), Integer.parseInt(status));
                My_Patients_Array.add(0,patient_medical);
                patient_fragment.setAll_Patients_Array(All_Patients_Array);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Patient Name").getValue().toString();
                String id = dataSnapshot.child("mid").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String age = dataSnapshot.child("Age").getValue().toString();
                String room = dataSnapshot.child("Room").getValue().toString();
                patient_medical = new Patient_Medical(name, id,room,
                        Integer.parseInt(age), Integer.parseInt(status));
                All_Patients_Array.add(0,patient_medical);
                patient_fragment.setAll_Patients_Array(All_Patients_Array);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("Patient Name").getValue().toString();
                String id = dataSnapshot.child("mid").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String age = dataSnapshot.child("Age").getValue().toString();
                String room = dataSnapshot.child("Room").getValue().toString();
                patient_medical = new Patient_Medical(name, id,room,
                        Integer.parseInt(age), Integer.parseInt(status));
                All_Patients_Array.add(0,patient_medical);
                patient_fragment.setAll_Patients_Array(All_Patients_Array);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return patient_fragment;
    }

    public Actual_Patient ListenToDatabaseOnMyPatients(){
        database.getReference().child("/User_Patients/"+mAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("Patient Name").getValue().toString();
                String id = dataSnapshot.child("mid").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String age = dataSnapshot.child("Age").getValue().toString();
                String room = dataSnapshot.child("Room").getValue().toString();
                patient_medical = new Patient_Medical(name, id,room,
                        Integer.parseInt(age), Integer.parseInt(status));
                //systolicPressure = new Systolic_Pressure(getgrahdata(id));
                //Log.v("Holis",""+systolicPressure.getXSystolic().size());
                //My_PatientsGraps_Array.add(0,systolicPressure);
                My_Patients_Array.add(0,patient_medical);
                //actual_patient.setMy_PatientsGraph_Array(My_PatientsGraps_Array);
                actual_patient.setMy_Patients_Array(My_Patients_Array);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("Patient Name").getValue().toString();
                String id = dataSnapshot.child("mid").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String age = dataSnapshot.child("Age").getValue().toString();
                String room = dataSnapshot.child("Room").getValue().toString();
                patient_medical = new Patient_Medical(name, id,room,
                        Integer.parseInt(age), Integer.parseInt(status));
                //systolicPressure = new Systolic_Pressure();
                //systolicPressure = getgrahdata(id);
                //My_PatientsGraps_Array.add(0,systolicPressure);
                My_Patients_Array.add(0,patient_medical);
                //actual_patient.setMy_PatientsGraph_Array(My_PatientsGraps_Array);
                actual_patient.setMy_Patients_Array(My_Patients_Array);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Patient Name").getValue().toString();
                String id = dataSnapshot.child("mid").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String age = dataSnapshot.child("Age").getValue().toString();
                String room = dataSnapshot.child("Room").getValue().toString();
                patient_medical = new Patient_Medical(name, id,room,
                        Integer.parseInt(age), Integer.parseInt(status));
                //systolicPressure = new Systolic_Pressure();
                //systolicPressure = getgrahdata(id);
                //My_PatientsGraps_Array.add(0,systolicPressure);
                My_Patients_Array.add(0,patient_medical);
                //actual_patient.setMy_PatientsGraph_Array(My_PatientsGraps_Array);
                actual_patient.setMy_Patients_Array(My_Patients_Array);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("Patient Name").getValue().toString();
                String id = dataSnapshot.child("mid").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String age = dataSnapshot.child("Age").getValue().toString();
                String room = dataSnapshot.child("Room").getValue().toString();
                patient_medical = new Patient_Medical(name, id,room,
                        Integer.parseInt(age), Integer.parseInt(status));
                //systolicPressure = new Systolic_Pressure();
                //systolicPressure = getgrahdata(id);
                //My_PatientsGraps_Array.add(0,systolicPressure);
                My_Patients_Array.add(0,patient_medical);
                //actual_patient.setMy_PatientsGraph_Array(My_PatientsGraps_Array);
                actual_patient.setMy_Patients_Array(My_Patients_Array);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return actual_patient;
    }

    public void add(String Pat_Name, int Age, int mStatus, String room, String key){
        DatabaseReference myRef = database.getReference("/User_Patients/"+mAuth.getUid()+"/");
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("Patient Name", Pat_Name);
        usermap.put("Age", Age);
        usermap.put("Status", mStatus);
        usermap.put("Room", room);
        usermap.put("mid", key);
        myRef.child(key).setValue(usermap);
    }

    public void addtopat(String Pat_Name, String Age, String mStatus, String room){
        DatabaseReference myRef = database.getReference("/Patients/");
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("Patient Name", Pat_Name);
        usermap.put("Age", Age);
        usermap.put("Room",room);
        usermap.put("Status", mStatus);
        String key = myRef.push().getKey();
        usermap.put("mid",key);
        usermap.put("Added by", mAuth.getUid());
        myRef.child(key).setValue(usermap);
        Map<String, String> pressure = new HashMap<>();
        pressure.put("Time", "0");
        pressure.put("Added by:", "Default");
        pressure.put("Data","0");
        DatabaseReference myRef2 = database.getReference("/Systolic_Pressure/");
        String key2 = myRef.push().getKey();
        myRef2.child(key).child("Pressure").child("Diastolic").child(key2).setValue(pressure);
        myRef2.child(key).child("Pressure").child("Systolic").child(key2).setValue(pressure);
    }

    public Systolic_Pressure getgrahdata(final String id){
        database.getReference().child("/Patients_Graphs/"+id+"/Pressure/Diastolic").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String y = dataSnapshot.child("Data").getValue().toString();
                String x = dataSnapshot.child("Time").getValue().toString();
                X_Systolic.add(x);
                Y_Systolic.add(y);
                systolicPressure = new Systolic_Pressure(X_Systolic, Y_Systolic);
                My_PatientsGraps_Array.add(systolicPressure);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String y = dataSnapshot.child("Data").getValue().toString();
                String x = dataSnapshot.child("Time").getValue().toString();
                X_Systolic.add(x);
                Y_Systolic.add(y);
                systolicPressure = new Systolic_Pressure(X_Systolic, Y_Systolic);
                My_PatientsGraps_Array.add(systolicPressure);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String y = dataSnapshot.child("Data").getValue().toString();
                String x = dataSnapshot.child("Time").getValue().toString();
                X_Systolic.add(x);
                Y_Systolic.add(y);
                systolicPressure = new Systolic_Pressure(X_Systolic, Y_Systolic);
                My_PatientsGraps_Array.add(systolicPressure);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String y = dataSnapshot.child("Data").getValue().toString();
                String x = dataSnapshot.child("Time").getValue().toString();
                X_Systolic.add(x);
                Y_Systolic.add(y);
                systolicPressure = new Systolic_Pressure(X_Systolic, Y_Systolic);
                My_PatientsGraps_Array.add(systolicPressure);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


        /*database.getReference().child("/Systolic_Pressure/"+id+"/Pressure/Systolic/").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                /*String x = dataSnapshot.getKey();
                String y = dataSnapshot.getValue().toString();
                X_Systolic.add(x);
                Y_Systolic.add(y);

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
        });*/
        //systolicPressure = new Systolic_Pressure(X_Systolic, Y_Systolic, X_Diastolic, Y_Systolic);
        return systolicPressure;
    }
}
