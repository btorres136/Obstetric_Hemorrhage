package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> info = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        /*Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "Successfully Singed Out", Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });*/

        BottomNavigationView Nav = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        Nav.setOnNavigationItemSelectedListener(this);

        loadFragment(new Patient_Fragment());

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setElevation(0);
    }

    public void add(String Pat_Name, String Age, String id, String mStatus){
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("Patient Name", Pat_Name);
        usermap.put("Age", Age);
        usermap.put("Status", mStatus);
        usermap.put("mid",id);
        db.collection("User_Pat").document(mAuth.getUid()).collection(id).document("Registry").set(usermap);
    }

    public ArrayList get(){
        db.collection("User_Pat").document(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.v("Data", "Cached document data: " + document.getData());
                } else {
                    Log.d("Error: ", "Error getting documents: ", task.getException());
                }
            }
        });
        return info;
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
