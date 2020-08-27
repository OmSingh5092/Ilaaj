package com.codenite.ilaaj.activities;

import android.os.Bundle;
import android.view.View;

import com.codenite.ilaaj.api.controllers.AppointmentController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.databinding.ActivityAppointmentBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentActivity extends AppCompatActivity {
    ActivityAppointmentBinding binding;
    Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

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