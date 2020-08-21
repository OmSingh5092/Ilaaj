package com.codenite.ilaaj.activities;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.codenite.ilaaj.databinding.ActivityPreviousReportsBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PreviousReportsActivity extends AppCompatActivity {
    ActivityPreviousReportsBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        ActivityPreviousReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
