package com.research.obstetric_hemorrhage.Fragments;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.research.obstetric_hemorrhage.Activities.mypatient_info;
import com.research.obstetric_hemorrhage.Services.Alarm;
import com.research.obstetric_hemorrhage.Firebase.DatabaseTransactions;
import com.research.obstetric_hemorrhage.Classes.Patient_Medical;
import com.research.obstetric_hemorrhage.R;

import static android.content.Context.ALARM_SERVICE;
import static android.os.Build.VERSION.SDK_INT;


public class Actual_Patient extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private DatabaseTransactions databaseTransactions = new DatabaseTransactions();
    private SwipeRefreshLayout swipe;
    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_actual__patient, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_mypat);

        FirebaseRecyclerOptions<Patient_Medical> options =
                new FirebaseRecyclerOptions.Builder<Patient_Medical>()
                        .setQuery(databaseTransactions.ListenToDatabaseOnMyPatients(), Patient_Medical.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Patient_Medical, ActualPatient_RecyclerView>(options) {
            @NonNull
            @Override
            public ActualPatient_RecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.actualpatient_layout, parent, false);
                ActualPatient_RecyclerView actualPatient_recyclerView = new ActualPatient_RecyclerView(view);
                return actualPatient_recyclerView;
            }
            @Override
            protected void onBindViewHolder(@NonNull ActualPatient_RecyclerView holder, int position, @NonNull final Patient_Medical model) {
                holder.getTextView_Patient().setText("Patient: "+model.getName());
                holder.getTextView_Age().setText("Age: "+model.getAge());
                holder.getTextView_id().setText("ID: "+model.getID());
                holder.getTextView_room().setText("Room: "+model.getRoom());
                holder.getTextView_status().setText("Stage: "+model.getStage());
                /*final Spinner spinner = holder.getSpinner_timer();
                holder.getCardView_set_timer().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(spinner.getSelectedItem().toString());
                        Intent intent = new Intent(view.getContext(), Alarm.class);
                        intent.putExtra("PATIENT_ID",model.getID());
                        final int _id = (int) System.currentTimeMillis();
                        intent.putExtra("ID",_id);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(view.getContext(),_id,intent,PendingIntent.FLAG_ONE_SHOT);
                        AlarmManager alarmManager = (AlarmManager) view.getContext().getSystemService(ALARM_SERVICE);
                        if (SDK_INT >= Build.VERSION_CODES.M) {
                            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+number*1000,pendingIntent);
                        }
                    }
                });*/
                holder.getCardView_erase().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            // setup the alert builder
                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                            builder.setTitle("Alert!");
                            builder.setMessage("Are you sure do you want to erase this patient?" +
                                    "\n"+
                                    "\n"+
                                    "You will not receive notifications of this patient anymore and also " +
                                    "you will not have permission to modify his Information");

                            // add the buttons
                            builder.setPositiveButton("Erase", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    databaseTransactions.RemoveFromMyPatients(model.getID());
                                }
                            });
                            builder.setNegativeButton("Cancel", null);

                            // create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                    }
                });

/*                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.time_spinner, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.getSpinner_timer().setAdapter(adapter);
                holder.getSpinner_timer().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });*/

                holder.getCardView_see_info().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), mypatient_info.class);
                        intent.putExtra("PATIENT_ID",model.getID());
                        intent.putExtra("PATIENT_NAME", model.getName());
                        intent.putExtra("PATIENT_AGE",Integer.toString(model.getAge()));
                        intent.putExtra("PATIENT_ROOM",model.getRoom());
                        intent.putExtra("PATIENT_STATE",Integer.toString(model.getStage()));
                        startActivity(intent);
                    }
                });

            }
        };
        adapter.startListening();

        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayout);

        swipe = rootView.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);


        return rootView;
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        //adapter = new ActualPatient_RecyclerView(My_Patients_Array);
        recyclerView = rootView.findViewById(R.id.recycler_mypat);
        //recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe.setRefreshing(false);
    }

}
