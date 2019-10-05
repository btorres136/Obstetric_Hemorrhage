package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;




import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private Actual_Patient actual_patient = new Actual_Patient();
    private Patient_Fragment patient_fragment = new Patient_Fragment();
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions();

    //public static boolean wait;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        BottomNavigationView Nav = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        Nav.setOnNavigationItemSelectedListener(this);
        patient_fragment = databaseTransactions.ListenToDatabaseOnAllPatients();
        actual_patient = databaseTransactions.ListenToDatabaseOnMyPatients();
        loadFragment(patient_fragment);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setElevation(0);
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
                 fragment = patient_fragment;
                 break;
             case R.id.APatient:
                 fragment = actual_patient;
                 break;
             case R.id.Add:
                 fragment = new add_patient();
                 break;
         }
        return loadFragment(fragment);
    }
}
