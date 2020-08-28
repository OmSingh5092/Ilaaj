package com.codenite.ilaaj.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.codenite.ilaaj.api.controllers.AppointmentController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.databinding.ActivityAppointmentBinding;
import com.codenite.ilaaj.utils.DateFormatter;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentActivity extends AppCompatActivity {
    ActivityAppointmentBinding binding;
    Appointment appointment;
    Calendar calendar;
    Date appointmentDate;
    User doctor = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        setPickerDialogs();
        doctor = (User) getIntent().getSerializableExtra("doctor");

        addInfo();

        binding.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate()){
                    return;
                }
                getDataFromEditText();
                startPayment();
            }
        });
    }

    private void addInfo(){
        binding.doctorName.setText(doctor.getName());
    }

    private void setPickerDialogs(){
        Calendar present = Calendar.getInstance();
        calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);

                binding.date.setText(new DateFormatter(new Date(calendar.getTimeInMillis())).getDate());
            }
        },present.get(Calendar.YEAR),present.get(Calendar.MONTH),present.get(Calendar.DAY_OF_MONTH));

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar.set(Calendar.HOUR_OF_DAY,i);
                calendar.set(Calendar.MINUTE,i1);
                binding.time.setText(new DateFormatter(new Date(calendar.getTimeInMillis())).getTime());
            }
        },present.get(Calendar.HOUR_OF_DAY),present.get(Calendar.MINUTE),false);

        binding.date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                timePickerDialog.dismiss();
                datePickerDialog.show();
            }
        });
        binding.time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                datePickerDialog.dismiss();
                timePickerDialog.show();
            }
        });

    }

    private void getDataFromEditText(){
        appointment = new Appointment();
        //Fill the details in appointment object.
    }

    private void startPayment(){

    }

    private void onPaymentSuccess(){
        AppointmentController.create(appointment, new AppointmentController.AppointmentDatabaseHandler() {
            @Override
            public void onSuccess(Appointment appointment) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void onPaymentFailure(){
        Snackbar.make(binding.getRoot(),"Payment Failure",Snackbar.LENGTH_SHORT).show();
    }

    private boolean validate(){
        if(binding.date.getText().length() == 0){
            Snackbar.make(binding.getRoot(),"Please enter a date",Snackbar.LENGTH_SHORT).show();
            return false;
        }else if(binding.time.getText().length() == 0){
            Snackbar.make(binding.getRoot(),"Please enter a time",Snackbar.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}