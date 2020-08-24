package com.codenite.ilaaj.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.databinding.FragmentDashboardBinding;

import androidx.fragment.app.Fragment;
public class DashboardFragment extends Fragment {
    FragmentDashboardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(LayoutInflater.from(getActivity()),container,false);
        return binding.getRoot();
    }
}