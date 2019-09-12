package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private ArrayList<String> Questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        Questions = new ArrayList<String>();

        FirebaseDatabase.getInstance().getReference().child("/Questions")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Questions.add(snapshot.getValue().toString());
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


        Spinner spinner = findViewById(R.id.Questions1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Questions);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.Questions2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Questions);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        CardView Register =(CardView)findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText)findViewById(R.id.email);
                EditText password = (EditText)findViewById(R.id.password);

                if(!(email.getText().toString().isEmpty()) && !(password.getText().toString().isEmpty()) && verifypassword())
                {
                    Signup(email.getText().toString(), password.getText().toString());
                    //showprogressbar();
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Please provide all requested fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showprogressbar(){
        ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar);
        pgsBar.setVisibility(VISIBLE);
        EditText email = (EditText)findViewById(R.id.email);
        EditText pass = (EditText)findViewById(R.id.password);
        CardView sign_in = (CardView) findViewById(R.id.Register);
        TextView sign_up = (TextView)findViewById(R.id.sign_up);
        email.setVisibility(View.GONE);
        pass.setVisibility(View.GONE);
        sign_in.setVisibility(View.GONE);
        sign_up.setVisibility(View.GONE);
    }

    public boolean verifypassword(){
        EditText password = (EditText)findViewById(R.id.password);
        EditText repassword = (EditText)findViewById(R.id.retypepassword);
        if(password.getText().toString().equals(repassword.getText().toString()))
        {
            return true;
        }else
        {
            Toast.makeText(SignupActivity.this, "Passwords are not the same", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public void Signup(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignupActivity.this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
                            updateUI(user, null);
                        } else {
                            // If sign in fails, display a message to the user.
                            updateUI(null, task);
                        }
                    }

                });
    }

    public void updateUI(FirebaseUser user, Task<AuthResult> task) {
        if (user != null && task == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (user == null && task != null) {
            FirebaseAuthException e = (FirebaseAuthException) task.getException();
            Toast.makeText(SignupActivity.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            //Blank
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}