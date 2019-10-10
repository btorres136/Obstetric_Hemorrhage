package com.research.obstetric_hemorrhage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class mypatient_info extends AppCompatActivity {

    EditText xValue_sys, yValue_sys;
    CardView update_info;

    FirebaseDatabase database;
    DatabaseReference reference;

    GraphView graph_presion;
    LineGraphSeries series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypatient_info_layout);
//////////
        xValue_sys = (EditText) graph_presion.findViewById(R.id.input_syspresX);
        yValue_sys = (EditText) graph_presion.findViewById(R.id.input_syspres);
        update_info = (CardView) update_info.findViewById(R.id.update_infoCV);
        graph_presion = (GraphView) graph_presion.findViewById(R.id.graph_presion);
        series = new LineGraphSeries();
        graph_presion.addSeries(series);
        reference = database.getReference("charTable");

        setListeners();
    }

    private void setListeners() {
        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = reference.push().getKey();

                int x = Integer.parseInt(xValue_sys.getText().toString());
                int y = Integer.parseInt(yValue_sys.getText().toString());

                PointValue_sys pointvalue_sys = new PointValue_sys(x, y);
                reference.child(id).setValue(pointvalue_sys);

            }
        });
    }

    public void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
                int index = 0;

                for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
                    PointValue_sys pointValueSys = dataSnapshot.getValue(PointValue_sys.class);
                    dp[index] = new DataPoint(pointValueSys.getxValue_sys(), pointValueSys.getyValue_sys());
                    index++;
                }

                series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    ////////////////////
}