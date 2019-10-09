package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;




import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Actual_Patient actual_patient = new Actual_Patient();
    private Patient_Fragment patient_fragment = new Patient_Fragment();
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        BottomNavigationView Nav = findViewById(R.id.bottomNavigationView);
        Nav.setOnNavigationItemSelectedListener(this);

        loadFragment(patient_fragment);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setElevation(0);
        FirebaseMessaging.getInstance().subscribeToTopic("PatientAdded");
        FirebaseMessaging.getInstance().subscribeToTopic("PatientDeleted");
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
