package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        mAuth = FirebaseAuth.getInstance();
        ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar);
        pgsBar.setVisibility(View.GONE);

        CardView sign_in = (CardView)findViewById(R.id.cardView);
        sign_in.setOnClickListener(new View.OnClickListener() {
            EditText email = (EditText)findViewById(R.id.email);
            EditText pass = (EditText)findViewById(R.id.password);
            @Override
            public void onClick(View view) {
                if(!(email.getText().toString().isEmpty()) && !(pass.getText().toString().isEmpty()) )
                {
                    Signin(email.getText().toString(),pass.getText().toString());
                    ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar);
                    pgsBar.setVisibility(VISIBLE);
                    EditText email = (EditText)findViewById(R.id.email);
                    EditText pass = (EditText)findViewById(R.id.password);
                    email.setVisibility(View.GONE);
                    pass.setVisibility(View.GONE);
                    CardView sign_in = (CardView) findViewById(R.id.cardView);
                    TextView sign_up = (TextView) findViewById(R.id.sign_up);
                    sign_in.setVisibility(View.GONE);
                    sign_up.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please provide all requested fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView sign_up = (TextView)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            EditText email = (EditText)findViewById(R.id.email);
            EditText pass = (EditText)findViewById(R.id.password);
            @Override
            public void onClick(View view) {
                if(!(email.getText().toString().isEmpty()) && !(pass.getText().toString().isEmpty()) )
                {
                    Signup(email.getText().toString(),pass.getText().toString());
                    ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar);
                    pgsBar.setVisibility(VISIBLE);
                    EditText email = (EditText)findViewById(R.id.email);
                    EditText pass = (EditText)findViewById(R.id.password);
                    CardView sign_in = (CardView) findViewById(R.id.cardView);
                    TextView sign_up = (TextView)findViewById(R.id.sign_up);
                    email.setVisibility(View.GONE);
                    pass.setVisibility(View.GONE);
                    sign_in.setVisibility(View.GONE);
                    sign_up.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please provide all requested fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void Signup(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar);
                            pgsBar.setVisibility(View.GONE);
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            EditText email = (EditText)findViewById(R.id.email);
                            EditText pass = (EditText)findViewById(R.id.password);
                            CardView sign_in = (CardView)findViewById(R.id.cardView);
                            TextView sign_up = (TextView) findViewById(R.id.sign_up);
                            email.setVisibility(VISIBLE);
                            pass.setVisibility(VISIBLE);
                            sign_in.setVisibility(VISIBLE);
                            sign_up.setVisibility(VISIBLE);
                            Toast.makeText(MainActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                });
    }
    public void Signin(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Successfully Signed In", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar);
                            pgsBar.setVisibility(View.GONE);
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            EditText email = (EditText)findViewById(R.id.email);
                            EditText pass = (EditText)findViewById(R.id.password);
                            CardView sign_in = (CardView)findViewById(R.id.cardView);
                            TextView sign_up = (TextView)findViewById(R.id.textView);
                            email.setVisibility(VISIBLE);
                            pass.setVisibility(VISIBLE);
                            sign_in.setVisibility(VISIBLE);
                            sign_up.setVisibility(VISIBLE);
                            Toast.makeText(MainActivity.this, "Failed Registration: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void updateUI(FirebaseUser user){
        if(user != null){
            //Intent intent = new Intent(this, .class);
            //startActivity(intent);
        }
    }
    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

}
