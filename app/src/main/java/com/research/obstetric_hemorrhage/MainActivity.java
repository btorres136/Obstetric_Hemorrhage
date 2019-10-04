package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.media.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.research.obstetric_hemorrhage.Actual_Patient.getList;
import static com.research.obstetric_hemorrhage.Actual_Patient.setgraphsinfo;
import static com.research.obstetric_hemorrhage.Patient_Fragment.getallpat;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<String> pat_name = new ArrayList<>();
    private ArrayList<String> pat_age = new ArrayList<>();
    private ArrayList<String> pat_id = new ArrayList<>();
    private ArrayList<String> pat_status = new ArrayList<>();
    private ArrayList<String> pat_room = new ArrayList<>();
    private LineGraphSeries<DataPoint> systolic;
    private LineGraphSeries<DataPoint> distolic;
    private ArrayList<String> pat_datapoints = new ArrayList<>();
    private ArrayList<String> allpat_name = new ArrayList<>();
    private ArrayList<String> allpat_age = new ArrayList<>();
    private ArrayList<String> allpat_id = new ArrayList<>();
    private ArrayList<String> allpat_status = new ArrayList<>();
    private ArrayList<String> allpat_room = new ArrayList<>();
    public static boolean wait;
    private FirebaseAnalytics mFirebaseAnalytics;


    public void get(){
        database.getReference().child("User_Patients/"+mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pat_name.clear();
                pat_age.clear();
                pat_id.clear();
                pat_status.clear();
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("Patient Name").getValue().toString();
                        String id = snapshot.child("mid").getValue().toString();
                        String status = snapshot.child("Status").getValue().toString();
                        String age = snapshot.child("Age").getValue().toString();
                        String room = snapshot.child("Room").getValue().toString();
                        pat_name.add(name);
                        pat_age.add(age);
                        pat_status.add(status);
                        pat_id.add(id);
                        pat_room.add(room);
                    }
                }
                getList(pat_name, pat_age, pat_id, pat_status, pat_room);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void get_pat(){
        database.getReference().child("/Patients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allpat_name.clear();
                allpat_age.clear();
                allpat_id.clear();
                allpat_status.clear();
                allpat_room.clear();
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("Patient Name").getValue().toString();
                        String age = snapshot.child("Age").getValue().toString();
                        String status = snapshot.child("Status").getValue().toString();
                        String id = snapshot.child("mid").getValue().toString();
                        String room = snapshot.child("Room").getValue().toString();
                        allpat_name.add(name);
                        allpat_age.add(age);
                        allpat_id.add(id);
                        allpat_status.add(status);
                        allpat_room.add(room);
                    }
                }
                getallpat(allpat_name, allpat_age,allpat_id,allpat_status,allpat_room);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                    Log.v("size111","1");
                    setgraphsinfo(patient_medical);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        BottomNavigationView Nav = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        Nav.setOnNavigationItemSelectedListener(this);
        loadFragment(new Patient_Fragment());
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setElevation(0);
        get_pat();
        get();
        wait=true;
        FirebaseMessaging.getInstance().subscribeToTopic("PatientAdded");
        FirebaseMessaging.getInstance().subscribeToTopic("PatientDeleted");
    }

    public void add(String Pat_Name, String Age, String mStatus, String room, String key){
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
        pressure.put("0","0");
        DatabaseReference myRef2 = database.getReference("/Patients_Graphs/");
        myRef2.child(key).child("Pressure").child("Diastolic").setValue(pressure);
        myRef2.child(key).child("Pressure").child("Systolic").setValue(pressure);
    }

    public void updateUI(FirebaseUser user){
        if(user == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    private boolean loadFragment(Fragment fragment)
    {
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FramePatients, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

         switch (menuItem.getItemId()){
             case R.id.Patients:
                 fragment = new Patient_Fragment();
                 break;
             case R.id.APatient:
                 fragment = new Actual_Patient();
                 break;
             case R.id.Add:
                 fragment = new add_patient();
                 break;
         }
        return loadFragment(fragment);
    }
}
