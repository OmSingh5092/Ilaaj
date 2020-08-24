package com.codenite.ilaaj.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.activities.HomeActivity;
import com.codenite.ilaaj.databinding.FragmentDashboardBinding;

import androidx.fragment.app.Fragment;
public class DashboardFragment extends Fragment {
    FragmentDashboardBinding binding;
    HomeActivity activity;

    public DashboardFragment(HomeActivity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(LayoutInflater.from(getActivity()),container,false);

        binding.appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(1);
            }
        });
        binding.conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(2);
            }
        });
        return binding.getRoot();
    }
}