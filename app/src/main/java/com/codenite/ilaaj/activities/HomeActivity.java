package com.codenite.ilaaj.activities;

import android.os.Bundle;
import android.view.Gravity;

import com.codenite.ilaaj.databinding.ActivityHomeBinding;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);


    }

    @Override
    public boolean onSupportNavigateUp() {
        binding.drawer.openDrawer(Gravity.LEFT);
        return super.onSupportNavigateUp();
    }
}