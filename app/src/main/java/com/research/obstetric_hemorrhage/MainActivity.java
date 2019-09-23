package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

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
