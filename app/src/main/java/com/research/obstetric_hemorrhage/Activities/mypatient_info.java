package com.research.obstetric_hemorrhage.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.research.obstetric_hemorrhage.Firebase.DatabaseTransactions;
import com.research.obstetric_hemorrhage.R;


public class mypatient_info extends AppCompatActivity {


    private DatabaseTransactions databaseTransactions = new DatabaseTransactions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypatient_info_layout);
    }
}