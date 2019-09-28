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


import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.research.obstetric_hemorrhage.Actual_Patient.getList;
import static com.research.obstetric_hemorrhage.Patient_Fragment.getallpat;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<String> pat_name = new ArrayList<>();
    private ArrayList<String> allpat_name = new ArrayList<>();
    private ArrayList<String> allpat_age = new ArrayList<>();
    private ArrayList<String> allpat_id = new ArrayList<>();
    private ArrayList<String> allpat_status = new ArrayList<>();
    public static boolean wait;


    public void get(){
        database.getReference().child("User_Patients/"+mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pat_name.clear();
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("Patient Name").getValue().toString();
                        pat_name.add(name);
                    }
                }
                getList(pat_name);

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
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("Patient Name").getValue().toString();
                        String age = snapshot.child("Age").getValue().toString();
                        String status = snapshot.child("Status").getValue().toString();
                        String id = snapshot.child("mid").getValue().toString();
                        allpat_name.add(name);
                        allpat_age.add(age);
                        allpat_id.add(id);
                        allpat_status.add(status);
                    }
                }
                getallpat(allpat_name, allpat_age,allpat_id,allpat_status);

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

        DatabaseReference reference = database.getReference();
        reference.child("/Patients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childre = dataSnapshot.getChildren();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void add(String Pat_Name, String Age, String mStatus){
        DatabaseReference myRef = database.getReference("/User_Patients/"+mAuth.getUid()+"/");
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("Patient Name", Pat_Name);
        usermap.put("Age", Age);
        usermap.put("Status", mStatus);
        String key = myRef.push().getKey();
        usermap.put("mid",key);
        myRef.child(key).setValue(usermap);
    }
    public void addtopat(String Pat_Name, String Age, String mStatus){
        DatabaseReference myRef = database.getReference("/Patients/");
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("Patient Name", Pat_Name);
        usermap.put("Age", Age);
        usermap.put("Status", mStatus);
        String key = myRef.push().getKey();
        usermap.put("mid",key);
        usermap.put("Added by", mAuth.getUid());
        myRef.child(key).setValue(usermap);
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
