package com.research.obstetric_hemorrhage;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;


public class add_patient extends Fragment {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private MainActivity main = new MainActivity();
    private View rootView;
    private String text = "";
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions(0);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_patient, container, false);

        CardView enter = rootView.findViewById(R.id.add_to_patients);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = rootView.findViewById(R.id.input_firstName);
                TextView lname = rootView.findViewById(R.id.input_lastName);
                TextView room = rootView.findViewById(R.id.input_Room);
                TextView age = rootView.findViewById(R.id.input_Age);
                if (Integer.parseInt(age.getText().toString()) > 65 || Integer.parseInt(age.getText().toString()) < 14){
                    Toast.makeText(view.getContext(), "Age should be between 14 and 65", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseTransactions.addtopat(name.getText().toString()+" "+lname.getText().toString(),
                            age.getText().toString(),text, room.getText().toString());
                }
            }
        });

        Spinner spinner = rootView.findViewById(R.id.input_Stage);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Stage, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text = adapterView.getItemAtPosition(i).toString();
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }


}
