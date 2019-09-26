package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.VISIBLE;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar2);
        pgsBar.setVisibility(View.GONE);
        ImageView icon = findViewById(R.id.principal_icon2);
        icon.setVisibility(View.GONE);


        /*FirebaseDatabase.getInstance().getReference().child("/Questions")
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
                });*/


        Spinner spinner = findViewById(R.id.Questions1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Questions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        Spinner spinner2 = findViewById(R.id.Questions2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Questions, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);


        CardView Register =(CardView)findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText)findViewById(R.id.email);
                EditText password = (EditText)findViewById(R.id.password);
                EditText answer = findViewById(R.id.answer1);
                EditText answer2 = findViewById(R.id.answer2);

                if(!(email.getText().toString().isEmpty()) && !(password.getText().toString().isEmpty()) && verifypassword() && !(answer.getText().toString().isEmpty()) && !(answer2.getText().toString().isEmpty()) && verifyquestions())
                {
                    Signup(email.getText().toString(), password.getText().toString());
                    showprogressbar();
                }
            }
        });

    }

    public boolean verifyquestions(){
        Spinner spinner = findViewById(R.id.Questions1);
        Spinner spinner2 = findViewById(R.id.Questions2);
        if(!(spinner.getSelectedItem().equals(spinner2.getSelectedItem()))) {
            return true;
        } else  {
            Toast.makeText(SignupActivity.this, "Questions can't be the same", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void showprogressbar(){
        ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar2);
        pgsBar.setVisibility(VISIBLE);
        ImageView icon = findViewById(R.id.principal_icon2);
        icon.setVisibility(VISIBLE);

        TextView text = findViewById(R.id.Sign_upLabel);
        text.setVisibility(View.GONE);
        TextView text2 = findViewById(R.id.SQ1);
        text2.setVisibility(View.GONE);
        TextView text3 = findViewById(R.id.SQ2);
        text3.setVisibility(View.GONE);
        EditText answer = findViewById(R.id.answer1);
        answer.setVisibility(View.GONE);
        EditText answer2 = findViewById(R.id.answer2);
        answer2.setVisibility(View.GONE);
        Spinner spinner = findViewById(R.id.Questions1);
        spinner.setVisibility(View.GONE);
        Spinner spinner2 = findViewById(R.id.Questions2);
        spinner2.setVisibility(View.GONE);
        CardView Register =(CardView)findViewById(R.id.Register);
        Register.setVisibility(View.GONE);
        EditText email = (EditText)findViewById(R.id.email);
        email.setVisibility(View.GONE);
        EditText password = (EditText)findViewById(R.id.password);
        password.setVisibility(View.GONE);
        EditText repassword = (EditText)findViewById(R.id.retypepassword);
        repassword.setVisibility(View.GONE);
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
            EditText answer1 = findViewById(R.id.answer1);
            EditText answer2 = findViewById(R.id.answer2);
            Spinner question1 = findViewById(R.id.Questions1);
            Spinner question2 = findViewById(R.id.Questions2);
            String uid = mAuth.getCurrentUser().getUid();
            String squestion1= question1.getSelectedItem().toString();
            String squestion2 = question2.getSelectedItem().toString();

            Map<String, Object> usermap = new HashMap<>();
            usermap.put("Question1", squestion1);
            usermap.put("Question2", squestion2);
            usermap.put("Answer1", answer1.getText().toString());
            usermap.put("Answer2",answer2.getText().toString());

            db.collection("Users").document(uid)
                    .set(usermap);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (user == null && task != null) {
            FirebaseAuthException e = (FirebaseAuthException) task.getException();
            ProgressBar pgsBar = (ProgressBar)findViewById(R.id.progressBar2);
            pgsBar.setVisibility(View.GONE);
            ImageView icon = findViewById(R.id.principal_icon2);
            icon.setVisibility(View.GONE);

            TextView text = findViewById(R.id.Sign_upLabel);
            text.setVisibility(VISIBLE);
            TextView text2 = findViewById(R.id.SQ1);
            text2.setVisibility(VISIBLE);
            TextView text3 = findViewById(R.id.SQ2);
            text3.setVisibility(VISIBLE);
            EditText answer = findViewById(R.id.answer1);
            answer.setVisibility(VISIBLE);
            EditText answer2 = findViewById(R.id.answer2);
            answer2.setVisibility(VISIBLE);
            Spinner spinner = findViewById(R.id.Questions1);
            spinner.setVisibility(VISIBLE);
            Spinner spinner2 = findViewById(R.id.Questions2);
            spinner2.setVisibility(VISIBLE);
            CardView Register =(CardView)findViewById(R.id.Register);
            Register.setVisibility(VISIBLE);
            EditText email = (EditText)findViewById(R.id.email);
            email.setVisibility(VISIBLE);
            EditText password = (EditText)findViewById(R.id.password);
            password.setVisibility(VISIBLE);
            EditText repassword = (EditText)findViewById(R.id.retypepassword);
            repassword.setVisibility(VISIBLE);
            Toast.makeText(SignupActivity.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}