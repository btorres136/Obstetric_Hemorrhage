package com.research.obstetric_hemorrhage.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.research.obstetric_hemorrhage.R;

public class input_data extends AppCompatActivity {
    EditText sis_press;
    EditText dis_press;
    EditText ebl;
    EditText perfusion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
    }
}
