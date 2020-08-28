package com.codenite.ilaaj.activities;

import android.os.Bundle;

import com.codenite.ilaaj.databinding.ActivityPatientDetailsBinding;

import androidx.appcompat.app.AppCompatActivity;

public class PatientDetailsActivity extends AppCompatActivity {
    ActivityPatientDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}