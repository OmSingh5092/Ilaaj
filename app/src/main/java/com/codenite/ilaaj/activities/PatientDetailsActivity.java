package com.codenite.ilaaj.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codenite.ilaaj.api.controllers.AppointmentController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.api.dataModels.Record;
import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.databinding.ActivityPatientDetailsBinding;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;
import com.codenite.ilaaj.recyclerView.adapters.PreviousReportsAdapter;
import com.codenite.ilaaj.utils.DateFormatter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class PatientDetailsActivity extends AppCompatActivity {
    ActivityPatientDetailsBinding binding;
    Appointment appointment;
    User patient;
    PreviousReportsAdapter adapter;
    List<Record> records = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        loadData();
        appointment = (Appointment)getIntent().getSerializableExtra("appointment");


        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMeetingLink();
            }
        });
    }

    void loadData(){
        binding.time.setText(new DateFormatter(appointment.getDateTime()).getDateAndTime());
        // Get patient data;
    }

    void setMeetingLink(){
        new AppointmentController(this).updateAppointment(appointment, new AppointmentController.updateAppointmentHandler() {
            @Override
            public void onSuccess() {
                Toast.makeText(PatientDetailsActivity.this, "Meeting link updated successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(PatientDetailsActivity.this, "Some error occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void fetchRecords(){

    }

    void setUpRecyclerView(){
        adapter = new PreviousReportsAdapter(this, records, new ItemClickHandler() {
            @Override
            public void onViewClick(int position) {
                //Set Click listener to open the documents.
            }
        });

        binding.reports.setLayoutManager(new LinearLayoutManager(this));
        binding.reports.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


}