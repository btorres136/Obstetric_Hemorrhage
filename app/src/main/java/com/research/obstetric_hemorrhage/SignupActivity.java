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

import static android.view.View.VISIBLE;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();


        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Security_Questions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
            ProgressBar pgsBar = (ProgressBar) findViewById(R.id.progressBar);
            pgsBar.setVisibility(View.GONE);
            FirebaseAuthException e = (FirebaseAuthException) task.getException();
            EditText email = (EditText) findViewById(R.id.email);
            EditText pass = (EditText) findViewById(R.id.password);
            CardView sign_in = (CardView) findViewById(R.id.cardView);
            TextView sign_up = (TextView) findViewById(R.id.sign_up);
            email.setVisibility(VISIBLE);
            pass.setVisibility(VISIBLE);
            sign_in.setVisibility(VISIBLE);
            sign_up.setVisibility(VISIBLE);
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