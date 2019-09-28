package com.research.obstetric_hemorrhage;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class add_patient extends Fragment {
    private MainActivity main = new MainActivity();
    private View rootView;

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
                TextView age = rootView.findViewById(R.id.input_Age);
                TextView stage = rootView.findViewById(R.id.input_Stage);
                main.addtopat(name.getText().toString()+" "+lname.getText().toString(),
                        age.getText().toString(),stage.getText().toString());
            }
        });

        return rootView;
    }

}
